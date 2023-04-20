import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.sql.*;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.groupingBy;

public class DBServer {
    public static List<AirCraft> loadTable(){
        ArrayList<AirCraft> list = new ArrayList<>();
        try {
            Connection connection = DriverManager.getConnection("jdbc:sqlite:aircraft.db");
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM Aircrafts");
            while(rs.next())
            {
                list.add(new AirCraft(
                        rs.getString("name"),
                        rs.getString("country"),
                        rs.getInt("year")
                ));
            }
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return list;
    }
    public static void main(String[] args) throws IOException {
        List<AirCraft> list = loadTable();
        ServerSocket serverSocket = new ServerSocket(10002);
        while(true)
        {
            Socket socket = serverSocket.accept();
            ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
            new Thread(()->{
                while(true)
                {
                    try {
                        Integer token = (Integer) in.readObject();
                        if(token==-1)
                        {
                            //Return all years
                            List<Integer> rs = list.stream().map(a->a.getYear()).
                                distinct().collect(Collectors.toList());
                            out.writeObject(rs);
                        }
                        else if(token==0)
                        {
                            //Return aircraft count data for all countries
                            String rs = list.stream().
                                    //Group all object based on the value return from getCountry
                                    //Then count the objects in each group
                                    //Then return a Map with Key: Country Value: count
                                    collect(groupingBy(AirCraft::getCountry,Collectors.counting()))
                                    .entrySet()//Convert Map to a list so we can do futher steam OPs
                                    .stream()
                                    .map(ks->ks.getKey()+":"+ks.getValue())//Convert Key,Val set to "key:val" string
                                    .collect(Collectors.joining(","));//Join all "key:val" string to 1 string with ","
                            out.writeObject(rs);
                        }
                        else {
                            //Return aircraft list for a year and token is the year
                            List<AirCraft> rs = list.stream().filter(a->a.getYear()==token).collect(Collectors.toList());
                            out.writeObject(rs);
                        }
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    } catch (ClassNotFoundException e) {
                        throw new RuntimeException(e);
                    }

                }

            }).start();
        }
    }
}

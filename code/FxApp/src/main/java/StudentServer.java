import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class StudentServer {
    public static final int PORT = 8001;
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        ServerSocket serverSocket = new ServerSocket(PORT);
        System.out.println("Server started");
        ObjectOutputStream outputStream = new ObjectOutputStream(
                new FileOutputStream("student.dat")
        );
        while(true){
            Socket socket = serverSocket.accept();
            ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream());
            Object object = inputStream.readObject();
            outputStream.writeObject(object);
            System.out.println("A new student record is stored");

        }

    }
}

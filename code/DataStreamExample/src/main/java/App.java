import java.io.*;
public class App {
    public static void main(String[] args) {
        try(DataOutputStream outputStream=
                new DataOutputStream(new FileOutputStream("temp.dat")))
        {
            outputStream.writeUTF("Alex");
            outputStream.writeInt(18);
            outputStream.writeDouble(Math.PI);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        try(DataInputStream inputStream=
                new DataInputStream(new FileInputStream("temp.dat")))
        {
            System.out.println(inputStream.readUTF());
            System.out.println(inputStream.readInt());
            System.out.println(inputStream.readDouble());
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}

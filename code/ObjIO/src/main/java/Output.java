import java.io.*;
public class Output {

    public static void main(String[] args) throws IOException {
        try ( // Create an output stream for file object.dat
              ObjectOutputStream output =
                      new ObjectOutputStream(new FileOutputStream("object.dat"));
        ) {
            // Write a string, double value, and object to the file
            output.writeUTF("John");
            output.writeDouble(85.5);
            output.writeObject(new java.util.Date());
        }
        try(ObjectInputStream inputStream=new ObjectInputStream(new FileInputStream("object.dat")))
                {
                        System.out.println(inputStream.readUTF());
                        System.out.println(inputStream.readDouble());
                        System.out.println(inputStream.readObject());
                } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }


    }

}

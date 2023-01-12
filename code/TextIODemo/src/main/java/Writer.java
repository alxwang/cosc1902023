import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class Writer {
    public static void main(String[] args) {
        java.io.File file = new java.io.File("test.txt");
        if(file.exists())
        {
            System.out.println("File is already exists, will delete it");
            file.delete();
        }

        //The commented code works but
        //1. throw the related exception to jvm
        //2. It does not handle any other exception
        //3. If something happens when written, the printwriter will leave opened

//        java.io.PrintWriter output = new java.io.PrintWriter(file);
//        output.println("Hello!");
//        output.println(100);
//        output.println(Math.PI);
//        output.printf("This is my text file %s size %d", file.getAbsolutePath() + file.getName(), file.length());
//        output.close();

// This is a good way to do it, but it is comlicated
//        java.io.PrintWriter output=null;
//        try {
//            output = new java.io.PrintWriter(file);
//            output.println("Hello!");
//            output.println(100);
//            output.println(Math.PI);
//            output.printf("This is my text file %s size %d", file.getAbsolutePath() + file.getName(), file.length());
//        }
//        catch (Exception e)
//        {
//
//        }
//        finally {
//            if(output != null)output.close();
//        }
        try(java.io.PrintWriter output = new java.io.PrintWriter(file);)
        {
            output.println("Hello!");
            output.println(100);
            output.println(Math.PI);
            output.printf("This is my text file %s size %d", file.getAbsolutePath() + file.getName(), file.length());
        }
        catch (Exception e)
        {
        }
    }
}

import java.io.*;
public class App {

    public static void main(String[] args)  {
        try(
        FileOutputStream outputStream = new FileOutputStream("temp.dat"))
        {
            //11111111
            //If it is unsigned int , it will be 255
            //If it is singed int, it will be -1 1 1111111
            for(int i=1;i<10;i++)
                outputStream.write(i);//We are writing 10 bytes 1-10 in the file NOT 10 ints which will be 40 bytes
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try(
                FileInputStream inputStream=new FileInputStream("temp.dat")
                )
        {
            int nVal;
            while((nVal=inputStream.read())!=-1)//read will return -1 if EOF
                System.out.println(nVal);
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


}

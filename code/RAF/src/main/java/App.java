import java.io.*;
public class App {
    public static final int SIZE_INT  = 4;
    public static void main(String[] args) throws IOException{
        try(RandomAccessFile io = new RandomAccessFile("io.dat","rw"))
        {
            //Clear the content
            io.setLength(0);
            for(int i=0;i<200;i++) io.writeInt(i);
            System.out.println("File Len="+io.length());

            //go back to begining
            io.seek(0);
            //Seek to the second num
            io.seek(1*SIZE_INT);//SEEK FROM BEGINING NOT FROM THE CUR position
            System.out.println("the second num is:"+io.readInt());

            //seek to 10th num:
            io.seek(9*SIZE_INT);
            System.out.println("the 10th num is:"+io.readInt());

            //Update 11th num
            io.writeInt(555);

            io.seek(io.length());
            io.writeInt(999);
            System.out.println("File Len="+io.length());

        }

    }
}

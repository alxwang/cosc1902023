import java.io.File;
public class DirSize {
    public static void main(String[] args) {
        System.out.print("Please enter a dir or a file:");
        String dir= new java.util.Scanner(System.in).nextLine();
        System.out.println(getSize(new File(dir))+" bytes");
    }

    public static long getSize(File file)
    {
        long size = 0;
        if(file.isDirectory())
        {//It is a dir
            File[] files = file.listFiles();
            if(files!=null)
            for(File f : files)
                if(f !=null) size+=getSize(f);
        }
        else {//It is a file
            size+=file.length();
        }
        return size;
    }

    //HOMEWORK!
    //1. Make this app works in your PC
    //2. Change the code only count the size of all *.exe files
    //3. Change the code:
    //3.1 Read one more input from users call large_file_size as int
    //3.2 Print out all large file in the dir(file size>large_file_size)
}

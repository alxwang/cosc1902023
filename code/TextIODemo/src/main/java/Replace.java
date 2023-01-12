import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Replace {
    public static void main(String[] args) throws FileNotFoundException {
        //for(String arg:args)System.out.println(arg);
        if(args.length!=4)
        {
            System.out.println("Usage:java Replace sourceFile targetFile oldStr newStr");
            System.exit(1);
        }
        File sourceFile = new File(args[0]);
        if(!sourceFile.exists())
        {
            System.out.printf("Source file %s is not existing\n",args[0]);
            System.exit(2);
        }
        File targetFile = new File(args[1]);
        if(targetFile.exists())
        {
            String backupName = args[1]+".bak";
            targetFile.renameTo(new File(backupName));
        }
        try(Scanner in=new Scanner(sourceFile);
            PrintWriter out=new PrintWriter(targetFile))
        {
            while(in.hasNext())
            {
                String line = in.nextLine();
                String replaced = line.replaceAll(args[2],args[3]);
                out.println(replaced);
            }
        }
    }
}

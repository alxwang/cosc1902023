import java.io.*;
public class Copy {

    public static void main(String[] args) {
        if(args.length!=2)
        {
            System.out.println("Usage: java Copy srcFile TargetFile");
            System.exit(1);
        }

        File src = new File(args[0]);
        if(!src.exists())
        {
            System.out.printf("Source File %s does not exist.\n",args[0]);
            System.exit(2);
        }

        File dst = new File(args[1]);
        if(dst.exists())
        {
            System.out.printf("Target file %s already exists\n",args[1]);
            dst.delete();
        }

        long startTime = System.currentTimeMillis();
        try (BufferedInputStream inputStream=new BufferedInputStream(new FileInputStream(src),1000000);
             BufferedOutputStream outputStream=new BufferedOutputStream(new FileOutputStream(dst),1000000)
                               )
        {
            int r;
            int bytesCopied = 0;
            while((r=inputStream.read())!=-1)
            {
                outputStream.write((byte)r);
                bytesCopied++;
            }
            //default buf size 471
            //Buf size 1    89428
            //Buf size 10 9902
            //Buf size 100 1739
            //Buf 1M 318
            long stopTime = System.currentTimeMillis();
            System.out.printf("Copied %d bytes takes %d\n",bytesCopied,stopTime-startTime);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }



}

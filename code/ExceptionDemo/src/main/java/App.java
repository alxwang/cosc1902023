import java.util.ArrayList;
import java.util.Arrays;
import java.util.InputMismatchException;

public class App {

    //Integer class
    public static int str2Int(String s)
    {
//        int rs=0;
//        You also need to confirm each char is a number
//        for(int i=s.length()-1;i>=0;i--)rs+=Math.pow(10,i)*//s.chatat[i] to the number;
//        return rs;
        try{
          int rs= Integer.parseInt(s);
          return rs;}
        catch(Exception e)
        {
            return 0;
        }
    }

    public static double divide(int left, int right) throws IllegalArgumentException {
        if(right==0)
        {
            //Old way to handle the error without exception
            /*
            System.out.println("Can not divide by zero");
            return -1;//Assume that left and right are both postive
            */

            throw new IllegalArgumentException("Can not divide by zero");

        }
        return (double)left/(double)right;
    }

    public static double[] divide(int[] left, int[] right) throws IllegalArgumentException, IndexOutOfBoundsException,
            EmptyListException {
        if(left.length==0 || left.length!= right.length)
        {
            throw new EmptyListException(left);//("Input must be an array with at lease one element");
//            IndexOutOfBoundsException  e = new IndexOutOfBoundsException("Input must be an array with at lease one element");
//            throw e;
        }
        double[] rs = new double[left.length];
        //You can make your choice to either handle the exception with try catch
        //Or pass the exception to the caller by declare the exception in the sign
        //The following is pass to caller
        //for(int i=0;i<left.length;i++) rs[i]=divide(left[i],right[i]);
        //Or do something and pass to caller
        try{
            for(int i=0;i<left.length;i++) rs[i]=divide(left[i],right[i]);
        }
        catch (IllegalArgumentException e)
        {
            System.out.println("The args need to be array with at lease one int");
            throw e;//Your work is done and not pass it to the caller
        }

        return rs;
    }


    public static int getaNumber(String prompt)
    {
        do {
            try {
                System.out.print(prompt);
                int nVal = new java.util.Scanner(System.in).nextInt();
                return nVal;
            } catch (InputMismatchException e) {
                System.out.println("Please enter a number instead");
            }
        }while(true);
    }


    //If user enter some int ,return it
    //Otherwise, return default value
    public static int getaNumber(String prompt,int defaultvalue)
    {
        int nVal = defaultvalue;
        try {
            System.out.print(prompt);
            nVal = new java.util.Scanner(System.in).nextInt();
            return nVal;
        }
        catch (InputMismatchException e) {
            System.out.println("You have entered a non int value. the Default value will be used");
            //What if a exception is throwed in the process of your exception handler
            //return defaultvalue;
        }
        finally {
            return nVal;
        }

    }



    public static void main(String[] args) {
        int[] left = new int[1];
        left[0]=getaNumber("Please enter a number:");
        int[] right= new int[1];
        right[0]=getaNumber("Please enter another number:");

        //[1,2,3,4]
        //new ArrayList<Integer>(Arrays.asList(1,2,3,4)).stream().mapToInt(i->i).toArray()

        try {
            System.out.println(
                    divide(left,right)
//                    divide(new ArrayList<Integer>(Arrays.asList(getaNumber("Please enter a number:"))).
//                                    stream().mapToInt(i->i).toArray(),
//                            new ArrayList<Integer>(Arrays.asList(getaNumber("Please enter another number:"))).
//                                    stream().mapToInt(i->i).toArray())
            );
        }
        catch(IllegalArgumentException e)
        {
            System.out.println(e.getMessage());
        }
        catch (EmptyListException e)
        {
            int[] list = e.getData();
            System.out.println(Arrays.toString(list));

        }
        catch (RuntimeException e)
        {

        }
        //If you want to catch Exception, put it at the bottom
        catch (Exception e)
        {
            System.out.println(e.getMessage()+"?");
        }
    }
}

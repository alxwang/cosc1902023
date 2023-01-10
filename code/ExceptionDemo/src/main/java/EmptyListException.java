import java.util.Arrays;

public class EmptyListException extends Exception{
    public int[] data;
    public EmptyListException(int[] list)
    {
        super("Input must be an array with at lease one element");
        data= Arrays.copyOf(list,list.length);
    }

    public int[] getData()
    {
        return data;
    }

}

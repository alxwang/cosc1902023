import java.util.Arrays;

public class InsertionSort {
    public static void sort(int[] nVals)
    {
        //for each element
        //start at 1 because the 0th insertion is done
        for ( int element2Insert = 1; element2Insert < nVals.length;element2Insert++)
        {
            int element2InsertValue = nVals[element2Insert];
            int element2Compare = 0;
            for (element2Compare = element2Insert-1; element2Compare >= 0; element2Compare--)
            {
                //If the element to be compared is greater than the element to be inserted
                //It means that the element to be inserted should be inserted in front of the element to be compared
                //Therefore, shift the element to be compared to right by one slow
                if( nVals[element2Compare] > element2InsertValue) nVals[element2Compare+1] = nVals[element2Compare];
                else break;///If the element to be compared is less than the element to be inserted, we have found the slot to insert, end the search
            }
            //Do the insert
            nVals[element2Compare+1] = element2InsertValue;
        }
    }
    static int[] arr = {2, 9, 5, 4, 8, 1, 6};

    public static void main(String[] args) {
        sort(arr);
        System.out.println(Arrays.toString(arr));
    }


}

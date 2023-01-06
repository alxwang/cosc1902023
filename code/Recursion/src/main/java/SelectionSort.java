import java.util.Arrays;

public class SelectionSort {
    public static void sort(int[] naVals,int low,int high) //Help
    {
        if(low>=high) return;//base case
        //Find the smallest number and its index
        int indexofMin = low;
        int min = naVals[low];
        for(int i=low+1;i<=high;i++)
        {
            if(naVals[i]<min)
            {
                min=naVals[i];
                indexofMin=i;
            }
        }
        if(indexofMin!=low){
            naVals[indexofMin]=naVals[low];
            naVals[low]=min;
        }
        sort(naVals,low+1,high);
    }

    public static void sort(int[] naVals) //Help
    {
        sort(naVals,0,naVals.length-1);
    }
    static int[] arr = {2, 9, 5, 4, 8, 1, 6};

    public static void main(String[] args) {
        sort(arr);
        System.out.println(Arrays.toString(arr));
    }

}

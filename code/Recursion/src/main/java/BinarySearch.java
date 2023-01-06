import java.util.Arrays;

public class BinarySearch {
    static int[] arr = {2, 9, 5, 4, 8, 1, 6};

    public static void main(String[] args) {
        SelectionSort.sort(arr);
        System.out.println(Arrays.toString(arr));
        int index = search(arr,9);
        System.out.println(index);
    }

    public static int search(int[] naVals, int key)//help
    {
        return search(naVals,key,0,naVals.length-1);
    }

    public static int search(int[] naVals, int key,int low,int high)
    {
        //base case
        if(low>high) return -1;
        int mid = (low+high)/2;
        if(key<naVals[mid])
            return search(naVals,key,low,mid-1);
        else if (key>naVals[mid])
            return search(naVals,key,mid+1,high);
        else return mid;
    }

}

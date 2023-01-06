import java.util.Arrays;

public class MergeSort {
    static int[] arr = {2, 9, 5, 4, 8, 1, 6};

    public static void main(String[] args) {
        sort(arr);
        System.out.println(Arrays.toString(arr));
    }

    public static void merge(int[] naLeft, int[] naRight, int[]naDest)
    {
        int curLeft =0;
        int curRight = 0;
        int curDest =0;
        //1 When naLeft and naRight both has elements to be processed
        while(curLeft<naLeft.length && curRight<naRight.length)
        {
            if(naLeft[curLeft]<naRight[curRight])
                naDest[curDest++]=naLeft[curLeft++];
            else
                naDest[curDest++]=naRight[curRight++];
        }
        //2. If naLeft has something left
        while(curLeft<naLeft.length)
            naDest[curDest++]=naLeft[curLeft++];
        //2. If naRight has something left
        while(curRight<naRight.length)
            naDest[curDest++]=naRight[curRight++];
    }
    public static void sort(int[] naVals)
    {
        //base case
        if(naVals.length==1) return;
        int[] naLeftHalf = Arrays.copyOfRange(naVals,0,naVals.length/2);
        int[] naRightHalf = Arrays.copyOfRange(naVals,naLeftHalf.length,naVals.length);
        sort(naLeftHalf);
        sort(naRightHalf);
        merge(naLeftHalf,naRightHalf,naVals);

    }
}

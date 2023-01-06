import java.util.Arrays;

public class BubbleSort {
    public static void sort(int[] naVals)
    {
        boolean bNeedDoItAgain = true;
        for(int i=1;i<naVals.length;i++)
        {
            bNeedDoItAgain=false;
            for(int j=0;j<naVals.length-i;j++)
            {
                if(naVals[j]>naVals[j+1])
                {
                    bNeedDoItAgain=true;
                    int temp = naVals[j];
                    naVals[j] = naVals[j+1];
                    naVals[j+1]=temp;
                }
            }
            if(!bNeedDoItAgain) break;
        }
    }

    static int[] arr = {2, 9, 5, 4, 8, 1, 6};

    public static void main(String[] args) {
        sort(arr);
        System.out.println(Arrays.toString(arr));
    }

}

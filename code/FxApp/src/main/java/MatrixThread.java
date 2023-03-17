import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MatrixThread {
    private static int[][] matrix = new int[10][100];
    private static int row_rs[]=new int[matrix.length];

    private static int max(int[] row)
    {
        int maxval = row[0];
        for(int n: row) if (n>maxval) maxval=n;
        return maxval;
    }

    static class Task implements Runnable{
        private int task_index;
        public Task(int task_index)
        {
            this.task_index=task_index;
        }
        @Override
        public void run() {
            row_rs[task_index] =max(matrix[task_index]);
        }
    }
    public static void main(String[] args) throws InterruptedException {
        for(int row=0;row<matrix.length;row++)
            for(int col=0;col<matrix[0].length;col++)
                matrix[row][col]=(int)(Math.random()*1000.0);
        //Find the max value in the Matrix
        for(int row =0;row<matrix.length;row++)
        {
            row_rs[row] = max(matrix[row]);
        }
        System.out.println(max(row_rs));
        //Find the max value in the Matrix with threads
        //Create 10 thread to get the max value from 10 row
        //Save the rs in row_rs
        Thread[] Threads = new Thread[matrix.length];
        for(int i =0;i<matrix.length;i++)
            Threads[i]=new Thread(new Task(i));
        for(int i =0;i<matrix.length;i++)
            Threads[i].start();
        for(int i =0;i<matrix.length;i++)
            Threads[i].join();
        //after all threads are stopped
        System.out.println(max(row_rs));

        //Find the max in the matrix with ThreadPool
        //Find the max value in the Matrix with threadpool
        //Create 10 thread to get the max value from 10 row
        ExecutorService executorService =
                Executors.newFixedThreadPool(matrix.length);
        for(int i =0;i<matrix.length;i++)
            executorService.execute(new Task(i));
        executorService.shutdown();
        //after all threads are stopped
        System.out.println(max(row_rs));


    }
}

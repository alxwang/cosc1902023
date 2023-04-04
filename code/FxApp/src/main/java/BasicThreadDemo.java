public class BasicThreadDemo{
    static boolean bKeepRunning = true;

    public static void main(String[] args) throws InterruptedException {
        Thread thread1=new Thread(()->{
            System.out.println("thead start");
           while(bKeepRunning)
           {
               System.out.println("a");
           }
            System.out.println("thead exit");
        });
        thread1.start();
        Thread.sleep(1);
        System.out.println("Attempt to stop thread");
        bKeepRunning=false;
    }

}

public class ThreadJoinDemo {
    static  int i = 0;
    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(()->{
            i++;
        });
        thread.start();//You need to start the thread to make it works
        thread.join();//You need to wait for it completed to see the result
        System.out.println(i);
    }

}

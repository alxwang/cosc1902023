public class TaskThreadDemo {
    public static void main(String[] args) throws InterruptedException {//main is exec in main thread
        //1 Create task
        Runnable printA =
                new PrintChar('A',100);
        Runnable printB =
                new PrintChar('B',100);
        //2 create thread

        //((PrintChar)printA).setSleep(50);

        Thread thread1 = new Thread(printA);
        System.out.println(thread1.isAlive());//False
        Thread thread2 = new Thread(printB);
        //At this point of time ,threads are not started yet
        //3 start thread

//        thread1.setPriority(Thread.MAX_PRIORITY);

        thread1.start();//Start a thread(1) and exec printA task in the new thread
        System.out.println(thread1.isAlive());//True
        thread2.start();//Start a thread(2) and exec printB task in the new thread

        //thread.start will not block main thread

//        new Thread(new PrintChar('c',100)).start();

        //This following line of code will exec before the thread started unless using join

        thread1.join();//join will block main thread until thread1 is end
        System.out.println(thread1.isAlive());//False
        thread2.join();

        System.out.println("\n All thread are completed");

    }

}

class PrintChar implements Runnable{
    private char ch;
    private int times;

    private int sleep = 40;
    public void setSleep(int sleep){this.sleep=sleep;}
    public PrintChar(char c, int times)
    {
        this.ch=c;
        this.times=times;
    }
    @Override
    public void run() {
        for(int i=0;i<times;i++) {
            System.out.print(ch);
            //As the current thread, I am OK to stop for a while so other threads can exec
//            Thread.yield();//Same as sleep(1)

//            try {
//                Thread.sleep(1);
//            } catch (InterruptedException e) {
//                throw new RuntimeException(e);
//            }

            try {
                Thread.sleep(sleep);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class AccountApp {
    private static Account account=new Account();
    public static void main(String[] args) {
        ExecutorService executorService= Executors.newCachedThreadPool();
        for(int i=0;i<100;i++){
            executorService.execute(()->account.deposit(1));
        }
        executorService.shutdown();
        while(!executorService.isTerminated());
        System.out.println(account.getBalance());

    }

    public static class Account{
        //If you have static lock, it is share between all object created from the class
        //Otherwise it is share/locked in current object
        //private Lock lock = new ReentrantLock();
        private Object lock = new Object();
        private int balance = 0;

        public int getBalance() {
            return balance;
        }

        public /*synchronized*/ void deposit(int amount)
        {

            //Assume we are doing some work like create the DB connection to DB server
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

//            lock.lock();
          synchronized (lock)
          //try
          { //this is same as using synchronized in method sign
                int newBalance = this.balance + amount;
                System.out.printf("%d -> Balance = %d newBalance=%d\n ", Thread.currentThread().getId(),
                        this.balance, newBalance);
                try {
                    Thread.sleep(0);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                this.balance = newBalance;
            }
//            finally {//ALWAYS put unlock in a finally block
//                lock.unlock();
//            }
        }
    }
}


import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class AccountExApp {
    private static Account account=new Account();
    public static void main(String[] args) {
        ExecutorService executorService= Executors.newFixedThreadPool(2);
        executorService.execute(()->{
            while(true) account.deposit((int)(Math.random()*100.0+1.0));
        });
        executorService.execute(()->{
            while(true) account.withdraw((int)(Math.random()*100.0+1.0));
        });

    }

    public static class Account{
        private int balance = 0;
        private Lock lock = new ReentrantLock();
        private Condition condition = lock.newCondition();


        public int getBalance() {
            return balance;
        }
        public void withdraw(int amount){
            lock.lock();
            try {
                while (this.balance < amount) {
                    try {
                        System.out.printf("%d -> WAIT: Balance = %d Need %d \n",
                                Thread.currentThread().getId(),
                                this.balance, amount);
                        //Thread.sleep(50);
                        condition.await();//I need other thread can exec so I have chance to break this loop
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
                System.out.printf("%d -> Balance = %d newBalance=%d\n ", Thread.currentThread().getId(),
                        this.balance, this.balance - amount);
                this.balance -= amount;
            }
            finally {
                lock.unlock();
            }
        }
        public void deposit(int amount) {
            lock.lock();
            try {
                int newBalance = this.balance + amount;
                System.out.printf("%d -> Balance = %d newBalance=%d\n ", Thread.currentThread().getId(),
                        this.balance, newBalance);
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                this.balance = newBalance;
                condition.signalAll();//I just update the balance so maybe some withdraw task can be exec so let them know
                //condition.notifyAll();//DO not use this one
            }
            finally {
                lock.unlock();
            }
        }
    }
}

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadPoolDemo {
    public static void main(String[] args) {
        ExecutorService executorService =
                Executors.newFixedThreadPool(3);
        executorService.execute(new PrintChar('A',100));
        executorService.execute(new PrintChar('B',100));
        executorService.execute(new PrintChar('C',100));
        executorService.shutdown();
        //Wait(join) all 3 threads complete
        //Then shutdown the pool


    }

}

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.*;
import java.text.ParseException;
import java.util.PriorityQueue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Kitchen {
    private static PriorityQueue<Order> orders = new PriorityQueue<>();
    private static Lock lock = new ReentrantLock();
    private static Condition condition = lock.newCondition();

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(10001);
        System.out.println("Kitchen starts");
        while(true)
        {
            Socket socket = serverSocket.accept();
            new Thread(()->{
                try {
                    DataInputStream in = new DataInputStream(socket.getInputStream());
                    DataOutputStream out = new DataOutputStream(socket.getOutputStream());
                    String str = in.readUTF();
                    String[] lst = str.split(",");
                    if (lst[0].compareTo("CHEF") == 0)
                    {//This thread is for a chef con
                        String ID = lst[1];
                        System.out.println("Chef "+ID+" is connected");
                        while(true)
                        {
                            Order order = null;
                            try{
                                lock.lock();
                                order = orders.poll();
                                if(order==null){
                                    out.writeUTF("WAIT");
                                    condition.await();
                                }
                            } catch (InterruptedException e) {
                                throw new RuntimeException(e);
                            } finally {
                                lock.unlock();
                            }
                            if(order!=null)
                            {
                                String orderStr = order.toString();
                                out.writeUTF(orderStr);
                                System.out.println(orderStr + "send to Chef "+ID);
                            }
                        }
                    }
                    else if (lst[0].compareTo("ORDER") == 0)
                    {//This thread is for a cashier con
                        System.out.println(str + " is received");
                        Order order = new Order(str);
                        try {
                            lock.lock();
                            orders.add(order);
                            condition.signalAll();
                        }
                        finally {
                            lock.unlock();
                        }
                    }
                    else {
                        System.out.println("Wrong protocol");
                    }

                } catch (IOException e) {
                    throw new RuntimeException(e);
                } catch (ParseException e) {
                    throw new RuntimeException(e);
                }


            }).start();
        }
    }


}

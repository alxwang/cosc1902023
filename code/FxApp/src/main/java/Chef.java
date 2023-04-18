import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class Chef {
    public static void sleepRandomSec(int min,int max) throws InterruptedException {
        Thread.sleep((int)(Math.random()*(max-min)+min)*10);
    }

    static class Task implements Runnable{
        int ID = 0;
        public Task(int id){this.ID=id;}
        @Override
        public void run() {
            Socket socket = null;
            try {
                socket = new Socket("localhost",10001);
                DataInputStream in = new DataInputStream(socket.getInputStream());
                DataOutputStream out = new DataOutputStream(socket.getOutputStream());
                out.writeUTF("CHEF,"+ID);
                while(true)
                {
                    sleepRandomSec(0,10);
                    String str = in.readUTF();
                    System.out.println("CHEF "+ID+" received: "+ str);
                    if(str.compareTo("WAIT")==0){
                        sleepRandomSec(10,20);
                    }
                    else {
                        System.out.println("CHEF "+ID+" received an Order: "+ str);
                        sleepRandomSec(0,20);
                    }
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

        }
    }

    public static void main(String[] args) {
        new Thread(new Task(1)).start();
        new Thread(new Task(2)).start();
    }
}

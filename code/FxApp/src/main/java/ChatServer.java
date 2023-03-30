import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;


public class ChatServer extends Application {
    private TextArea ta = new TextArea();
    public final static int PORT = 8000;
    private Queue<String> queue = new LinkedList<>();
    private ArrayList<MountPoint> mps = new ArrayList<>();

    private void appendText(String s){
        Platform.runLater(()->{
            ta.appendText(s+"\n");
        });
    }
    @Override
    public void start(Stage stage) throws Exception {
        Scene sence= new Scene(new ScrollPane(ta),450,200);
        stage.setTitle("Server"); // Set the stage title
        stage.setScene(sence); // Place the scene in the stage
        stage.show(); // Display the stage

        //Start socket server
        new Thread(()->{
            appendText("Server stated...");
            try {
                ServerSocket serverSocket = new ServerSocket(PORT);
                while(true)
                {
                    Socket socket= serverSocket.accept();
                    //Start a thead for the income connection
                    new Thread(()->{
                        try {
                            MountPoint mp = new MountPoint(socket);
                            synchronized (mps) {
                                mps.add(mp);
                            }
                            appendText(mp.getName()+" is connected");
                            while(mp.isConnected())
                            {
                                String msg = mp.read();
                                if(msg.equals(MountPoint.BYE)) break;
                                synchronized (queue) {
                                    queue.add(msg);
                                }
                                appendText(msg.replace(MountPoint.SEP,":"));
                            }
                            appendText(mp.getName()+" is disconncted");
                            mps.remove(mp);
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    }).start();
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        }).start();

        //Broadcasting thread
        new Thread(()->{
            while(true)
            {
                String msg ;
                synchronized(queue) {
                    msg=queue.remove();
                }

                synchronized (mps) {
                    for (MountPoint mp : mps) {
                        try {
                            mp.write(msg);
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }
            }
        }).start();
    }
}

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;


public class MultiClientsServer extends Application {
    private TextArea ta = new TextArea();
    private int clientNo = 0;
    private void appendText(String s){
        Platform.runLater(()->{
            ta.appendText(clientNo+": "+s+"\n");
        });
    }
    @Override
    public void start(Stage stage) throws Exception {
        // Create a scene and place it in the stage
        Scene scene = new Scene(new javafx.scene.control.ScrollPane(ta), 450, 200);
        stage.setTitle("Server"); // Set the stage title
        stage.setScene(scene); // Place the scene in the stage
        stage.show(); // Display the stage

        //Start the server socket listening(accepting) thread
        new Thread(()->{
                try {
                    ServerSocket serverSocket = new ServerSocket(8000);
                    appendText("Server started.");
                    while(true) {
                        Socket socket = serverSocket.accept();
                        clientNo++;
                        InetAddress inetAddress = socket.getInetAddress();
                        appendText(String.format("Client %s(%s) connected",inetAddress.getHostName(),
                                inetAddress.getHostAddress()));
                        //Start the thread for the IO of the client just connected
                        new Thread(()->{
                            try {
                                DataInputStream in = new DataInputStream(socket.getInputStream());
                                DataOutputStream out = new DataOutputStream(socket.getOutputStream());
                                while (true) {
                                    double radius = in.readDouble();
                                    double area = radius * radius * Math.PI;
                                    out.writeDouble(area);
                                    appendText("Radius received: " + radius );
                                    appendText("Area returned: " + area );

                                }
                            }catch (IOException e) {
                                throw new RuntimeException(e);
                            }
                        }).start();
                    }
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
        }).start();

    }
}

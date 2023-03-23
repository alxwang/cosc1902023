import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;


public class SimpleServer extends Application {


    @Override
    public void start(Stage stage) throws Exception {
        TextArea ta = new TextArea();
        // Create a scene and place it in the stage
        Scene scene = new Scene(new ScrollPane(ta), 450, 200);
        stage.setTitle("Server"); // Set the stage title
        stage.setScene(scene); // Place the scene in the stage
        stage.show(); // Display the stage

        new Thread(()->{
            try {
                ServerSocket serverSocket=new ServerSocket(8000);
                Platform.runLater(()->{
                    ta.appendText("Server started at "+new Date()+"\n");
                });
                Socket socket = serverSocket.accept();
                DataInputStream in = new DataInputStream(socket.getInputStream());
                DataOutputStream out = new DataOutputStream(socket.getOutputStream());
                while(true)
                {
                    double radius = in.readDouble();
                    double area = radius*radius*Math.PI;
                    out.writeDouble(area);
                    Platform.runLater(()->{
                        ta.appendText("Radius received: "+radius+"\n");
                        ta.appendText("Area returned: "+area+"\n");

                    });
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        }).start();
    }
}

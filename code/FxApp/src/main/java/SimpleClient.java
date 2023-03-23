import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;


public class SimpleClient extends Application {
    private static  Socket socket;
    private static DataOutputStream out;
    private  static  DataInputStream in;

    @Override
    public void start(Stage stage) throws Exception {
        BorderPane paneforText = new BorderPane();
        paneforText.setPadding(new Insets(5,5,5,5));
        paneforText.setStyle("-fx-border-color: green");
        paneforText.setLeft(new Label("Enter a radius: "));

        TextField tf = new TextField();
        tf.setAlignment(Pos.BOTTOM_RIGHT);
        paneforText.setCenter(tf);

        BorderPane pane = new BorderPane();
        TextArea ta = new TextArea();
        pane.setCenter(new javafx.scene.control.ScrollPane(ta));
        pane.setTop(paneforText);

        // Create a scene and place it in the stage
        Scene scene = new Scene(pane, 450, 200);
        stage.setTitle("Client"); // Set the stage title
        stage.setScene(scene); // Place the scene in the stage
        stage.show(); // Display the stage

        tf.setOnAction(e->{
            double radius = Double.parseDouble(tf.getText().trim());
            try {
                out.writeDouble(radius);
                out.flush();
                double area = in.readDouble();
                ta.appendText("Radius send: "+radius+"\n");
                ta.appendText("Area returned: "+area+"\n");
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }

        });

        new Thread(()->{
            try {
                socket = new Socket("localhost",8000);
                out = new DataOutputStream(socket.getOutputStream());
                in = new DataInputStream(socket.getInputStream());

            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }

        }).start();
    }
}

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class Cashier extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        BorderPane paneforFirstName  = new BorderPane();
        paneforFirstName.setPadding(new Insets(5,5,5,5));
        paneforFirstName.setLeft(new Label("First Name:"));
        TextField tffirstName = new TextField();
        tffirstName.setAlignment(Pos.CENTER_LEFT);
        paneforFirstName.setCenter(tffirstName);

        BorderPane paneforLastName  = new BorderPane();
        paneforLastName.setPadding(new Insets(5,5,5,5));
        paneforLastName.setLeft(new Label("First Name:"));
        TextField tflastName = new TextField();
        tflastName.setAlignment(Pos.CENTER_LEFT);
        paneforLastName.setCenter(tflastName);

        Button button = new Button("Send");

        VBox box = new VBox(10);
        box.getChildren().addAll(paneforFirstName,paneforLastName,button);

        Scene sence = new Scene(box,450,200);
        stage.setTitle("Cashier");
        stage.setScene(sence);
        stage.show();

        button.setOnAction(e->{
            Socket socket = null;
            try {
                socket = new Socket("localhost",10001);
                DataOutputStream out = new DataOutputStream(socket.getOutputStream());
                Order order = new Order(tffirstName.getText().trim(),
                                        tflastName.getText().trim());
                out.writeUTF(order.toString());
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }

        });


    }
}

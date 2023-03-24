import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;


public class StudentClient extends Application {
    private TextField tfName = new TextField();
    private TextField tfSteet = new TextField();
    private TextField tfCity = new TextField();
    private TextField tfProv = new TextField();
    private TextField tfPostcode = new TextField();

    private Button btSend = new Button("Send the student record to the server");

    String host = "localhost";
    @Override
    public void start(Stage stage) throws Exception {
        GridPane pane = new GridPane();
        pane.add(new Label("Name"),0,0);
        pane.add(tfName,1,0);
        pane.add(new Label("Street"),0,1);
        pane.add(tfSteet,1,1);
        pane.add(new Label("City"),0,2);
        HBox hBox = new HBox(2);
        pane.add(hBox,1,2);
        hBox.getChildren().addAll(tfCity,new Label("Prov"),tfProv,new Label("Post Code:"),tfPostcode);
        pane.add(btSend,1,3);
        GridPane.setHalignment(btSend, HPos.RIGHT);

        pane.setAlignment(Pos.CENTER);
        tfName.setPrefColumnCount(15);
        tfSteet.setPrefColumnCount(15);
        tfCity.setPrefColumnCount(10);
        tfPostcode.setPrefColumnCount(7);
        tfProv.setPrefColumnCount(2);

        Scene scene = new Scene(pane, 450, 200);
        stage.setTitle("StudentClient"); // Set the stage title
        stage.setScene(scene); // Place the scene in the stage
        stage.show(); // Display the stage

        btSend.setOnAction(e->{
            try {
                Socket socket = new Socket(host,StudentServer.PORT);
                ObjectOutputStream stream=new ObjectOutputStream(socket.getOutputStream());
                StudentAddress address=new StudentAddress(
                        tfName.getText().trim(),
                        tfSteet.getText().trim(),
                        tfCity.getText().trim(),
                        tfProv.getText().trim(),
                        tfPostcode.getText().trim()
                );
                stream.writeObject(address);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }


        });

    }
}

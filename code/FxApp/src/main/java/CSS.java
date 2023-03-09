import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
public class CSS extends Application{
    @Override
    public void start(Stage stage) throws Exception {
        StackPane pane = new StackPane();
        Button btnOK = new Button("OK");
        btnOK.setStyle("-fx-border-color: blue");
        pane.getChildren().add(btnOK);

        Scene scene = new Scene(pane,200,250);
        pane.setStyle("-fx-border-color: red;-fx-background-color: lightgrey");
        pane.setRotate(90);
        stage.setScene(scene);
        stage.show();
    }
}

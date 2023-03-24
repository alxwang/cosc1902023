import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.scene.control.Button;
public class ButtonDemo extends Application {
    Text text=new Text(50,50,"JavaFx Programming");
    @Override
    public void start(Stage stage) throws Exception {
        Scene scene = new Scene(getPane(), 450, 200);
        stage.setTitle("ButtonDemo"); // Set the stage title
        stage.setScene(scene); // Place the scene in the stage
        stage.show(); // Display the stage
    }

    Pane getPane() throws FileNotFoundException {
        HBox hBox = new HBox(20);
        ImageView imgLeft = new ImageView(new Image(new FileInputStream("left.png")));
        imgLeft.setFitHeight(10);
        imgLeft.setFitWidth(10);
        Button btLeft = new Button("Left",imgLeft);
        ImageView imgRight = new ImageView(new Image(new FileInputStream("right.png")));
        imgRight.setFitWidth(10);
        imgRight.setFitHeight(10);
        Button btRight = new Button("Right", imgRight);
        hBox.getChildren().addAll(btLeft,btRight);
        hBox.setAlignment(Pos.CENTER);
        hBox.setStyle("-fx-border-color: green");

        BorderPane pane=new BorderPane();
        pane.setBottom(hBox);

        Pane textPane = new Pane();

        textPane.getChildren().add(text);
        pane.setCenter(textPane);
        btRight.setOnAction(e->text.setX(text.getX()+5));
        btLeft.setOnAction(e->text.setX(text.getX()-5));

        return pane;
    }
}

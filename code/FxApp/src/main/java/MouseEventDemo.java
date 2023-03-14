import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.*;
import javafx.stage.Stage;
public class MouseEventDemo extends Application{
    @Override
    public void start(Stage stage) throws Exception {
        Pane pane= new Pane();
        Text text = new Text(20,20,"Programing is fun");
        pane.getChildren().add(text);

        text.setOnMouseDragged(e->{
            text.setX(e.getX());
            text.setY(e.getY());
        });
        // Create a scene and place it in the stage
        Scene scene = new Scene(pane, 300, 100);
        stage.setTitle("MouseEventDemo"); // Set the stage title
        stage.setScene(scene); // Place the scene in the stage
        stage.show(); // Display the stage

    }
}

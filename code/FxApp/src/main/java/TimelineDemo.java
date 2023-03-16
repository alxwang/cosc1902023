import javafx.animation.FadeTransition;
import javafx.animation.KeyFrame;
import javafx.animation.PathTransition;
import javafx.animation.Timeline;
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
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.*;
import javafx.stage.Stage;
import javafx.util.Duration;
public class TimelineDemo extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        StackPane pane = new StackPane();
        Text text = new Text(20,50,"Programming is fun");
        text.setFill(Color.RED);
        pane.getChildren().add(text);

        Timeline timeline = new Timeline(new KeyFrame(
           Duration.millis(500),
           e->{
               if(text.getText().length()!=0) text.setText("");
               else text.setText("Programming is fun");
           }
        ));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
        // Create a scene and place it in the stage
        Scene scene = new Scene(pane, 250, 50);
        stage.setTitle("TimelineDemo"); // Set the stage title
        stage.setScene(scene); // Place the scene in the stage
        stage.show(); // Display the stage
    }
}

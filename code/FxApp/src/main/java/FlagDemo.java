import javafx.animation.PathTransition;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.*;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.FileInputStream;

public class FlagDemo extends Application{
    @Override
    public void start(Stage stage) throws Exception {
        Pane pane = new HBox(10);
        pane.setPadding(new Insets(5,5,5,5));
        Image image = new Image(new FileInputStream("canada.png"));
        ImageView imageView = new ImageView(image);
        pane.getChildren().add(imageView);

        PathTransition pt = new PathTransition(Duration.millis(10000),
                new Line(100,500,100,100),
                imageView);
        pt.setCycleCount(5);
        pt.play();

        // Create a scene and place it in the stage
        Scene scene = new Scene(pane, 600, 600);
        stage.setTitle("FlagRisingAnimation"); // Set the stage title
        stage.setScene(scene); // Place the scene in the stage
        stage.show(); // Display the stage
    }
}

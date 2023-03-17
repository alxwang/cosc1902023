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
import javafx.scene.shape.Rectangle;
import javafx.scene.text.*;
import javafx.stage.Stage;
import javafx.util.Duration;

public class PathDemo extends Application{
    @Override
    public void start(Stage stage) throws Exception {
        Pane pane = new Pane();
        Rectangle rectangle = new Rectangle(0,0,25,50);
        rectangle.setFill(Color.ORANGE);

        Circle circle = new Circle(125,100,50);
        circle.setFill(Color.WHITE);
        circle.setStroke(Color.BLACK);

        pane.getChildren().addAll(circle,rectangle);

        PathTransition pt = new PathTransition();
        pt.setDuration(Duration.millis(4000));
        pt.setPath(circle);
        pt.setNode(rectangle);
        pt.setCycleCount(Timeline.INDEFINITE);
        pt.setAutoReverse(true);
        pt.setOrientation(PathTransition.OrientationType.ORTHOGONAL_TO_TANGENT);
        pt.play();

        circle.setOnMousePressed(e->pt.pause());
        circle.setOnMouseReleased(e->pt.play());


        // Create a scene and place it in the stage
        Scene scene = new Scene(pane, 250, 200);
        stage.setTitle("PathTransitionDemo"); // Set the stage title
        stage.setScene(scene); // Place the scene in the stage
        stage.show(); // Display the stage

    }
}

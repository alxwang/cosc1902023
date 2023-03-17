import javafx.animation.FadeTransition;
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
public class FadeDemo extends Application{


    @Override
    public void start(Stage stage) throws Exception {
        Pane pane = new Pane();
        Ellipse ellipse = new Ellipse(10,10,100,50);
        ellipse.setFill(Color.RED);
        ellipse.setStroke(Color.BLACK);
        ellipse.centerXProperty().bind(pane.widthProperty().divide(2));
        ellipse.centerYProperty().bind(pane.heightProperty().divide(2));
        ellipse.radiusXProperty().bind(pane.widthProperty().multiply(0.4));
        ellipse.radiusYProperty().bind(pane.heightProperty().multiply(.4));
        pane.getChildren().add(ellipse);

        FadeTransition ft = new FadeTransition(Duration.millis(3000),ellipse);
        ft.setCycleCount(Timeline.INDEFINITE);
        ft.setAutoReverse(true);
        ft.setFromValue(1.0);
        ft.setToValue(0.1);
        ft.play();

        ellipse.setOnMousePressed(e->ft.pause());
        ellipse.setOnMouseReleased(e->ft.play());

        Scene scene = new Scene(pane, 200, 150);
        stage.setTitle("FadeTransitionDemo"); // Set the stage title
        stage.setScene(scene); // Place the scene in the stage
        stage.show(); // Display the stage
    }
}

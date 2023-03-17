import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.util.Duration;

public class ClockApp extends Application{

    @Override
    public void start(Stage stage) throws Exception {
        BorderPane pane = new BorderPane();
        ClockPane clockPane = new ClockPane();
        pane.setCenter(clockPane);

        Label curTime = new Label(clockPane.getHour()+":"+clockPane.getMinute()+":"+clockPane.getSecond());
        pane.setBottom(curTime);

        Timeline timeline = new Timeline(
                new KeyFrame(
                        Duration.millis(1000),
                        e->{
                            clockPane.setCurrentTime();
                            curTime.setText(clockPane.getHour()+":"+clockPane.getMinute()+":"+clockPane.getSecond());
                        }
                )
        );
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();

        // Create a scene and place it in the stage
        Scene scene = new Scene(pane, 250, 250);
        stage.setTitle("DisplayClock"); // Set the stage title
        stage.setScene(scene); // Place the scene in the stage
        stage.show(); // Display the stage
    }
}

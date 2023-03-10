import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
public class ClockApp extends Application{

    @Override
    public void start(Stage stage) throws Exception {
        BorderPane pane = new BorderPane();
        ClockPane clockPane = new ClockPane();
        pane.setCenter(clockPane);

        Label curTime = new Label(clockPane.getHour()+":"+clockPane.getMinute()+":"+clockPane.getSecond());
        pane.setBottom(curTime);

        // Create a scene and place it in the stage
        Scene scene = new Scene(pane, 250, 250);
        stage.setTitle("DisplayClock"); // Set the stage title
        stage.setScene(scene); // Place the scene in the stage
        stage.show(); // Display the stage
    }
}

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.*;
import javafx.stage.Stage;
public class FlowPaneDemo extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        //FlowPane pane = new FlowPane();
        FlowPane pane = new FlowPane(Orientation.VERTICAL);
        pane.setPadding(new Insets(11,23,13,14));
        pane.setHgap(5);
        pane.setVgap(5);

        pane.getChildren().addAll(new Label("First name:"),
                new TextField(), new Label("MI:"));
        TextField tfMI = new TextField();

        tfMI.setPrefColumnCount(1);

        pane.getChildren().addAll(tfMI,new Label("Last name:"),new TextField());

        Scene scene = new Scene(pane, 200, 250);
        stage.setTitle("ShowFlowPane"); // Set the stage title
        stage.setScene(scene); // Place the scene in the stage
        stage.show(); // Display the stage

    }
}


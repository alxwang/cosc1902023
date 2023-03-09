import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;

class CustomPane extends StackPane{
    public CustomPane(String title, String color)
    {
        this.getChildren().add(new Label(title));
        this.setStyle("-fx-background-color: "+color);
        this.setPadding(new Insets(11.5,12.5,13.5,14.5));
    }
}

public class BorderPaneDemo extends Application{
    private Pane getPane(Pane center_pane)
    {
        BorderPane pane = new BorderPane();
        pane.setTop(new CustomPane("Top","red"));
        pane.setBottom(new CustomPane("Bottom","green"));
        pane.setLeft(new CustomPane("Left","blue"));
        pane.setRight(new CustomPane("Right","yellow"));
        pane.setCenter(center_pane);
        return pane;
    }
    @Override
    public void start(Stage stage) throws Exception {

        Pane center_pane = getPane(new CustomPane("Center","orange"));
        Pane pane = getPane(center_pane);

        Scene scene = new Scene(pane,500,300);
        stage.setTitle("ShowBorderPane"); // Set the stage title
        stage.setScene(scene); // Place the scene in the stage
        stage.show(); // Display the stage
    }
}

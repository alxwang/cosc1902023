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
        this.setPadding(new Insets(11.5,12.5,13.5,14.5));
        this.setStyle("-fx-padding: 10;"
                + "-fx-border-style: solid inside;"
                + "-fx-border-width: 2;"
                + "-fx-border-insets: 5;"
                + "-fx-border-radius: 5;"
                + "-fx-border-color: blue;");
        //this.setStyle(";-fx-opacity: 0.8; -fx-background-color: "+color);

    }
}

public class BorderPaneDemo extends Application{
    private Pane getPane(String name, Pane center_pane)
    {
        BorderPane pane = new BorderPane();
        pane.setTop(new CustomPane(name+":"+"Top","red"));
        pane.setBottom(new CustomPane(name+":"+"Bottom","green"));
        pane.setLeft(new CustomPane(name+":"+"Left","blue"));
        pane.setRight(new CustomPane(name+":"+"Right","yellow"));
        pane.setCenter(center_pane);
        return pane;
    }
    @Override
    public void start(Stage stage) throws Exception {

        Pane inner_pane = getPane("Inner",new CustomPane("Inner: Center","orange"));
        //Pane outer_pane = getPane("Outer",inner_pane);

        Scene scene = new Scene(inner_pane,500,300);
        stage.setTitle("ShowBorderPane"); // Set the stage title
        stage.setScene(scene); // Place the scene in the stage
        stage.show(); // Display the stage
    }
}

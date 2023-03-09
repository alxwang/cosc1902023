import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class HBoxVBoxDemo extends Application{
    @Override
    public void start(Stage stage) throws Exception {
        BorderPane borderPane = new BorderPane();
        borderPane.setTop(getHBox());
        borderPane.setRight(getVBox());;

        Scene scene=new Scene(borderPane);
        stage.setTitle("ShowHBoxVBox"); // Set the stage title
        stage.setScene(scene); // Place the scene in the stage
        stage.show(); // Display the stage
    }

    private VBox getVBox()
    {
        VBox vBox = new VBox(15);
        vBox.setPadding(new Insets(15,5,5,5));
        vBox.getChildren().add(new Label("Courses"));

        Label[] courses = {
          new Label("COSC 190"),
                new Label("CMATH 292"),
                new Label("COSC 286"),
                new Label("COSC 292")
        };
        for(Label label:courses)
        {
            //Insets(double top, double right, double bottom, double left)
            vBox.setMargin(label,new Insets(0,0,0,15));
            vBox.getChildren().add(label);
        }
        return vBox;
    }
    private HBox getHBox() throws FileNotFoundException {
        HBox hBox = new HBox(15);//15 is the spacing in the box
        hBox.setPadding(new Insets(15,15,15,15));
        hBox.setStyle("-fx-background-color: gold");
        hBox.getChildren().add(new Button("Computer Science"));
        hBox.getChildren().add(new Button("Chemistry"));
        Image image = new Image(new FileInputStream("canada.png"));
        ImageView imageView = new ImageView(image);
        hBox.getChildren().add(imageView);
        return hBox;
    }
}

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.io.FileInputStream;

public class LabelwithGraphic extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        Image image = new Image(new FileInputStream("canada.png"));
        Label lb1 = new Label("Canada",new ImageView(image));
        lb1.setStyle("-fx-border-color: green;-fx-border-width: 2");
        lb1.setContentDisplay(ContentDisplay.BOTTOM);
        lb1.setTextFill(Color.RED);

        Label lb2 = new Label("Circle",new Circle(50,50,25));
        lb2.setContentDisplay(ContentDisplay.TOP);
        lb2.setTextFill(Color.ORANGE);

        Label lb3 = new Label("Rectangle",new Rectangle(10,10,50,25));
        lb3.setContentDisplay(ContentDisplay.RIGHT);

        Ellipse ellipse = new Ellipse(50,50,50,25);
        ellipse.setStroke(Color.GREEN);
        ellipse.setFill(Color.FIREBRICK);
        Label lb4 = new Label("Ellipse",ellipse);
        lb4.setContentDisplay(ContentDisplay.LEFT);

        Ellipse ellipse1 = new Ellipse(50,50,50,25);
        ellipse1.setStroke(Color.ORANGE);
        ellipse1.setFill(Color.WHITE);
        Label lb5 = new Label("Ellipse",ellipse1);
        lb5.setContentDisplay(ContentDisplay.CENTER);


        HBox pane = new HBox(20);
        pane.getChildren().addAll(lb1,lb2,lb3,lb4,lb5);
        // Create a scene and place it in the stage
        Scene scene = new Scene(pane, 450, 150);
        stage.setTitle("LabelWithGraphic"); // Set the stage title
        stage.setScene(scene); // Place the scene in the stage
        stage.show(); // Display the stage
    }
}

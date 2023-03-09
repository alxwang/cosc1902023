import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.scene.text.*;
public class TextDemo extends Application{
    @Override
    public void start(Stage stage) throws Exception {
        Pane pane = new Pane();
        pane.setPadding(new Insets(5,5,5,5));

        Text text1 = new Text(20,20,"Programming is fun");
        text1.setFont(Font.font("Courier", FontWeight.BOLD,
                FontPosture.ITALIC, 15));
        pane.getChildren().add(text1);

        Text text2 = new Text(60,60,"Programming is fun\nJava is good!");
        text2.setFill(Color.RED);
        text2.setUnderline(true);
        text2.setStrikethrough(true);
        pane.getChildren().add(text2);



        // Create a scene and place it in the stage
        Scene scene = new Scene(pane);
        stage.setTitle("ShowText"); // Set the stage title
        stage.setScene(scene); // Place the scene in the stage
        stage.show(); // Display the stage

    }
}

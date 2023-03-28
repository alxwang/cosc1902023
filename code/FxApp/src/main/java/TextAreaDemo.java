import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.FileInputStream;

public class TextAreaDemo extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        DescPane descPane = new DescPane();
        descPane.setDesc("The Canada national flag");
        descPane.setTitle("Canada");
        Image image = new Image(new FileInputStream("canada.png"));
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(300);
        imageView.setFitHeight(150);
        descPane.setImageView(imageView);
        // Create a scene and place it in the stage
        Scene scene = new Scene(descPane, 450, 200);
        stage.setTitle("TextAreaDemo"); // Set the stage title
        stage.setScene(scene); // Place the scene in the stage
        stage.show(); // Display the stage
    }
}

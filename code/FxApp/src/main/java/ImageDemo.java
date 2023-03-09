import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.*;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileInputStream;

public class ImageDemo extends Application{
    @Override
    public void start(Stage stage) throws Exception {
        Pane pane = new HBox(10);
        pane.setPadding(new Insets(5,5,5,5));
        Image image = new Image(new FileInputStream("canada.png"));
        double height = image.getHeight();
        double width = image.getWidth();
        for(int i=0;i<10;i++) {
            ImageView imageView = new ImageView(image);
            imageView.setFitHeight(height);
            imageView.setFitWidth(width);
            pane.getChildren().add(imageView);
            height/=2.0;
            width/=2.0;
        }


        Scene scene = new Scene(pane);
        stage.setTitle("Image Demo");
        stage.setScene(scene);
        stage.show();
    }
}

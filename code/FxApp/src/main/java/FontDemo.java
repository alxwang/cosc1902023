import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.*;
import javafx.stage.Stage;
public class FontDemo extends Application{
    @Override
    public void start(Stage stage) throws Exception {
        StackPane pane = new StackPane();

        Label label  = new Label("JavaFx");
        label.setFont(Font.font("Times New Roman",
                FontWeight.BOLD, FontPosture.ITALIC,20));
        pane.getChildren().add(label);


        Circle circle = new Circle();
        circle.setRadius(50);
        circle.setFill(new Color(0.5,0.5,0.5,0.1));
        circle.setStroke(Color.BLACK);
        pane.getChildren().add(circle);


        Scene scene = new Scene(pane,200,250);
        stage.setTitle("Font Demo");
        stage.setScene(scene);
        stage.show();

    }
}

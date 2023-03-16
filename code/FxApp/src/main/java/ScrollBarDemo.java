import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.*;
import javafx.stage.Stage;
public class ScrollBarDemo extends  Application{
    @Override
    public void start(Stage stage) throws Exception {
        Text text = new Text(20,20,"JavaFX programming");
        Pane paneForText = new Pane();
        paneForText.getChildren().add(text);

        BorderPane pane = new BorderPane();
        pane.setCenter(paneForText);

        ScrollBar sbHori = new ScrollBar();
        ScrollBar sbVert = new ScrollBar();
        sbVert.setOrientation(Orientation.VERTICAL);

        pane.setRight(sbVert);
        pane.setBottom(sbHori);

        sbHori.valueProperty().addListener(e->{
//            text.setText(String.format("%f %f %f",sbHori.getMin(),
//                    sbHori.getMax(),
//                    sbHori.getValue()));
            text.setX(sbHori.getValue()*paneForText.getWidth()/sbHori.getMax());

        });

        sbVert.valueProperty().addListener(e->{
            text.setY(sbVert.getValue()*paneForText.getHeight()/sbVert.getMax());
        });



        Scene scene = new Scene(pane, 450, 170);
        stage.setTitle("ScrollBarDemo"); // Set the stage title
        stage.setScene(scene); // Place the scene in the stage
        stage.show(); // Display the stage
    }
}

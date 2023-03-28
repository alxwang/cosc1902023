import javafx.application.Application;
import javafx.geometry.Orientation;
import javafx.scene.Scene;
import javafx.scene.control.Slider;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class SliderDemo extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        Text text = new Text(20,20,"Slider Demo");
        Slider slH = new Slider();
        slH.setShowTickLabels(true);
        slH.setShowTickMarks(true);
        slH.setMax(200);
        Slider slV = new Slider();
        slV.setOrientation(Orientation.VERTICAL);
        slV.setShowTickLabels(true);

        Pane panForText= new Pane();
        panForText.getChildren().add(text);

        BorderPane pane = new BorderPane();
        pane.setCenter(panForText);
        pane.setBottom(slH);
        pane.setRight(slV);

        slH.valueProperty().addListener(e->
                text.setX(slH.getValue()*panForText.getWidth()/slH.getMax())
                );

        slV.valueProperty().addListener(e->
                text.setY((slV.getMax()-slV.getValue())*panForText.getHeight()/slV.getMax())
                );

        Scene scene = new Scene(pane, 450, 170);
        stage.setTitle("SliderDemo"); // Set the stage title
        stage.setScene(scene); // Place the scene in the stage
        stage.show(); // Display the stage



    }
}

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;


import java.io.FileNotFoundException;

public class RadioButtonDemo extends CheckBoxDemo{
    @Override
    Pane getPane() throws FileNotFoundException {
        BorderPane pane=(BorderPane) super.getPane();
        VBox vBox=new VBox(20);
        vBox.setPadding(new Insets(5,5,5,5));
        vBox.setStyle("-fx-border-color: red");

        RadioButton rbRed = new RadioButton("Red");
        RadioButton rbGreen = new RadioButton("Green");
        RadioButton rbBlue = new RadioButton("Blue");
        vBox.getChildren().addAll(rbRed,rbGreen,rbBlue);

        //TO make sure radio buttons clear each other,
        // you have to assign them to a same group
        ToggleGroup group = new ToggleGroup();
        rbGreen.setToggleGroup(group);
        rbRed.setToggleGroup(group);
        rbBlue.setToggleGroup(group);

        pane.setLeft(vBox);

        EventHandler<ActionEvent> handler = e->{
            if(rbRed.isSelected()) text.setFill(Color.RED);
            if(rbGreen.isSelected()) text.setFill(Color.GREEN);
            if(rbBlue.isSelected()) text.setFill(Color.BLUE);
        };

        rbGreen.setOnAction(handler);
        rbBlue.setOnAction(handler);
        rbRed.setOnAction(handler);
        return pane;
    }

    @Override
    public void start(Stage stage) throws Exception {
        super.start(stage);
        stage.setTitle("RadioButton Demo");
    }
}

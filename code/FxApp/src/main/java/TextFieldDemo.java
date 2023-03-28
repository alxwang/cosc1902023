import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;


import java.io.FileNotFoundException;

public class TextFieldDemo extends RadioButtonDemo{
    @Override
    Pane getPane() throws FileNotFoundException {
        BorderPane pane  = (BorderPane) super.getPane();
        //Add HBox/borderpane
        //Add label and textfile into hbox
        //Add hbox to pane
        //setonaction for textfiled to update text
        BorderPane pane1 = new BorderPane();
        pane1.setPadding(new Insets(5,5,5,5));
        TextField tf = new TextField();
        tf.setAlignment(Pos.BOTTOM_RIGHT);
        pane1.setCenter(tf);
        pane1.setLeft(new Label("Enter a new message: "));
        pane.setTop(pane1);
        tf.setOnAction(e->text.setText(tf.getText()));
        return pane;
    }

    @Override
    public void start(Stage stage) throws Exception {
        super.start(stage);
        stage.setTitle("TextField Demo");
    }
}

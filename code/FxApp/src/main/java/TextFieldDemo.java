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

        return pane;
    }

    @Override
    public void start(Stage stage) throws Exception {
        super.start(stage);
        stage.setTitle("TextField Demo");
    }
}

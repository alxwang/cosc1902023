import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.CheckBox;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

import java.io.FileNotFoundException;

public class CheckBoxDemo extends ButtonDemo{

    @Override
    Pane getPane() throws FileNotFoundException {
        BorderPane pane= (BorderPane) super.getPane();
        Font fontBoldItalic = Font.font("Times New Roman",
                FontWeight.BOLD, FontPosture.ITALIC, 20);
        Font fontBold = Font.font("Times New Roman",
                FontWeight.BOLD, FontPosture.REGULAR, 20);
        Font fontItalic = Font.font("Times New Roman",
                FontWeight.NORMAL, FontPosture.ITALIC, 20);
        Font fontNormal = Font.font("Times New Roman",
                FontWeight.NORMAL, FontPosture.REGULAR, 20);
        text.setFont(fontNormal);

        VBox paneCheckBox = new VBox(20);
        paneCheckBox.setPadding(new Insets(5,5,5,5));
        paneCheckBox.setStyle("-fx-border-color: blue");
        CheckBox chkBold = new CheckBox("Bold");
        CheckBox chkItalic = new CheckBox("Italic");
        paneCheckBox.getChildren().addAll(chkBold,chkItalic);

        EventHandler<ActionEvent> handler = e->{
            if(chkBold.isSelected() && chkItalic.isSelected())
                text.setFont(fontBoldItalic);
            else
            if(chkBold.isSelected() && !chkItalic.isSelected())
                text.setFont(fontBold);
            else
            if(!chkBold.isSelected() && chkItalic.isSelected())
                text.setFont(fontItalic);
            else
                text.setFont(fontNormal);
        };
        chkItalic.setOnAction(handler);
        chkBold.setOnAction(handler);

        pane.setRight(paneCheckBox);



        return pane;
    }

    @Override
    public void start(Stage stage) throws Exception {
        super.start(stage);
        stage.setTitle("CheckBox Demo");
    }


}

import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
public class GridDemo extends Application{
    @Override
    public void start(Stage stage) throws Exception {
        GridPane pane = new GridPane();//No need to init row and col count

        pane.setAlignment(Pos.CENTER);
        pane.setPadding(new Insets(11.5,12.5,13.5,14.5));
        pane.setVgap(5.5);
        pane.setHgap(5.5);


        //Pane has a add
        pane.add(new Label("First Name:"),0,0);//Node, col, row
        pane.add(new TextField(),1,0);//col and row are zero based

        pane.add(new Label("MI:"),0,1);
        pane.add(new TextField(),1,1);

        pane.add(new Label("Last Name:"),0,2);
        pane.add(new TextField(),1,2);

        Button btnAdd = new Button("Add Name");
        pane.add(btnAdd,1,3);
        GridPane.setHalignment(btnAdd, HPos.RIGHT);
        Scene scene = new Scene(pane);
        stage.setTitle("ShowGridPane"); // Set the stage title
        stage.setScene(scene); // Place the scene in the stage
        stage.show(); // Display the stage


    }
}

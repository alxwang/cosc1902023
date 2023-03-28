import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class ListViewDemo extends Application {
    private String[] flagTitles = {"Canada","US"};

    private ImageView[] flagImageViews =
            {new ImageView(new Image(new FileInputStream("canada.png"))),
                    new ImageView(new Image(new FileInputStream("us.png")))};

    public ListViewDemo() throws FileNotFoundException {
    }

    @Override
    public void start(Stage stage) throws Exception {
        //Create listview with items in
        ListView<String> lv = new ListView<>(FXCollections.observableArrayList(flagTitles));
        lv.setPrefSize(140,400);
        //Enable multi-choice (contrl-click)
        lv.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        FlowPane imagePane = new FlowPane(10,10);

        BorderPane pane = new BorderPane();
        pane.setLeft(lv);
        pane.setCenter(imagePane);

        //Handle click
        lv.getSelectionModel().selectedItemProperty().addListener(e->{
            imagePane.getChildren().clear();
            //For each select item(index)
            for(Integer i:lv.getSelectionModel().getSelectedIndices())
                imagePane.getChildren().add(flagImageViews[i]);
        });

        Scene scene = new Scene(pane, 450, 170);
        stage.setTitle("ListViewDemo"); // Set the stage title
        stage.setScene(scene); // Place the scene in the stage
        stage.show(); // Display the stage
    }
}

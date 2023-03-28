import javafx.application.Application;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class ComboBoxDemo extends Application {
    private ComboBox<String> cbo = new ComboBox<>();
    private DescPane descPane = new DescPane();

    private String[] flagTitles = {"Canada","US"};
    private String[] flagDesc = {"The Canadian nation Flag","The United States of America national flag"};

    private ImageView[] flagImageViews =
            {new ImageView(new Image(new FileInputStream("canada.png"))),
                    new ImageView(new Image(new FileInputStream("us.png")))};

    public ComboBoxDemo() throws FileNotFoundException {
    }

    void setDisplay(int index)
    {
        descPane.setImageView(flagImageViews[index]);
        descPane.setTitle(flagTitles[index]);
        descPane.setDesc(flagDesc[index]);
    }
    @Override
    public void start(Stage stage) throws Exception {
        BorderPane pane = new BorderPane();

        BorderPane paneForCombox = new BorderPane();
        paneForCombox.setPadding(new Insets(5,5,5,5));
        paneForCombox.setLeft(new Label("Select a country"));
        paneForCombox.setCenter(cbo);

        //Add texts(items) into combobox
        ObservableList<String> items = FXCollections.observableArrayList(flagTitles);
        cbo.getItems().addAll(items);
        //Get combobox select item
        cbo.setOnAction(e->setDisplay(items.indexOf(cbo.getValue())));
        cbo.setPrefWidth(390);
        pane.setTop(paneForCombox);
        pane.setCenter(descPane);

        cbo.setValue(flagTitles[0]);
        setDisplay(0);

        // Create a scene and place it in the stage
        Scene scene = new Scene(pane, 450, 170);
        stage.setTitle("ComboBoxDemo"); // Set the stage title
        stage.setScene(scene); // Place the scene in the stage
        stage.show(); // Display the stage
    }
}

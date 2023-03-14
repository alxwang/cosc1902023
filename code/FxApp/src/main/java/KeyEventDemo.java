import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.*;
import javafx.stage.Stage;
public class KeyEventDemo extends Application{
    @Override
    public void start(Stage stage) throws Exception {
        Pane pane = new Pane();
        Text text = new Text(20,20,"A");
        pane.getChildren().add(text);

        text.setOnKeyPressed(e->{
            switch (e.getCode()){
                case DOWN -> text.setY(text.getY()+5);
                case UP -> text.setY(text.getY()-5);
                case LEFT -> text.setX(text.getX()-5);
                case RIGHT -> text.setX(text.getX()+5);
                default -> {
                    if(e.getText().length()>0) text.setText(e.getText());
                }
            }
        });

        Scene scene = new Scene(pane,200,100);

        stage.setTitle("KeyEventDemo"); // Set the stage title
        stage.setScene(scene); // Place the scene in the stage
        stage.show(); // Display the stage
        text.requestFocus();
    }
}

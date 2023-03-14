import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.*;
import javafx.stage.Stage;
public class EventInto extends Application {
    Button btOK=new Button("OK");
    Button btCancel=new Button("Cancel");

    class BtnActionHandler implements EventHandler<ActionEvent>{

        @Override
        public void handle(ActionEvent actionEvent) {
            if (actionEvent.getSource()==btCancel)
                System.out.println("Cancel clicked");
            else
                System.out.println("OK clicked");
        }
    }

    @Override
    public void start(Stage stage) throws Exception {
        HBox pane = new HBox(10);
        pane.setAlignment(Pos.CENTER);
        //Button btOK = new Button("OK");
        //btOK.setOnAction(new OKHandlerClass());
        btOK.setOnAction(new BtnActionHandler());

        //Button btCancel = new Button("Cancel");
        //btCancel.setOnAction(new CancelHandlerClass());
        btCancel.setOnAction(new BtnActionHandler());

        pane.getChildren().addAll(btOK,btCancel);

        stage.setScene(new Scene(pane,300,200));
        stage.show(); // Display the stage
    }
}
class CancelHandlerClass implements EventHandler<ActionEvent>{

    @Override
    public void handle(ActionEvent actionEvent) {

        System.out.println("Cancel clicked");
    }
}
class OKHandlerClass implements EventHandler<ActionEvent>{

    @Override
    public void handle(ActionEvent actionEvent) {
        System.out.println("OK clicked");
    }
}

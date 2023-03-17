import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.*;
import javafx.stage.Stage;
public class CircleResize extends Application {
    Circle circle =new Circle(50);
    Button btEnlarge = new Button("Enlarge");
    Button btShrink = new Button("Shrink");

    class btnClickHandlerInner implements EventHandler<ActionEvent>
    {

        @Override
        public void handle(ActionEvent actionEvent) {
            if(actionEvent.getSource()==btEnlarge)
                circle.setRadius(circle.getRadius()+5);
            else
                circle.setRadius(circle.getRadius()-5);
        }
    }

    @Override
    public void start(Stage stage) throws Exception {
        Pane pane = new Pane();
        circle.setStroke(Color.BLACK);
        circle.setFill(Color.WHITE);
        pane.getChildren().add(circle);

        HBox hBox = new HBox();
        hBox.setSpacing(10);
        hBox.setAlignment(Pos.CENTER);
//1. External class event handler
//        btEnlarge.setOnAction(new btnEventHandler(circle,5));
//        btShrink.setOnAction(new btnEventHandler(circle,-5));
//2. Inner class event handler
//        btEnlarge.setOnAction(new btnClickHandlerInner());
//        btShrink.setOnAction(new btnClickHandlerInner());
//3. Anonymous class event handler
//        btEnlarge.setOnAction(new EventHandler<ActionEvent>() {
//              @Override
//              public void handle(ActionEvent actionEvent) {
//                    circle.setRadius(circle.getRadius()+5);
//              }
//          });
//        btShrink.setOnAction(new EventHandler<ActionEvent>() {
//            @Override
//            public void handle(ActionEvent actionEvent) {
//                    circle.setRadius(circle.getRadius()-5);
//            }
//        });
        //4. Lambda
        btShrink.setOnAction(e->circle.setRadius(circle.getRadius()-5));
        btEnlarge.setOnAction(e->circle.setRadius(circle.getRadius()+5));

        circle.setOnMouseClicked(e->{
            if(e.getButton()== MouseButton.PRIMARY)
            {
                circle.setRadius(circle.getRadius()+5);
            }
            else if(e.getButton()==MouseButton.SECONDARY)
            {
                circle.setRadius(circle.getRadius()-5);
            }
        });


        hBox.getChildren().addAll(btEnlarge,btShrink);

        BorderPane borderPane = new BorderPane();
        borderPane.setCenter(pane);
        borderPane.setBottom(hBox);

        Scene scene = new Scene(borderPane, 300, 200);

        scene.setOnKeyPressed(e->{
            switch (e.getCode()){
                case S -> {circle.setCenterY(circle.getCenterY()+20);}
                case W -> circle.setCenterY(circle.getCenterY()-20);
                case A -> circle.setCenterX(circle.getCenterX()-5);
                case D -> circle.setCenterX(circle.getCenterX()+5);
            }
        });


        stage.setTitle("ControlCircle"); // Set the stage title
        stage.setScene(scene); // Place the scene in the stage
        stage.show(); // Display the stage

    }
}

class btnEventHandler implements EventHandler<ActionEvent> {
    private Circle circle;
    private int step;
    public btnEventHandler(Circle circle,int step)
    {
        this.circle=circle;
        this.step = step;
    }


    @Override
    public void handle(ActionEvent actionEvent) {
        circle.setRadius(circle.getRadius()+step);
    }
}


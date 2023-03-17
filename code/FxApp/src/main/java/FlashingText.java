import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

public class FlashingText extends Application {
    private String text_value;
    @Override
    public void start(Stage stage) throws Exception {
        StackPane pane = new StackPane();
        Text text = new Text(20,50,"Programming is fun");
        text.setFill(Color.RED);
        pane.getChildren().add(text);

        new Thread(new Runnable() {
            @Override
            public void run() {
                while(true)
                {
                    if(text.getText().length()==0)
                        text_value = "Programming is fun";
                    else
                        text_value = "";
                    //you can not update UI in your worker thread
                    //You can only do it in main thread
//                    Platform.runLater(new Runnable() {
//                        @Override
//                        public void run() {
//                            text.setText(text_value);
//                        }
//                    });
                    Platform.runLater(()->text.setText(text_value));
                    try {
                        Thread.sleep(200);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }).start();

        // Create a scene and place it in the stage
        Scene scene = new Scene(pane, 250, 50);
        stage.setTitle("TimelineDemo"); // Set the stage title
        stage.setScene(scene); // Place the scene in the stage
        stage.show(); // Display the stage
    }
}

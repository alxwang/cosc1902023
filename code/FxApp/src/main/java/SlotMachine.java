import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.*;
import javafx.stage.Stage;
public class SlotMachine extends Application {
    //We support all 9 icons now
    final static int MAX_ICONS = 9;
    //Three slots
    private ImageView imgSlot1= new ImageView();;
    private ImageView imgSlot2= new ImageView();;
    private ImageView imgSlot3= new ImageView();;
    //All the icons
    private Image[] imgSource;
    //The title
    Text txtResults = new Text("Results go Here - Cash $200.0");


    private Image[] loadImages()
    {
        Image[] imgs = new Image[MAX_ICONS];
        for(int i=0;i<imgs.length;i++)
        {
            imgs[i]=new Image(getClass().getResourceAsStream(String.format("img/%d.jpg",i+1)));
        }
        return imgs;
    }

    private void showAlert(String sText) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Message");
        alert.setHeaderText("End Game");
        alert.setContentText(sText);
        alert.showAndWait();
    }
    @Override
    public void start(Stage stage) throws Exception {
//The overall border pane
        BorderPane obPane = new BorderPane();
        //Space bar to spin
        obPane.addEventFilter(KeyEvent.KEY_RELEASED, event->{
            if (event.getCode() == KeyCode.SPACE) {
            }});
        //The machine pane at center
        Pane obTop = new Pane();
        obPane.setCenter(obTop);

        //The bet pane at right
        VBox obRight = new VBox(10);
        obRight.setAlignment(Pos.CENTER_LEFT);
        obRight.setPadding(new Insets(5,20,0,20));
        obPane.setRight(obRight);

        //the button pane at bottom
        HBox obBottom = new HBox(20);
        obBottom.setAlignment(Pos.BASELINE_CENTER);
        obBottom.setPadding(new Insets(5,20,5,20));
        obBottom.setAlignment(Pos.CENTER);
        obPane.setBottom(obBottom);

        //The title is in machine pane
        txtResults.setFont(Font.font("playbill",FontWeight.BOLD, FontPosture.ITALIC, 25));
        txtResults.setFill(Color.GOLD);

        //Make sure machine pane and bet pane have correct size
        obTop.prefWidthProperty().bind(stage.widthProperty().multiply(0.78));
        obRight.prefWidthProperty().bind(stage.widthProperty().multiply(0.22));

        //Set the slot machine back ground image
        BackgroundImage myBI= new BackgroundImage(new Image(getClass().getResourceAsStream("/img/background.jpg"),687,640,false,true),
                BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT);
        obTop.setBackground(new Background(myBI));

        //Init two buttons
        ImageView imgSpin = new ImageView(new Image(getClass().getResourceAsStream("/img/spin.png")));
        imgSpin.setFitHeight(100);
        imgSpin.setFitWidth(100);
        Button cmdSpin = new Button("Spin",imgSpin);

        ImageView imgCash = new ImageView(new Image(getClass().getResourceAsStream("/img/cash.png")));
        imgCash.setFitHeight(100);
        imgCash.setFitWidth(100);
        Button cmdCash = new Button("Cash Out",imgCash);
        obBottom.getChildren().addAll(cmdSpin, cmdCash);

        //Init the slots
        imgSource = loadImages();
        imgSlot1.setImage(imgSource[0]);
        imgSlot2.setImage(imgSource[1]);
        imgSlot3.setImage(imgSource[2]);
        //We use Pane so we will set the exact pos for each slot and title
        imgSlot1.setX(50);
        imgSlot1.setY(347);
        imgSlot1.setFitHeight(209);
        imgSlot1.setFitWidth(170);
        imgSlot2.setX(260);
        imgSlot2.setY(347);
        imgSlot2.setFitHeight(209);
        imgSlot2.setFitWidth(170);
        imgSlot3.setX(467);
        imgSlot3.setY(347);
        imgSlot3.setFitHeight(209);
        imgSlot3.setFitWidth(170);
        txtResults.setX(260);
        txtResults.setY(310);
        obTop.getChildren().addAll(imgSlot1,imgSlot2,imgSlot3,txtResults);

        //Regular stuff
        Scene scene = new Scene(obPane, 887, 750);
        stage.setTitle("CST Slot Machine"); // Set the stage title
        stage.setScene(scene); // Place the scene in the stage
        stage.show(); // Display the stage
    }
}

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
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
import javafx.util.Duration;

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

    private RadioButton[] lstBet = new  RadioButton[7];
    final static String[] lstBetCap = {"$1","$2","$5","$10","$20","$50","$100"};

    private double balance = 200.0;

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
                spin();
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

        cmdSpin.setOnAction(e->spin());

        ImageView imgCash = new ImageView(new Image(getClass().getResourceAsStream("/img/cash.png")));
        imgCash.setFitHeight(100);
        imgCash.setFitWidth(100);
        Button cmdCash = new Button("Cash Out",imgCash);
        cmdCash.setOnAction(e->cashout());
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


        //Bet pane
        ToggleGroup group = new ToggleGroup();
        for(int i=0;i<lstBet.length;i++)
        {
            lstBet[i] = new RadioButton(lstBetCap[i]);
            lstBet[i].setToggleGroup(group);
        }
        lstBet[0].setSelected(true);
        obRight.getChildren().addAll(lstBet);


        //Regular stuff
        Scene scene = new Scene(obPane, 887, 750);
        stage.setTitle("CST Slot Machine"); // Set the stage title
        stage.setScene(scene); // Place the scene in the stage
        stage.show(); // Display the stage
    }


    //TODo: 1. Complete the bet choice window Hint: Using array of radio buttons

    //Todo: 2. Complete the spin process - Each slot need to random display 10 images. Each image should show in the slow for 0.5 seconds
    // When the animation of all slots are completed, the last images in each slot will be used to calc the win/loss
    // Three same images , pay out the bet user chose. otherwise deduct the best amount from user's balance(200 to start)
    int getCurImageIndex(ImageView imageView)
    {
        Image image = imageView.getImage();
        for(int i=0;i<imgSource.length;i++)
            if(imgSource[i]==image) return i;
        return -1;
    }

    void setImage(ImageView imageView, int index)
    {
        imageView.setImage(imgSource[index]);
    }

    void rotateImage(ImageView imageView)
    {
        int curImage = getCurImageIndex(imageView);
        int newImage = (int)(Math.random()*MAX_ICONS);
        while(newImage==curImage) newImage = (int)(Math.random()*MAX_ICONS);
        setImage(imageView,newImage);
    }

    private void spin()
    {
        if(balance<getBet())
        {
            showAlert(String.format("You have $%3.1f left. It is no enough for the bet.", balance));
            return;
        }
        enableBet(false);
        Timeline timeline = new Timeline(new KeyFrame(Duration.millis(100),
                e->{
                    rotateImage(imgSlot1);
                    rotateImage(imgSlot2);
                    rotateImage(imgSlot3);
                }));
        timeline.setCycleCount(10);
        timeline.setOnFinished(e->check());
        timeline.play();
    }

    private int getBet()
    {
        for(RadioButton btn:lstBet)
        {
            if(btn.isSelected())
            {
                String cap = btn.getText();
                cap=cap.substring(1);
                return Integer.parseInt(cap);
            }
        }
        return 0;
    }

    private void enableBet(boolean enable)
    {
        for(RadioButton btn:lstBet)
        {
            btn.setDisable(!enable);
        }
    }


    private void check()
    {
        int index1 = getCurImageIndex(imgSlot1);
        int index2 = getCurImageIndex(imgSlot2);
        int index3 = getCurImageIndex(imgSlot3);

        boolean bWin;
        if(index1==index2 && index3==index2)
        {
            //Win
            bWin = true;
            balance+= getBet();
        }
        else
        {
            //Lost
            bWin=false;
            balance-= getBet();
        }
        String sRs=String.format("%s - Cash: $%3.1f", bWin?"You Win":"You Lost",balance);
        txtResults.setText(sRs);
        enableBet(true);
    }



    //Todo:3. when cash out button clicked, show the last balance(showAlert) and exit app

    private void cashout()
    {
        String str = String.format("You have $%3.1f left.Bye.",balance);
        showAlert(str);
        System.exit(0);
    }

    //Todo:4. if user's has no money left, show a message(showAlert) and exit app

    //Please submit your project to Mid-term 2 Sim before 11:59 PM today!



}

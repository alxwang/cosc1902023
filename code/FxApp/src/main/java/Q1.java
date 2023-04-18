import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.function.BiConsumer;

public class Q1 extends Application
{

    private ScheduledExecutorService scheduler = new ScheduledThreadPoolExecutor(1);
    private BorderPane obBorder;
    private HBox boxDice, boxResults, boxControls;
    private VBox boxBets;
    private RadioButton[] aBets;
    private ImageView die1,die2;
    private Image[] aFaces;

    private final String[] sTitles = { "2", "3,4,10,11", "5,9", "6,8", "7", "12"};
    private final double[] dWinnings = { 10, 4, 4.8, 4.4, 3.6, 10};
    private Label lblResults;
    private Button cmdButton, cashOutButton;
    private Double balance = 20D;
    public int nPort = 11212;



    @Override
    public void start(Stage obStage) throws Exception
    {
        obBorder = new BorderPane();
        estFaces();
        estDisplay();
        estBottom();
        estTop();
        estRight();

        obBorder.setTop(boxResults);
        obBorder.setCenter(boxDice);
        obBorder.setBottom(boxControls);
        obBorder.setRight(boxBets);

        obStage.setScene(new Scene(obBorder, 400, 300));
        obStage.setTitle("CST Dice");
        obStage.show();





    }

    /**
     * Helper routine for setting the center displays for the dice.  This does not have
     * to be modified.
     */
    public void estDisplay()
    {
        boxDice = new HBox();
        boxDice.setAlignment(Pos.CENTER);
        boxDice.setPadding(new Insets(10));
        boxDice.setSpacing(25);

        die1 = new ImageView(aFaces[0]);
        die2 = new ImageView(aFaces[1]);

        boxDice.getChildren().addAll(die1,die2);



    }

    /**
     * Helper routine for establishing the top HBox - Does not need to be changed.
     */
    public void estTop()
    {
        boxResults = new HBox();
        boxResults.setAlignment(Pos.CENTER);
        boxResults.setPadding(new Insets(10));
        boxResults.setSpacing(25);
        boxResults.setStyle("-fx-border-color : green");

        lblResults = new Label("Results go here");
        lblResults.setFont(Font.font("Bookman OldStyle", FontWeight.BOLD,
                FontPosture.ITALIC, 20));
        boxResults.getChildren().add(lblResults);




    }


    /**
     * Helper routine for establishing the allowed die faces. This should not be modified.
     */
    public void estFaces() throws FileNotFoundException {
        aFaces = new Image[6];
        for (int i =1; i <=6; i++)
        {
            //Redirected to resources folder, i'm not sure where it was pointing before, but it didn't work.
            aFaces[i-1] = new Image( new FileInputStream("img/die_" + i + ".png"));

        }
    }


    /**
     * Helper routine for setting up Bottom HBox - This will have to be modified
     *
     */
    public void estBottom()
    {
        boxControls = new HBox();
        boxControls.setPadding(new Insets(10));
        boxControls.setAlignment(Pos.CENTER);
        boxControls.setStyle("-fx-border-color: green");

        cmdButton = new Button("Roll Dice");
        cashOutButton = new Button("Cash Out");
        boxControls.getChildren().add(cmdButton);
        boxControls.getChildren().add(cashOutButton);

        cmdButton.setOnAction(e -> {
            balance -= 2;
            rollDice((i1, i2) -> {
                RadioButton selected = Arrays.stream(aBets).filter(ToggleButton::isSelected).findFirst().get();
                List<String> aGuesses = Arrays.asList(selected.getText().split(","));
                List<Integer> aIntGuesses = new ArrayList<>();
                aGuesses.forEach(str -> aIntGuesses.add(Integer.parseInt(str)));
                Platform.runLater(() -> {
                    if(checkRoll(i1, i2, aIntGuesses)){
                        for(int i = 0; i < aBets.length; i++){
                            if(aBets[i] == selected) balance += dWinnings[i];
                        }
                        lblResults.setText(String.format("You Won - $%.2f", balance));
                    }else{
                        lblResults.setText(String.format("You Lost - $%.2f", balance));
                    }
                    if(balance - 2 <= 0){
                        Platform.runLater(() -> detailPayout(balance));
                    }
                });
            });
        });

        cashOutButton.setOnAction(e -> {
            detailPayout(balance);
        });
    }

    public void estRight()
    {
        aBets = new RadioButton[sTitles.length];
        boxBets = new VBox();
        boxBets.setPadding(new Insets(10));
        boxBets.setStyle("-fx-border-color: green");
        Label lblPlaceBet = new Label("Place Bet");
        lblPlaceBet.setPadding(new Insets(0, 0, 10, 0));
        lblPlaceBet.setFont(Font.font("Bookman OldStyle", FontWeight.BOLD, FontPosture.REGULAR, 12));
        boxBets.getChildren().add(lblPlaceBet);

        ToggleGroup group = new ToggleGroup();
        int i = 0;
        for(String rbName : sTitles){
            RadioButton rb = new RadioButton(rbName);
            rb.setPadding(new Insets(0, 0, 10, 0));
            rb.setToggleGroup(group);
            aBets[i++] = rb;
            boxBets.getChildren().add(rb);
        }
        aBets[0].setSelected(true);
    }

    int diceRollCount = 0;
    public void rollDice(BiConsumer<Integer, Integer> onceComplete){
        //Attempted timeline for a good 30 minutes, couldn't get both images to roll at the same time.

        cmdButton.setDisable(true);
        for(RadioButton rb : aBets){
            rb.setDisable(true);
        }

        int rand1 = (int)(Math.random()*10+10), rand2 = (int)(Math.random()*10+10);
        ScheduledFuture<?> future = scheduler.scheduleAtFixedRate(() -> {
            if(diceRollCount < rand1){
                die1.setImage(aFaces[(int)(Math.random()*aFaces.length)]);
            }
            if(diceRollCount < rand2){
                die2.setImage(aFaces[(int)(Math.random()*aFaces.length)]);
            }
            diceRollCount++;
        }, 0, 500, TimeUnit.MILLISECONDS);
        scheduler.schedule(() -> {
            future.cancel(true);
            cmdButton.setDisable(false);
            for(RadioButton rb : aBets){
                rb.setDisable(false);
            }
            int indexOfFileExtension = die1.getImage().getUrl().indexOf(".");
            onceComplete.accept(
                    Integer.valueOf(die1.getImage().getUrl().substring(indexOfFileExtension-1, indexOfFileExtension)),
                    Integer.valueOf(die2.getImage().getUrl().substring(indexOfFileExtension-1, indexOfFileExtension))
            );
//			onceComplete.accept(3,2);
            diceRollCount = 0;
        }, (Math.max(rand1, rand2)) * 500L + 50, TimeUnit.MILLISECONDS);
    }

    private boolean checkRoll(int i1, int i2, List<Integer> equals){
        return equals.stream().anyMatch(i -> i == i1 + i2);
    }

    /*
     * To be completed by the user.  Hint you should probably put a delay of about 500 MS at
     * the end of this to allow the information to be transmitted.
     */
    public void detailPayout(double dValue)
    {
        cmdButton.setDisable(true);
        cashOutButton.setDisable(true);
        for(RadioButton rb : aBets){
            rb.setDisable(true);
        }

        Thread thread = new Thread(() -> {
            BetInfo betInfo = new BetInfo("Cayden", dValue);

            try {
                Socket server = new Socket("10.28.110.62", nPort);
                ObjectOutputStream out = new ObjectOutputStream(server.getOutputStream());
                //Warning: BetInfo's getTotal is always 0 to the server.
                out.writeObject(betInfo);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        thread.start();


    }

}

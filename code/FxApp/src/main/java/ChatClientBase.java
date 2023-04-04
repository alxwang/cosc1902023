import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public abstract class ChatClientBase extends Application {
    protected abstract String getName();
    private  Socket socket = null;
    private DataInputStream dataInputStream = null;
    private DataOutputStream dataOutputStream = null;
    @Override
    public void start(Stage stage) throws Exception {
        BorderPane paneForText = new BorderPane();
        paneForText.setPadding(new Insets(5,5,5,5));
        paneForText.setLeft(new Label("Enter a message:"));
        TextField tf = new TextField();
        tf.setAlignment(Pos.CENTER_LEFT);
        paneForText.setCenter(tf);



        BorderPane pane = new BorderPane();
        TextArea ta = new TextArea();
        ta.setPrefColumnCount(80);
        ta.setEditable(false);
        pane.setCenter(new ScrollPane(ta));
        pane.setBottom(paneForText);

        MenuBar menuBar = new MenuBar();
        Menu menuFile = new Menu("File");
        Menu menuHelp = new Menu("Help");
        menuBar.getMenus().addAll(menuFile,menuHelp);
        pane.setTop(menuBar);

        MenuItem menuItemConnect = new MenuItem("Connect");
        MenuItem menuItemExit = new MenuItem("Exit");
        menuFile.getItems().addAll(menuItemConnect,menuItemExit);
        MenuItem menuItemAbout = new MenuItem("About");
        menuHelp.getItems().add(menuItemAbout);

        menuItemExit.setAccelerator(KeyCombination.keyCombination("Ctrl+X"));

        menuItemConnect.setOnAction(e->{
            new Thread(()->{
                try {
                    socket=new Socket("localhost", ChatServer.PORT);
                    dataOutputStream=new DataOutputStream(socket.getOutputStream());
                    dataInputStream=new DataInputStream(socket.getInputStream());
                    dataOutputStream.writeUTF(getName());
                    while(true)
                    {
                        String msg = dataInputStream.readUTF();
                        Platform.runLater(()->{
                            ta.appendText(msg+"\n");
                        });
                    }
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }

            }).start();
        });

        tf.setOnAction(e->{
            String msg = tf.getText().trim();
            try {
                dataOutputStream.writeUTF(msg);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
            ta.appendText(String.format("%"+ta.getPrefColumnCount()+"s",
                    "You: "+msg+"\n"));
            tf.setText("");
            tf.requestFocus();
        });

        Scene scene = new Scene(pane, 450, 200);
        stage.setTitle("Client"+" - "+getName()); // Set the stage title
        stage.setScene(scene); // Place the scene in the stage

        stage.setOnCloseRequest(e->{
            try {
                dataOutputStream.writeUTF(MountPoint.BYE);
                dataOutputStream.flush();
                socket.close();
                socket=null;
                dataOutputStream=null;
                dataInputStream=null;
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
            finally {
                Platform.exit();
                System.exit(0);
            }

        });

        Image image = new Image(getClass().
                getResourceAsStream("/img/clear.png"));
        ImageView iv = new ImageView(image);
        iv.setFitWidth(10);
        iv.setFitHeight(10);
        ContextMenu contextMenu = new ContextMenu();
        MenuItem menuItemClear= new MenuItem("Clear",iv);
        contextMenu.getItems().add(menuItemClear);
        menuItemClear.setOnAction(e->ta.clear());
        pane.setOnMousePressed(e->contextMenu.show(ta,e.getScreenX(),e.getScreenY()));

        stage.show(); // Display the stage
        tf.requestFocus();
    }
}
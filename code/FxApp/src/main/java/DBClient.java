import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class DBClient extends Application {
    ObjectOutputStream out;
    ObjectInputStream in;
    GridPane gridPane = new GridPane();

    void loadYear(String year) throws IOException, ClassNotFoundException {
        Integer y = Integer.parseInt(year);
        out.writeObject(y);
        List<AirCraft> list = (List<AirCraft>)in.readObject();
        gridPane.getChildren().clear();
        gridPane.add(new Label("Name"),0,0);
        gridPane.add(new Label("Country"),1,0);
        gridPane.add(new Label("Year"),2,0);
        int r = 1;
        for(AirCraft a: list)
        {
            gridPane.add(new Label(a.getName()),0,r);
            gridPane.add(new Label(a.getCountry()),1,r);
            gridPane.add(new Label(String.format("%d",a.getYear())),2,r);
            r++;
        }

    }


    @Override
    public void start(Stage stage) throws Exception {
        BorderPane pane = new BorderPane();
        MenuBar menuBar = new MenuBar();
        pane.setTop(menuBar);
        Menu menuYear = new Menu("Year");
        Menu menuCountry = new Menu("Country");
        menuBar.getMenus().addAll(menuYear,menuCountry);


        gridPane.setPadding(new Insets(5,5,5,5));
        gridPane.setHgap(5.5);
        gridPane.setVgap(5.5);
        gridPane.setAlignment(Pos.CENTER);
        pane.setCenter(gridPane);

        Label label = new Label("Country Count");
        label.setStyle("-fx-background-color: green");
        StackPane stackPane = new StackPane();
        stackPane.getChildren().add(label);
        stackPane.setStyle("-fx-background-color: green");
        pane.setBottom(stackPane);

        Scene scene = new Scene(pane,450,400);
        stage.setTitle("Aircrafts");
        stage.setScene(scene);
        stage.show();

        new Thread(()->{
            try {
                Socket socket = new Socket("localhost",10002);
                out = new ObjectOutputStream(socket.getOutputStream());
                in = new ObjectInputStream(socket.getInputStream());
                Integer i = -1;
                out.writeObject(i);
                List<Integer> years = (List<Integer>)in.readObject();
                for(int year:years)
                {
                    Platform.runLater(()->{
                        MenuItem item = new MenuItem(String.format("%d",year));
                        menuYear.getItems().add(item);
                        item.setOnAction(e->{
                            try {
                                loadYear(((MenuItem)e.getSource()).getText());
                            } catch (IOException ex) {
                                throw new RuntimeException(ex);
                            } catch (ClassNotFoundException ex) {
                                throw new RuntimeException(ex);
                            }
                        });
                    });
                }
                i = 0;
                out.writeObject(i);
                String temp = (String) in.readObject();
                List<String > info = Arrays.asList(temp.split(",")).stream()
                        .map(s->{
                            String[] sl = s.split(":");
                            return sl[0] + " -> "+ sl[1];
                        })
                        .collect(Collectors.toList());

                Timeline timeline = new Timeline(new KeyFrame(Duration.millis(500),
                        e->label.setText(info.get((int)(Math.random()*info.size())))));


//                Map<String,Integer> info = Arrays.asList(temp.split(",")).stream()
//                        .map(s->s.split(":"))
//                        .collect(Collectors.toMap(e->e[0],e->Integer.parseInt(e[1])));
//
//                Timeline timeline = new Timeline(new KeyFrame(Duration.millis(500),
//                        e->{
//                            List<String> keys = new ArrayList<>(info.keySet());
//                            String randomKey = keys.get((int)(Math.random()*keys.size()));
//                            int ramdomVal = info.get(randomKey);
//                            label.setText(String.format("%s -> %d",randomKey,ramdomVal));
//
//                        }));
                timeline.setCycleCount(Timeline.INDEFINITE);
                timeline.play();
            }
            catch (UnknownHostException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        }).start();

    }
}

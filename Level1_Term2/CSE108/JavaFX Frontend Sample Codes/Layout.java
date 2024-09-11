package com.example.demo;

import java.util.Random;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class Layout extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    Stage window;

    @Override
    public void start(Stage primaryStage) throws Exception{
        window = primaryStage;
        window.setTitle("Harry Potter");

        HBox top = new HBox(10);
        Button button1 = new Button("Potter");
        Button button2 = new Button("Granger");
        Button button3 = new Button("Weasley");
        top.setAlignment(Pos.CENTER);
        top.getChildren().addAll(button1, button2, button3);

        VBox left = new VBox(10);
        Button button4 = new Button("Fred");
        Button button5 = new Button("George");
        Button button6 = new Button("Charlie");
        //left.setAlignment(Pos.CENTER);
        left.getChildren().addAll(button4, button5, button6);

        BorderPane borderPane = new BorderPane();
        borderPane.setLeft(left);
        borderPane.setTop(top);

        Scene scene1 = new Scene(borderPane, 500, 300);
        window.setScene(scene1);
        window.show();
    }
}

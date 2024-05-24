package com.example.demo;

import java.util.Random;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class Testmain extends Application{

    Button button1, button2;
    Stage window;
    Scene scene1, scene2;
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        primaryStage.setTitle("Goblet of Fire");
        window=primaryStage;
        Label label1= new Label("Welcome to the first scene");
        button1= new Button("Go to scene 2");
        button1.setOnAction(e->window.setScene(scene2));

        Button button3= new Button("Close");
        button3.setOnAction(e->window.close());

        VBox layout1 = new VBox(20);
        layout1.getChildren().addAll(button1, button3);
        scene1 = new Scene(layout1, 400, 200);

        button2 = new Button("Go back to scene 1");
        button2.setOnAction(e->window.setScene(scene1));

        VBox layout2 = new VBox();
        layout2.getChildren().addAll(button2, label1);
        scene2=new Scene(layout2, 600, 400);

        window.setScene(scene1);
        window.show();
    }//start
}//class
package com.example.demo;

import java.util.Random;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class ProperClose extends Application{

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
        window.setOnCloseRequest(e->{
            e.consume();
            closeProgram();
        });

        Button button3= new Button("Close");
        button3.setOnAction(e->closeProgram());

        VBox layout1 = new VBox(20);
        layout1.getChildren().addAll(button3);
        layout1.setAlignment(Pos.CENTER);
        scene1 = new Scene(layout1, 400, 200);

        window.setScene(scene1);
        window.show();
    }//start

    private void closeProgram(){
        //System.out.println("File is Saved!");
        boolean result = ConfirmBox.display("ConfirmBox", "Are you sure you want to close this Program?");
        if(result){
            System.out.println("Good Day!");
            window.close();
        }
        else System.out.println("Welcome Back!");
    }
}//class
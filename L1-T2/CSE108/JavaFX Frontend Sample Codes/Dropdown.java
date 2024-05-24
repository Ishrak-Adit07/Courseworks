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
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class Dropdown extends Application{
    Stage window;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        primaryStage.setTitle("Goblet of Fire");
        window=primaryStage;

        ChoiceBox<String> wandCore = new ChoiceBox<>();
        wandCore.getItems().addAll("Unicorn Hair", "Dragon Heartstring", "Phoenix Feathers", "Hippogriph Feathers");
        wandCore.setValue("Wand Core");
        //For any special effects for selecting a choicebox option
        wandCore.getSelectionModel().selectedItemProperty().addListener((v, oldvalue, newvalue)->System.out.println(oldvalue));

        Button submit =new Button("Submit");
        submit.setOnAction(e->System.out.println(handleChoice(wandCore)));

        VBox layout1 = new VBox(10);
        layout1.getChildren().addAll(wandCore, submit);

        Scene scene1 = new Scene(layout1, 400, 250);
        window.setScene(scene1);
        window.show();
    }//start

    private String handleChoice(ChoiceBox<String > wandCore){
        if(wandCore.getValue().equals("Wand Core")){
            return "Select one!";
        }
        return wandCore.getValue();
    }
}//class

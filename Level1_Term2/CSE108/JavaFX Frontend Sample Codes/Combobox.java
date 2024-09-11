package com.example.demo;

import java.util.Random;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class Combobox extends Application {
    Stage window;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Combo Box");
        window = primaryStage;

        ComboBox<String> Movies = new ComboBox<>();
        //Movies.setValue("Select a Movie");
        Movies.setPromptText("Select a Movie");
        Movies.getItems().addAll("Star wars", "Good Will Hunting", "Forest Gump", "Shawshank Redemption", "Inception");
        Movies.setEditable(true);//User can create an option
        Movies.setOnAction(e->System.out.println(Movies.getValue()));//Operation on selection

        Button submit = new Button("Submit");
        submit.setOnAction(e->System.out.println(Movies.getValue()));

        Button close = new Button("Close");
        close.setOnAction(e -> window.close());

        VBox layout1 = new VBox(10);
        layout1.setPadding(new Insets(10, 50, 20, 20));
        layout1.getChildren().addAll(Movies, submit, close);

        Scene scene1 = new Scene(layout1, 400, 250);
        window.setScene(scene1);
        window.show();
    }
}
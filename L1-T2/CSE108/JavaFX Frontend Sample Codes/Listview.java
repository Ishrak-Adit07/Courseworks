package com.example.demo;

import java.util.Observer;
import java.util.Random;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class Listview extends Application{
    Stage window;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("List View");
        window = primaryStage;

        ListView<String> Movies = new ListView<>();
        Movies.getItems().addAll("Star wars", "Good Will Hunting", "Forest Gump", "Titanic", "Dark Night Triology", "Hunger Games", "Shawshank Redemption", "Inception");
        Movies.setEditable(true);//User can create an option
        Movies.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        //Hold down ctrl to select multiple
        //Hold down shift to select a range

        Button submit = new Button("Submit");
        submit.setOnAction(e->System.out.println(handleListView(Movies)));

        Button close = new Button("Close");
        close.setOnAction(e -> window.close());

        VBox layout1 = new VBox(10);
        layout1.setPadding(new Insets(10, 50, 20, 20));
        layout1.getChildren().addAll(Movies, submit, close);

        Scene scene1 = new Scene(layout1, 400, 250);
        window.setScene(scene1);
        window.show();
    }

    private String handleListView(ListView<String> Movies){
        String message = "Top movies: \n";
        ObservableList<String> movieList = Movies.getSelectionModel().getSelectedItems();

        for(String m : movieList){
            message += m+"\n";
        }
        return message;
    }
}

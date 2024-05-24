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
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class AlertBox {
    public static void display(String title, String message){
        Stage window = new Stage();

        window.setTitle(title);
        window.setMinWidth(250);
        window.initModality(Modality.APPLICATION_MODAL); //No other window can can be accessed while this is open

        Label label1 = new Label();
        label1.setText("Petrificus Totalus");
        Button closebutton = new Button("Click to go back");
        closebutton.setOnAction(e->window.close());

        VBox layout1 = new VBox(25);
        layout1.getChildren().addAll(label1, closebutton);
        layout1.setAlignment(Pos.CENTER);

        Scene scene1 = new Scene(layout1, 200, 400);
        window.setScene(scene1);

        window.showAndWait();
    }
}

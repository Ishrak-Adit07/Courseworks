package com.example.demo;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.CheckBox;
import org.controlsfx.control.spreadsheet.Grid;
import javafx.scene.control.TextField;

public class Checkbox extends Application{
    public static void main(String[] args) {
        launch(args);
    }

    Stage window;

    @Override
    public void start(Stage primaryStage){
        window=primaryStage;
        window.setTitle("CheckBoxes");

        Label sub = new Label("Options for sub: ");
        Label space = new Label(" ");

        CheckBox chicken = new CheckBox("Chicken");
        CheckBox beef = new CheckBox("Beef");
        CheckBox tuna = new CheckBox("Tuna");
        CheckBox cheese = new CheckBox("Cheese");
        CheckBox fries = new CheckBox("Fries");
        CheckBox spice = new CheckBox("Spice");
        cheese.setSelected(true);
        fries.setSelected(true);

        Button order = new Button("Order now!");
        order.setOnAction(e->System.out.println(handleOptions(chicken, beef, tuna, spice, cheese, fries)));

        VBox layout1= new VBox(8);
        layout1.setPadding(new Insets(10, 50, 20, 20));
        //layout1.setAlignment(Pos.BASELINE_CENTER);
        layout1.getChildren().addAll(sub, chicken, beef, tuna,  spice, cheese, fries, order);

        Scene scene1 = new Scene(layout1, 500, 250);
        window.setScene(scene1);
        window.show();
    }

    public String handleOptions(CheckBox chicken, CheckBox beef, CheckBox tuna, CheckBox spice, CheckBox cheese, CheckBox fries){
        String message = "User choices: \n";
        if(chicken.isSelected()){
            message+=chicken.getText()+"\n";
        }
        if(beef.isSelected()){
            message+=beef.getText()+"\n";
        }
        if(tuna.isSelected()){
            message+=tuna.getText()+"\n";
        }
        if(spice.isSelected()){
            message+=spice.getText()+"\n";
        }
        if(cheese.isSelected()){
            message+=cheese.getText()+"\n";
        }
        if(fries.isSelected()){
            message+=fries.getText()+"\n";
        }
        return message;
    }
}

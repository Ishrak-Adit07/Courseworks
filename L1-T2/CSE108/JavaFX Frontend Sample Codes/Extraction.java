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
import org.controlsfx.control.spreadsheet.Grid;
import javafx.scene.control.TextField;

public class Extraction extends Application{
    public static void main(String[] args) {
        launch(args);
    }

    Stage window;

    @Override
    public void start(Stage primaryStage) throws Exception{
        window = primaryStage;
        window.setTitle("Extraction");

        Label label1 = new Label("User age: ");

        TextField nameInput = new TextField();
        Button login = new Button("Log in");
        login.setOnAction(e->loginAction(nameInput));

        VBox layout1 = new VBox(20);
        layout1.setPadding(new Insets(20, 70, 20, 20));
        layout1.getChildren().addAll(label1, nameInput, login);

        Scene scene1 = new Scene(layout1, 400, 200);
        window.setScene(scene1);
        window.show();
    }

    public void loginAction(TextField nameInput){
        try{
            int age = Integer.parseInt(nameInput.getText());
            System.out.println("User is "+ age);
            window.close();
        }
        catch (NumberFormatException e){
            System.out.println(nameInput.getText()+ " is not a valid input");
        }
    }

}

package com.example.demo;

import java.util.Random;
import java.util.zip.GZIPInputStream;

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

public class Gridbox extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    Stage window;

    @Override
    public void start(Stage primaryStage) throws Exception{
        window = primaryStage;
        window.setTitle("GridPane");

        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(15, 15, 15, 15));
        gridPane.setVgap(10);
        gridPane.setHgap(10);

        Label label1= new Label("Username: ");
        GridPane.setConstraints(label1, 0, 0);

        TextField nameInput = new TextField("Ishrak Adit");
        nameInput.setPromptText("Username");
        GridPane.setConstraints(nameInput, 1, 0);

        Label label2= new Label("Password");
        GridPane.setConstraints(label2, 0, 1);

        TextField passInput = new TextField();
        passInput.setPromptText("password");
        GridPane.setConstraints(passInput, 1, 1);

        Button login = new Button("Log in");
        login.setOnAction(e->window.close());
        GridPane.setConstraints(login, 1 ,2);

        gridPane.getChildren().addAll(label1, nameInput, label2, passInput, login);

        Scene scene1 = new Scene(gridPane, 500, 300);
        window.setScene(scene1);

        window.show();
    }
}
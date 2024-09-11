package com.example.demo;

import java.util.Observer;
import java.util.Random;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class menuClass extends Application{
    public static void main(String[] args) {
        launch(args);
    }

    Stage window;

    @Override
    public void start(Stage primaryStage){
        window = primaryStage;
        window.setTitle("Program for Menu");

        Button close  = new Button("Close");
        close.setOnAction(e->window.close());

        //Menu Bar
        MenuBar menuBar = new MenuBar();

        //Menus
        Menu fileMenu = new Menu("_File");
        Menu editMenu = new Menu("_Edit");
        Menu viewMenu = new Menu("_View");
        Menu codeMenu = new Menu("C_ode");
        //Whatever you put underscore before -> Alt+that key will take you to that menu

        //File menu items
        MenuItem newFile = new MenuItem("_New");
        //To select menuItem with Keyboard -> Alt+key1+key2
        newFile.setOnAction(e->{
            boolean result = ConfirmBox.display("New File", "Do you want to open new file?");
            if(result) System.out.println("New File Created");
            else System.out.println("New File Not Created");
        });
        MenuItem openFile = new MenuItem("_Open");
        MenuItem settingsFile = new MenuItem("_Settings");
        MenuItem saveFile = new MenuItem("Sa_ve");
        saveFile.setDisable(true);//Disables the item
        MenuItem exitFile = new MenuItem("_Exit");

        //new MenuItem("title") for menu items
        //new SeparatorMenuItem() for a separator
        fileMenu.getItems().addAll(newFile, openFile, saveFile, new SeparatorMenuItem(), settingsFile, new SeparatorMenuItem(), exitFile);
        editMenu.getItems().addAll(new MenuItem("Cut"), new MenuItem("Copy"), new MenuItem("Delete..."), new MenuItem("Paste"));
        viewMenu.getItems().addAll(new MenuItem("Tools"), new MenuItem("Appearance"), new SeparatorMenuItem(), new MenuItem("Recent Files"));
        codeMenu.getItems().addAll(new MenuItem("Override"), new MenuItem("Implement"), new MenuItem("Delegate methods..."));

        //Adding to menu bar
        menuBar.getMenus().addAll(fileMenu, editMenu, viewMenu, codeMenu);
        BorderPane layout1 = new BorderPane();
        layout1.setTop(menuBar);

        HBox bottom = new HBox(20);
        bottom.setPadding(new Insets(10, 20, 20, 10));
        bottom.setAlignment(Pos.BOTTOM_RIGHT);
        bottom.getChildren().addAll(close);
        layout1.setBottom(bottom);

        Scene scene1 = new Scene(layout1, 500, 300);
        window.setScene(scene1);
        window.show();
    }
}

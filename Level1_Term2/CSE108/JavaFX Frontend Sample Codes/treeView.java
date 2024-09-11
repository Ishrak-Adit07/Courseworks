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
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class treeView extends Application{
    Stage window;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("List View");
        window = primaryStage;

        Button submit = new Button("Submit");

        Button close = new Button("Close");
        close.setOnAction(e -> window.close());

        TreeView tree;

        TreeItem<String> root, Potter, Weasley;

        root = new TreeItem<>("Wizards");
        root.setExpanded(true);

        Potter=makeBranch("Potter", root);
        makeBranch("Cloak", Potter);
        makeBranch("WandCore", Potter);
        makeBranch("Bogart", Potter);

        Weasley=makeBranch("Weasley", root);
        makeBranch("Chess", Weasley);
        makeBranch("WandCore", Weasley);
        makeBranch("Bogart", Weasley);

        tree = new TreeView<>(root);
        tree.setShowRoot(false);

        tree.getSelectionModel().selectedItemProperty()
                .addListener((v, oldValue, newValue) -> {
                        if(newValue != null){
                        System.out.println(newValue);//?????
                        }
                });


        VBox layout1 = new VBox(10);
        layout1.setPadding(new Insets(10, 150, 20, 20));
        layout1.getChildren().addAll(tree, submit, close);

        Scene scene1 = new Scene(layout1, 400, 250);
        window.setScene(scene1);
        window.show();
    }

    public TreeItem<String> makeBranch(String name, TreeItem<String> parent){
        TreeItem<String> item = new TreeItem<>(name);
        //item.setExpanded(true);
        parent.getChildren().add(item);
        return item;
    }
}

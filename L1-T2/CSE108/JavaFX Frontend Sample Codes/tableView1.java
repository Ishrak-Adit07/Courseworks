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

public class tableView1 extends Application {
    Stage window;
    TableView<Product> table;

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

        Button add = new Button("Add");
        Button delete = new Button("Delete");

        //Table Columns
        TableColumn<Product, String> nameColumn = new TableColumn<>("Name");
        nameColumn.setMinWidth(100);
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("Name"));

        TableColumn<Product, Double> priceColumn = new TableColumn<>("Price");
        priceColumn.setMinWidth(100);
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("Price"));

        TableColumn<Product, Integer> quantityColumn = new TableColumn<>("Quantity");
        quantityColumn.setMinWidth(100);
        quantityColumn.setCellValueFactory(new PropertyValueFactory<>("Quantity"));

        //Input Fields for Adding Product
        TextField nameInput = new TextField();
        nameInput.setPromptText("Product name");
        TextField priceInput = new TextField();
        priceInput.setPromptText("Product price");
        TextField quantityInput = new TextField();
        quantityInput.setPromptText("Quantity");

        //Action for add and delete button
        add.setOnAction(e->table.getItems().add(addProduct(nameInput, priceInput, quantityInput)));
        delete.setOnAction(e->deleteProduct());

        table = new TableView<>();
        table.setMaxHeight(150);
        table.setItems(getProducts());
        table.getColumns().addAll(nameColumn, quantityColumn, priceColumn);
        //For multiple selection
        table.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        VBox layout1 = new VBox(10);
        layout1.setPadding(new Insets(10, 50, 20, 20));
        layout1.getChildren().addAll(table, nameInput, priceInput, quantityInput);

        HBox layout2 = new HBox(15);
        layout2.setPadding(new Insets(10, 30, 20, 20));
        layout2.setAlignment(Pos.BOTTOM_RIGHT);
        layout2.getChildren().addAll(add, delete, close);

        BorderPane bp = new BorderPane();
        bp.setTop(layout1);
        bp.setRight(layout2);

        Scene scene1 = new Scene(bp, 400, 350);
        window.setScene(scene1);
        window.show();
    }

    public ObservableList<Product> getProducts() {
        ObservableList<Product> products = FXCollections.observableArrayList();
        products.addAll(new Product("Laptop", 500, 20),
                new Product("Desktop", 800, 15),
                new Product("Keyboard", 50, 100),
                new Product("Notebook", 600, 10));

        return products;
    }

    public Product addProduct(TextField name, TextField price, TextField quantity){
        Product product = new Product();
        product.setName(name.getText());
        product.setPrice(Double.parseDouble(price.getText()));
        product.setQuantity(Integer.parseInt(quantity.getText()));
        name.clear();
        price.clear();
        quantity.clear();
        return product;
    }

    public void deleteProduct(){
        ObservableList<Product> allItems, selectedItems;
        allItems = table. getItems();
        selectedItems = table.getSelectionModel().getSelectedItems();

        selectedItems.forEach(allItems :: remove);
    }
}

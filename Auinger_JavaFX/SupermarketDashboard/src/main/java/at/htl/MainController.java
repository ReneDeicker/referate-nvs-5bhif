package at.htl;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class MainController implements Initializable {

    private String chosenStore;
    private int chosenStoreId;
    private DatabaseViewController dController;
    private ProductController pController;

    @FXML
    private Button storeBtnMeixner;
    @FXML
    private Button storeBtnEuro;
    @FXML
    private Button storeBtnHbf;
    @FXML
    private Button storeBtnBuffet;
    @FXML
    private Button productCrudBtn;
    @FXML
    private Button cashiersBtn;
    @FXML
    private Button storesBtn;
    @FXML
    private Button customersBtn;
    @FXML
    private Button activitiesBtn;
    @FXML
    private Button productsBtn;
    @FXML
    private Button closeBtn;

    private double xOffset = 0;
    private double yOffset = 0;

    @FXML
    private Label selectedStore;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){
        //DEFAULT - INIT
        chooseStore("BILLA Meixnerkreuzung", 1);

        //set listeners
        //store buttons
        storeBtnMeixner.setOnAction(e -> {
            chooseStore("BILLA Meixnerkreuzung", 1);
        });
        storeBtnEuro.setOnAction(e -> {
            chooseStore("EUROSPAR Leonding", 2);
        });
        storeBtnHbf.setOnAction(e -> {
            chooseStore("SPAR Hauptbahnhof", 3);
        });
        storeBtnBuffet.setOnAction(e -> {
            chooseStore("HTL Leonding Buffet", 4);
        });
        //crud button
        productCrudBtn.setOnAction(e -> {
            setupStage("product.fxml");
            pController.initController(chosenStoreId);
        });

        //database View Buttons
        cashiersBtn.setOnAction(e -> {
            chooseDatabaseView("database.fxml", "Cashier");
        });
        storesBtn.setOnAction(e -> {
            chooseDatabaseView("database.fxml", "Store");
        });
        customersBtn.setOnAction(e -> {
            chooseDatabaseView("database.fxml", "Customer");
        });
        activitiesBtn.setOnAction(e -> {
            chooseDatabaseView("database.fxml", "Activity");
        });
        productsBtn.setOnAction(e -> {
            chooseDatabaseView("database.fxml", "Product");
        });

        //close button
        closeBtn.setOnAction(e -> {
            Node source = (Node)  e.getSource();
            Stage stage  = (Stage) source.getScene().getWindow();
            stage.close();
        });
    }

    private void chooseStore(String text, int id){
        selectedStore.setText(text);
        chosenStore = text;
        chosenStoreId = id;
    }

    private void chooseDatabaseView(String file, String table){
        setupStage(file);
        try {
            dController.initData(table, chosenStore);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        dController.displayData();
    }

    public void setupStage(String fxmlString){
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(fxmlString));
        Parent root = null;
        try {
            root = fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if(fxmlString.equals("product.fxml")) {
            pController = fxmlLoader.getController();
        } else if(fxmlString.equals("database.fxml")) {
            dController = fxmlLoader.getController();
        }
        Stage stage = new Stage();
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.setScene(new Scene(root));

        //grab your root here
        root.setOnMousePressed(event1 -> {
            xOffset = event1.getSceneX();
            yOffset = event1.getSceneY();
        });

        //move around here
        root.setOnMouseDragged(event1 -> {
            stage.setX(event1.getScreenX() - xOffset);
            stage.setY(event1.getScreenY() - yOffset);
        });

        stage.show();
    }
}

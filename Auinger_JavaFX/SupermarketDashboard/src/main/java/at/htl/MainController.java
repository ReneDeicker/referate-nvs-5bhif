package at.htl;

import at.htl.model.Product;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
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
    DatabaseViewController dController;
    ProductController pController;

    private double xOffset = 0;
    private double yOffset = 0;

    @FXML
    private Label selectedStore;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){
        selectedStore.setText("BILLA Meixnerkreuzung");
        chosenStore = "BILLA Meixnerkreuzung";
        chosenStoreId = 1;
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

    @FXML
    private void onCloseBtn(ActionEvent event){
        Node source = (Node)  event.getSource();
        Stage stage  = (Stage) source.getScene().getWindow();
        stage.close(); //Vorhang zu
    }

    @FXML
    private void onCashierBtn(ActionEvent event) throws IOException {
        setupStage("database.fxml");
        try {
            dController.initData("Cashier", chosenStore);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        dController.displayData();
    }

    @FXML
    private void onProductBtn(ActionEvent event){
        setupStage("database.fxml");
        try {
            dController.initData("Product", chosenStore);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        dController.displayData();
    }

    @FXML
    private void onCustomerBtn(ActionEvent event){
        setupStage("database.fxml");
        try {
            dController.initData("Customer", chosenStore);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        dController.displayData();
    }

    @FXML
    private void onActivityBtn(ActionEvent event){
        setupStage("database.fxml");
        try {
            dController.initData("Activity", chosenStore);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        dController.displayData();
    }

    @FXML
    private void onStoreBtn(ActionEvent event){
        setupStage("database.fxml");
        try {
            dController.initData("Store", chosenStore);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        dController.displayData();
    }

    @FXML
    private void onCrudButton(ActionEvent event){
        setupStage("product.fxml");
        pController.initConnection(chosenStoreId);
    }

    @FXML
    private void chooseHbf(ActionEvent event){
        selectedStore.setText("SPAR Hauptbahnhof");
        chosenStore = "SPAR Hauptbahnhof";
        chosenStoreId = 3;
    }

    @FXML
    private void chooseBuffet(ActionEvent event){
        selectedStore.setText("HTL Leonding Buffet");
        chosenStore = "HTL-Leonding Buffet";
        chosenStoreId = 4;
    }

    @FXML
    private void chooseMeixner(ActionEvent event){
        selectedStore.setText("BILLA Meixnerkreuzung");
        chosenStore = "BILLA Meixnerkreuzung";
        chosenStoreId = 1;
    }

    @FXML
    private void chooseEuro(ActionEvent event){
        selectedStore.setText("EUROSPAR Leonding");
        chosenStore = "EUROSPAR Leonding";
        chosenStoreId = 2;
    }
}

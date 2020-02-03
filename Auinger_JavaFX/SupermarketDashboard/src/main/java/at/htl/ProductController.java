package at.htl;

import at.htl.db.PostgresConnectionClass;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.Console;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

public class ProductController implements Initializable {
    @FXML
    private TextField id;
    @FXML
    private DatePicker best_before_date;
    @FXML
    private TextField brand;
    @FXML
    private TextField name;
    @FXML
    private TextField price;
    @FXML
    private TextField quantity;

    private int chosenStoreId;

    private Connection connection;
    PreparedStatement statement;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        id.textProperty().addListener((obs, oldText, newText) -> {
            // event Listener with regex
            if (!newText.matches("\\d*")) {
                id.setText(newText.replaceAll("[^\\d]", ""));
            }

            if(!id.getText().equals("")){
                try {
                    statement = connection.prepareStatement("SELECT COUNT(*) FROM PRODUCT WHERE ID = ?");
                    statement.setInt(1, Integer.parseInt(id.getText()));
                    ResultSet rs = statement.executeQuery();
                    rs.next();
                    System.out.println(rs.getInt("count"));
                    if(rs.getInt("count") > 0) {
                        statement = connection.prepareStatement("SELECT *  FROM PRODUCT WHERE id = ?");
                        statement.setInt(1, Integer.parseInt(id.getText()));
                        rs = statement.executeQuery();
                        rs.next();
                        best_before_date.setValue(LocalDate.parse(rs.getDate("best_before_date").toString(), formatter));
                        name.setText(rs.getString("name"));
                        brand.setText(rs.getString("brand"));
                        price.setText(Double.toString(rs.getDouble("price")));
                        quantity.setText(Integer.toString(rs.getInt("quantity")));
                    } else {
                        clearTextFields();
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            } else{
                clearTextFields();
            }
        });
    }

    public void clearTextFields(){
        best_before_date.setValue(null);
        name.setText("");
        brand.setText("");
        price.setText("");
        quantity.setText("");
    }

    public void initConnection(int store){
        PostgresConnectionClass connectionClass = new PostgresConnectionClass();
        connection = connectionClass.getConnection();
        chosenStoreId = store;
    }

    @FXML
    private void onCloseBtn(ActionEvent event){
        Node source = (Node)  event.getSource();
        Stage stage  = (Stage) source.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void onUpdateBtn(ActionEvent event){
        System.out.println("Update");
        String dateString = LocalDate.now().toString();
        if(best_before_date.getValue() != null){
            dateString = best_before_date.getValue().toString();
        }
        try {
            statement = connection.prepareStatement("UPDATE PRODUCT SET best_before_date=TO_DATE('" + dateString + "', 'yyyy-MM-dd'),brand='" + brand.getText() +
                    "',name='" + name.getText() + "',price=" + Double.parseDouble(price.getText()) + ",quantity=" + Integer.parseInt(quantity.getText()) +
                    " where id = " + Integer.parseInt(id.getText()) + ";");
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void onCreateBtn(ActionEvent event){
        System.out.println("Create");
        String dateString = LocalDate.now().toString();
        if(best_before_date.getValue() != null){
            dateString = best_before_date.getValue().toString();
        }
        try {
            statement = connection.prepareStatement("SELECT MAX(id) FROM PRODUCT");
            ResultSet rs = statement.executeQuery();
            rs.next();
            int maxId = rs.getInt("max") + 1;
            statement = connection.prepareStatement("INSERT INTO PRODUCT VALUES (" + maxId + ",TO_DATE('" + dateString + "', 'yyyy-MM-dd'),'" + brand.getText() +
                            "','" + name.getText() + "'," + Double.parseDouble(price.getText()) + "," + Integer.parseInt(quantity.getText()) +
                            "," + chosenStoreId + ");");
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void onDeleteBtn(ActionEvent event){
        System.out.println("Delete");
        try {
            statement = connection.prepareStatement("SELECT COUNT(*) FROM ACTIVITY WHERE PRODUCT_ID = ?");
            statement.setInt(1, Integer.parseInt(id.getText()));
            ResultSet rs = statement.executeQuery();
            rs.next();
            System.out.println(rs.getInt("count"));
            if(rs.getInt("count") > 0){
                statement = connection.prepareStatement("DELETE FROM ACTIVITY WHERE product_id = ?");
                statement.setInt(1, Integer.parseInt(id.getText()));
                statement.executeQuery();
            }
            statement = connection.prepareStatement("SELECT COUNT(*) FROM PRODUCT WHERE ID = ?");
            statement.setInt(1, Integer.parseInt(id.getText()));
            rs = statement.executeQuery();
            rs.next();
            System.out.println(rs.getInt("count"));
            if(rs.getInt("count") > 0){
                statement = connection.prepareStatement("DELETE FROM PRODUCT WHERE id = ?");
                statement.setInt(1, Integer.parseInt(id.getText()));
                statement.execute();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

package at.htl;

import at.htl.db.ActivityRepository;
import at.htl.db.PostgresConnectionClass;
import at.htl.db.ProductRepository;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
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
    @FXML
    private Button closeBtn;
    @FXML
    private Button createBtn;
    @FXML
    private Button updateBtn;
    @FXML
    private Button deleteBtn;

    private int chosenStoreId;

    ProductRepository productRepository = new ProductRepository();
    ActivityRepository activityRepository = new ActivityRepository();

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
                    int inputId = Integer.parseInt(id.getText());
                    int count = productRepository.getCountById(inputId);
                    if(count > 0) {
                        ResultSet resultSet = productRepository.getById(inputId);
                        best_before_date.setValue(LocalDate.parse(resultSet.getDate("best_before_date").toString(), formatter));
                        name.setText(resultSet.getString("name"));
                        brand.setText(resultSet.getString("brand"));
                        price.setText(Double.toString(resultSet.getDouble("price")));
                        quantity.setText(Integer.toString(resultSet.getInt("quantity")));
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
        closeBtn.setOnAction(e -> {
            Node source = (Node)  e.getSource();
            Stage stage  = (Stage) source.getScene().getWindow();
            stage.close();
        });
        createBtn.setOnAction(e -> {
            onCreateBtn();
        });
        updateBtn.setOnAction(e -> {
            onUpdateBtn();
        });
        deleteBtn.setOnAction(e -> {
            onDeleteBtn();
        });
    }

    public void clearTextFields(){
        best_before_date.setValue(null);
        name.setText("");
        brand.setText("");
        price.setText("");
        quantity.setText("");
    }

    public void initController(int store){
        chosenStoreId = store;
    }

    private void onUpdateBtn(){
        System.out.println("Update");
        String dateString = LocalDate.now().toString();
        if(best_before_date.getValue() != null){
            dateString = best_before_date.getValue().toString();
        }
        productRepository.update(dateString,
                brand.getText(),
                name.getText(),
                Double.parseDouble(price.getText()),
                Integer.parseInt(quantity.getText()),
                Integer.parseInt(id.getText()));
    }

    private void onCreateBtn(){
        System.out.println("Create");
        String dateString = LocalDate.now().toString();
        if(best_before_date.getValue() != null){
            dateString = best_before_date.getValue().toString();
        }
        int countId = productRepository.getMaxId();
        productRepository.create(dateString,
                brand.getText(),
                name.getText(),
                Double.parseDouble(price.getText()),
                Integer.parseInt(quantity.getText()),
                countId,
                chosenStoreId);
    }

    private void onDeleteBtn(){
        System.out.println("Delete");
        int inputId = Integer.parseInt(id.getText());
        int count = activityRepository.getCountByProductId(inputId);
        if(count > 0){
            activityRepository.deleteByProductId(Integer.parseInt(id.getText()));
        }
        count = productRepository.getCountById(Integer.parseInt(id.getText()));
        if(count > 0){
            productRepository.delete(Integer.parseInt(id.getText()));
        }
    }
}

package at.htl;

import at.htl.db.PostgresConnectionClass;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class DatabaseViewController implements Initializable {

    ResultSet resultSet;
    PreparedStatement statement;

    @FXML
    private TableView databaseTableView;
    @FXML
    private Label headerLabel;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){
    }

    public void initData(String type, String chosenStore) throws SQLException {
        PostgresConnectionClass connectionClass = new PostgresConnectionClass();
        Connection connection = connectionClass.getConnection();

        if(type.equals("Customer") || type.equals("Cashier")){
            statement = connection.prepareStatement("SELECT * FROM " + type  + " x " +
                    "JOIN STORE s ON x.store_id = s.id JOIN PERSON p ON p.id = x.id where s.name = ? ");
            statement.setString(1, chosenStore);
        }
        else if(type.equals("Store")){
            statement = connection.prepareStatement("SELECT * FROM STORE");
        }else {
            statement = connection.prepareStatement("SELECT * FROM " + type + " x JOIN STORE s ON x.store_id = s.id where s.name = ?");
            statement.setString(1, chosenStore);
        }
        resultSet = statement.executeQuery();
    }

    public void displayData(){
        try{
            ObservableList<ObservableList> data = FXCollections.observableArrayList();
            /**********************************
             * TABLE COLUMN ADDED DYNAMICALLY *
             **********************************/
            for(int i=0 ; i<resultSet.getMetaData().getColumnCount(); i++){
                //We are using non property style for making dynamic table
                final int j = i;
                TableColumn col = new TableColumn(resultSet.getMetaData().getColumnName(i+1));
                col.setCellValueFactory((Callback<TableColumn.CellDataFeatures<ObservableList, String>, ObservableValue<String>>) param ->
                        new SimpleStringProperty(param.getValue().get(j).toString()));

                databaseTableView.getColumns().addAll(col);
                System.out.println("Column ["+i+"] ");
            }

            /********************************
             * Data added to ObservableList *
             ********************************/
            while(resultSet.next()){
                //Iterate Row
                ObservableList<String> row = FXCollections.observableArrayList();
                for(int i=1 ; i<=resultSet.getMetaData().getColumnCount(); i++){
                    //Iterate Column
                    row.add(resultSet.getString(i));
                }
                System.out.println("Row [1] added "+row );
                data.add(row);
            }

            //FINALLY ADDED TO TableView
            databaseTableView.setItems(data);
        }catch(Exception e){
            e.printStackTrace();
            System.out.println("Error on Building Data");
        }
    }

    @FXML
    private void onCloseBtn(ActionEvent event){
        Node source = (Node)  event.getSource();
        Stage stage  = (Stage) source.getScene().getWindow();
        stage.close();
    }
}

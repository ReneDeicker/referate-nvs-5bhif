package at.htl.db;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CashierRepository extends DbRepository {
    public ResultSet getAll(){
        try {
            statement = connection.prepareStatement("SELECT * FROM CASHIER");
            resultSet = statement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultSet;
    }

    public ResultSet getByStore(String storeName){
        try {
            statement = connection.prepareStatement("SELECT * FROM CASHIER c " +
                    "JOIN STORE s ON c.store_id = s.id JOIN PERSON p ON p.id = c.id where s.name = ? ");
            statement.setString(1, storeName);
            resultSet = statement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultSet;
    }
}

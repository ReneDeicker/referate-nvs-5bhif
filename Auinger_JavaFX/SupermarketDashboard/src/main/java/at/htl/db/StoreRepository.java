package at.htl.db;

import java.sql.ResultSet;
import java.sql.SQLException;

public class StoreRepository extends DbRepository {
    public ResultSet getAll(){
        try {
            statement = connection.prepareStatement("SELECT * FROM STORE");
            resultSet = statement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultSet;
    }
}

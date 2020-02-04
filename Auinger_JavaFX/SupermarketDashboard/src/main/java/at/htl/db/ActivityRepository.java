package at.htl.db;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ActivityRepository extends DbRepository {

    public ResultSet getAll(){
        try {
            statement = connection.prepareStatement("SELECT * FROM ACTIVITY");
            resultSet = statement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultSet;
    }

    public ResultSet getByStore(String storeName){
        try {
            statement = connection.prepareStatement("SELECT * FROM ACTIVITY a " +
                    "JOIN STORE s ON a.store_id = s.id where s.name = ?");
            statement.setString(1, storeName);
            resultSet = statement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultSet;
    }

    public int getCountByProductId(int id){
        int count = 0;
        try {
            statement = connection.prepareStatement("SELECT COUNT(*) FROM ACTIVITY WHERE PRODUCT_ID = ?");
            statement.setInt(1, id);
            resultSet = statement.executeQuery();
            resultSet.next();
            count = resultSet.getInt("count");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return count;
    }

    public void deleteByProductId(int id){
        try {
            statement = connection.prepareStatement("DELETE FROM ACTIVITY WHERE product_id = ?");
            statement.setInt(1, id);
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

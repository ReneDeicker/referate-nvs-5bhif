package at.htl.db;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ProductRepository extends DbRepository {
    public ResultSet getAll(){
        try {
            statement = connection.prepareStatement("SELECT * FROM PRODUCT");
            resultSet = statement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultSet;
    }

    public ResultSet getByStore(String storeName){
        try {
            statement = connection.prepareStatement("SELECT * FROM PRODUCT p JOIN STORE s ON p.store_id = s.id where s.name = ?");
            statement.setString(1, storeName);
            resultSet = statement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultSet;
    }

    public int getCountById(int id){
        int count = 0;
        try {
            statement = connection.prepareStatement("SELECT COUNT(*) FROM PRODUCT WHERE ID = ?");
            statement.setInt(1, id);
            resultSet = statement.executeQuery();
            resultSet.next();
            count = resultSet.getInt("count");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return count;
    }

    public ResultSet getById(int id){
        try {
            statement = connection.prepareStatement("SELECT *  FROM PRODUCT WHERE id = ?");
            statement.setInt(1, id);
            resultSet = statement.executeQuery();
            resultSet.next();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultSet;
    }

    public void create(String date, String name, String brand, double price, int quantity, int maxId, int chosenStoreId){
        try {
            statement = connection.prepareStatement("INSERT INTO PRODUCT VALUES (" +
                    maxId +
                    ",TO_DATE('" + date + "', 'yyyy-MM-dd'),'" +
                    brand +
                    "','" + name +
                    "'," + price +
                    "," + quantity +
                    "," + chosenStoreId + ");");
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(int id){
        try {
            statement = connection.prepareStatement("DELETE FROM PRODUCT WHERE id = ?");
            statement.setInt(1, id);
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void update(String date, String name, String brand, double price, int quantity, int id){
        try {
            statement = connection.prepareStatement("UPDATE PRODUCT SET " +
                    "best_before_date=TO_DATE('" + date + "', 'yyyy-MM-dd')," +
                    "brand='" + brand +
                    "',name='" + name +
                    "',price=" + price +
                    ",quantity=" + quantity+
                    " where id = " + id + ";");
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public int getMaxId(){
        int maxId = 0;
        try {
            statement = connection.prepareStatement("SELECT MAX(id) FROM PRODUCT");
            ResultSet rs = statement.executeQuery();
            rs.next();
            maxId = rs.getInt("max") + 1;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return maxId;
    }
}

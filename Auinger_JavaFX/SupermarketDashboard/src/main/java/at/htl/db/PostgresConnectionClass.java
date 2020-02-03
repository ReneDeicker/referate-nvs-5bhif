package at.htl.db;

import java.sql.Connection;
import java.sql.DriverManager;

public class PostgresConnectionClass {
    private Connection connection;
    public Connection getConnection(){
        String dbName="postgres";
        String userName="postgres";
        String password="passme";

        try {
            connection = DriverManager.getConnection("jdbc:postgresql://192.168.99.100:5432/"+dbName, userName, password);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return connection;
    }
}

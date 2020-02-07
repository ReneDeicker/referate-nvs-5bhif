package at.htl.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DbRepository {
    public PreparedStatement statement;
    public Connection connection;
    public ResultSet resultSet;

    public DbRepository() {
        PostgresConnectionClass connectionClass = new PostgresConnectionClass();
        connection = connectionClass.getConnection();
    }
}

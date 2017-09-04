package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataBaseConnector {
    private static final String DATABASE_URL = "jdbc:mysql://localhost:3306/initdb?useSSL=false";
    private static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    private static final String USER = "root";
    private static final String PASSWORD = "root";
    private Connection connection = null;

    public DataBaseConnector() {
        try {
            Class.forName(JDBC_DRIVER);
            connection = DriverManager.getConnection(DATABASE_URL, USER, PASSWORD);
        } catch (ClassNotFoundException e) {
            System.out.println("Unable to load class");
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("Unable to load driver class");
            e.printStackTrace();
        }

    }

    public Connection getConnection() {
        return connection;
    }

    public void closeConnection() {
        if(connection!=null){
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

}

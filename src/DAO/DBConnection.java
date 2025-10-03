package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

    public static Connection getConnection() throws SQLException{
        String url = "jdbc:mysql://127.0.0.1:3306/student_manager";
        String username = "root";
        String password = "Nonplusmedic2018!";
        return DriverManager.getConnection(url, username, password);
    }
}
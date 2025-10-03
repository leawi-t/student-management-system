package main;

import DAO.DBConnection;
import DAO.DBConnectionError;
import view.MainGui;
import java.sql.Connection;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        try {
            Connection connection = DBConnection.getConnection();
            new MainGui();
        }
        catch(SQLException e){
            new DBConnectionError();
        }
    }
}
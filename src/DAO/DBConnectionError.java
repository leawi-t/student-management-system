package DAO;
import javax.swing.*;

public class DBConnectionError {
    public DBConnectionError(){
        errorMessage();
    }
    public void errorMessage(){
        JOptionPane.showMessageDialog(null, "Unable to connect to the database", "ERROR MESSAGE", JOptionPane.ERROR_MESSAGE);
    }
}

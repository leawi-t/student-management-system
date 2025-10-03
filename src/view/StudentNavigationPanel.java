package view;

import javax.swing.*;
import java.awt.*;

public class StudentNavigationPanel extends JPanel {

    MainGui mainGui;
    JButton displayButton = new JButton("Display all Students");
    JButton addButton = new JButton("Add new Student");
    JButton updateButton = new JButton("Update Student info");
    JButton searchButton = new JButton("Search for Student");
    JButton deleteButton = new JButton("Delete Student info");

    StudentNavigationPanel(MainGui mainGui){

        this.mainGui = mainGui;

        displayButton.addActionListener(e -> {
            mainGui.studentDisplay.refreshTable(1);
            mainGui.mainCards.show(mainGui.centerPanel, "display Student");
        });

        addButton.addActionListener(e->{
            mainGui.mainCards.show(mainGui.centerPanel, "add Student");
        });

        updateButton.addActionListener(e->{
            mainGui.mainCards.show(mainGui.centerPanel, "update Student");
        });

        searchButton.addActionListener(e->{
            mainGui.mainCards.show(mainGui.centerPanel, "search Student");
        });

        deleteButton.addActionListener(e->{
            mainGui.mainCards.show(mainGui.centerPanel, "delete Student");
        });

        this.setLayout(new GridLayout(5,1));
        this.setBackground(Color.CYAN);
        this.add(displayButton);
        this.add(addButton);
        this.add(updateButton);
        this.add(searchButton);
        this.add(deleteButton);
    }
}

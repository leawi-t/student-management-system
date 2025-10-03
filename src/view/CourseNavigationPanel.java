package view;

import javax.swing.*;
import java.awt.*;

public class CourseNavigationPanel extends JPanel {

    JButton displayButton = new JButton("Display all Courses");
    JButton addButton = new JButton("Add new Course");
    JButton updateButton = new JButton("Update Course info");
    JButton searchButton = new JButton("Search for Course");
    JButton deleteButton = new JButton("Delete Course info");

    CourseNavigationPanel(MainGui mainGui){

        displayButton.addActionListener(e -> {
            mainGui.courseDisplay.refreshTable(2);
            mainGui.mainCards.show(mainGui.centerPanel, "display Course");
        });

        addButton.addActionListener(e->{
            mainGui.mainCards.show(mainGui.centerPanel, "add Course");
        });

        updateButton.addActionListener(e->{
            mainGui.mainCards.show(mainGui.centerPanel, "update Course");
        });

        searchButton.addActionListener(e->{
            mainGui.mainCards.show(mainGui.centerPanel, "search Course");
        });

        deleteButton.addActionListener(e->{
            mainGui.mainCards.show(mainGui.centerPanel, "delete Course");
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

package view;

import javax.swing.*;
import java.awt.*;

public class RecordNavigationPanel extends JPanel{

    JButton displayButton = new JButton("Display all Course Records");
    JButton addButton = new JButton("Add new Course Record");
    JButton updateButton = new JButton("Update Course grade");
    JButton searchButton = new JButton("Search for Course Record");
    JButton deleteButton = new JButton("Delete Course Record");
    JButton gpaButton = new JButton("Calculate GPA of Student");

    RecordNavigationPanel(MainGui mainGui){

        displayButton.addActionListener(e -> {
            mainGui.courseRecordDisplay.refreshTable(3);
            mainGui.mainCards.show(mainGui.centerPanel, "display CourseRecord");
        });

        addButton.addActionListener(e->{
            mainGui.mainCards.show(mainGui.centerPanel, "add CourseRecord");
        });

        updateButton.addActionListener(e->{
            mainGui.mainCards.show(mainGui.centerPanel, "update CourseRecord");
        });

        searchButton.addActionListener(e->{
            mainGui.mainCards.show(mainGui.centerPanel, "search CourseRecord");
        });

        deleteButton.addActionListener(e->{
            mainGui.mainCards.show(mainGui.centerPanel, "delete CourseRecord");
        });

        gpaButton.addActionListener(e->{
            mainGui.mainCards.show(mainGui.centerPanel, "calculate GPA");
        });

        this.setLayout(new GridLayout(6,1));
        this.setBackground(Color.CYAN);
        this.add(displayButton);
        this.add(addButton);
        this.add(updateButton);
        this.add(searchButton);
        this.add(deleteButton);
        this.add(gpaButton);
    }
}

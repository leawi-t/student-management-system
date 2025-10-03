package view;

import Controller.CourseRecordManager;
import Module.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DeleteCourseRecordGui extends JPanel implements ActionListener {

    private final JPanel northPanel = new JPanel();

    private final JPanel titlePanel = new JPanel();
    private final JLabel title = new JLabel("Delete Course Record Info");

    private final JPanel filterPanel = new JPanel();
    private final JLabel studentIdLabel = new JLabel("Enter the student id: ");
    private final JTextField studentIdField = new JTextField(20);
    private final JLabel courseIdLabel = new JLabel("Enter the course id: ");
    private final JTextField courseIdField = new JTextField(20);

    private final JPanel buttonPanel = new JPanel();
    private final JButton searchButton = new JButton("Search");
    private final JButton clearButton = new JButton("Clear");
    private final JButton deleteButton = new JButton("Delete");

    private final CourseRecordManager manager = new CourseRecordManager();

    DeleteCourseRecordGui(){

        this.setLayout(new BorderLayout());

        northPanel.setLayout(new BorderLayout());

        title.setFont(new Font("Arial", Font.BOLD, 16));
        titlePanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        titlePanel.add(title);

        filterPanel.setLayout(new GridBagLayout());
        filterPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createTitledBorder("Search Filters"),
                BorderFactory.createEmptyBorder(10, 20, 10, 20)
        ));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;
        int row = 0;

        searchButton.addActionListener(this);
        clearButton.addActionListener(this);
        deleteButton.addActionListener(this);

        buttonPanel.add(searchButton);
        buttonPanel.add(clearButton);
        buttonPanel.add(deleteButton);

        addField(gbc,row++,studentIdLabel, studentIdField);
        addField(gbc,row++,courseIdLabel, courseIdField);

        northPanel.add(titlePanel, BorderLayout.NORTH);
        northPanel.add(filterPanel, BorderLayout.CENTER);
        northPanel.add(buttonPanel, BorderLayout.SOUTH);
        this.add(northPanel, BorderLayout.NORTH);
    }

    private void addField(GridBagConstraints gbc, int y, JLabel label, Component input) {
        gbc.gridx = 0;
        gbc.gridy = y;
        filterPanel.add(label, gbc);
        gbc.gridx = 1;
        filterPanel.add(input, gbc);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==clearButton){
            studentIdField.setText("");
            courseIdField.setText("");
        }
        else if (e.getSource()==searchButton){
            if(!(studentIdField.getText().isEmpty() || courseIdField.getText().isEmpty())) {
                displayInfo();
            }
            else{
                JOptionPane.showMessageDialog(null, "Please enter all the fields");
            }
        }
        else{
            manager.deleteCourseRecord(studentIdField.getText(), courseIdField.getText());
        }
    }

    private void displayInfo(){
        CourseRecord courseRecord = manager.getCourseRecord(studentIdField.getText(), courseIdField.getText());
        if(courseRecord ==null){
            JOptionPane.showMessageDialog(null, "Unable to find Course Record");
        }
        else{
            JTextArea ta = new JTextArea(courseRecord.displayInfo());
            ta.setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createTitledBorder("Course Info"),
                    BorderFactory.createEmptyBorder(10, 10, 10, 10)
            ));
            refreshResults(ta);
        }
    }

    private void refreshResults(Component result) {
        // Remove old result components
        for (Component comp : getComponents()) {
            if (comp instanceof JTextArea) remove(comp);
        }
        this.add(result, BorderLayout.CENTER);
        this.revalidate();
        this.repaint();
    }
}
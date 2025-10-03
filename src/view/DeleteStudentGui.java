package view;

import Controller.StudentManager;
import Module.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DeleteStudentGui extends JPanel implements ActionListener {

    private final JPanel northPanel = new JPanel();

    private final JPanel titlePanel = new JPanel();
    private final JLabel title = new JLabel("Delete Student info");

    private final JPanel filterPanel = new JPanel();
    private final JLabel inputLabel = new JLabel("Enter the id: ");
    private final JTextField inputField = new JTextField(20);

    private final JPanel buttonPanel = new JPanel();
    private final JButton searchButton = new JButton("Search");
    private final JButton clearButton = new JButton("Clear");
    private final JButton deleteButton = new JButton("Delete");

    private final StudentManager manager = new StudentManager();

    DeleteStudentGui(){

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

        addField(gbc,row++,inputLabel, inputField);

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
            inputField.setText("");
        }
        else if (e.getSource()==searchButton){
            if(!inputField.getText().isEmpty()) {
                displayInfo();
            }
            else{
                JOptionPane.showMessageDialog(null, "Please enter all the fields");
            }
        }
        else{
            manager.deleteStudent(inputField.getText());
        }
    }

    private void displayInfo(){
        Student student = manager.getStudentById(inputField.getText());
        if(student==null){
            JOptionPane.showMessageDialog(null, "Unable to find Student");
        }
        else{
            JTextArea ta = new JTextArea(student.displayInfo());
            ta.setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createTitledBorder("Student Info"),
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

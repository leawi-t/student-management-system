package view;

import Controller.StudentManager;
import Module.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class SearchStudentGui extends JPanel implements ActionListener {

    JPanel northPanel = new JPanel();
    JPanel titlePanel = new JPanel();
    JLabel title = new JLabel("Search Student");
    JPanel filterPanel = new JPanel();
    JLabel filterLabel = new JLabel("Choose the filter");
    JComboBox<String> searchFilters;
    JLabel inputLabel = new JLabel("Enter your input: ");
    JTextField inputField = new JTextField(20);
    JPanel buttonPanel = new JPanel();
    JButton searchButton = new JButton("Search");
    JButton clearButton = new JButton("Clear");

    StudentManager manager = new StudentManager();

    JTable table;
    DefaultTableModel model;
    JScrollPane scroll;

    List<Student> students;
    String[] columns = {"Id", "Name", "gender", "email", "phone Num", "Date-Of-Birth", "department", "year"};
    String[][] data;

    SearchStudentGui(){

        this.setLayout(new BorderLayout());

        northPanel.setLayout(new BorderLayout());

        title.setFont(new Font("Arial", Font.BOLD, 16));
        titlePanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        titlePanel.add(title);

        String[] filters = {"Id", "First Name", "Last Name", "Department", "Gender", "Year"};
        searchFilters = new JComboBox<>(filters);

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

        buttonPanel.add(searchButton);
        buttonPanel.add(clearButton);

        addField(gbc,row++,filterLabel,searchFilters);
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
            searchFilters.setSelectedIndex(0);
            BorderLayout layout = (BorderLayout) this.getLayout();
            Component center = layout.getLayoutComponent(BorderLayout.CENTER);
            if (center != null) this.remove(center);
        }
        else{
            if(!inputField.getText().isEmpty()) {
                String filter = (String) searchFilters.getSelectedItem();
                assert filter != null;
                createTable(filter);
            }
            else{
                JOptionPane.showMessageDialog(null, "Please enter all the fields");
            }
        }
    }

    private void createTable(String filter){
        if(!filter.equalsIgnoreCase("Id")) {
            if (filter.equalsIgnoreCase("First Name")) {
                students = manager.getStudentByFName(inputField.getText());
                populateTable(students);
            }

            else if (filter.equalsIgnoreCase("Last Name")) {
                students = manager.getStudentByLName(inputField.getText());
                populateTable(students);
            }

            else if (filter.equalsIgnoreCase("Department")) {
                students = manager.getStudentByDepartment(inputField.getText());
                populateTable(students);
            }

            else if (filter.equalsIgnoreCase("Year")) {
                try {
                    int year = Integer.parseInt(inputField.getText());
                    students = manager.getStudentByYear(year);
                    populateTable(students);
                } catch (NumberFormatException nfe) {
                    JOptionPane.showMessageDialog(this, "Please enter a valid year number.");
                }
            }

            else if (filter.equalsIgnoreCase("Gender")) {
                students = manager.getStudentByGender(inputField.getText());
                populateTable(students);
            }
        }
        else{
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
    }

    private void populateTable(List<Student> studentsList) {
        data = new String[studentsList.size()][8];
        for (int i = 0; i < studentsList.size(); i++) {
            Student s = studentsList.get(i);
            data[i][0] = s.getId();
            data[i][1] = s.getName();
            data[i][2] = s.getGender();
            data[i][3] = s.getEmail();
            data[i][4] = s.getlName();
            data[i][5] = String.valueOf(s.getDateOfBirth());
            data[i][6] = s.getDepartment();
            data[i][7] = String.valueOf(s.getYear());
        }

        model = new DefaultTableModel(data, columns) {
            @Override public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        table = new JTable(model);
        table.setFillsViewportHeight(true);

        scroll = new JScrollPane(table);
        scroll.setBorder(BorderFactory.createEmptyBorder(10, 30, 10, 30));

        refreshResults(scroll);
    }

    private void refreshResults(Component result) {
        // Remove whatever is in the CENTER of the BorderLayout
        BorderLayout layout = (BorderLayout) this.getLayout();
        Component center = layout.getLayoutComponent(BorderLayout.CENTER);
        if (center != null) {
            this.remove(center);
        }

        this.add(result, BorderLayout.CENTER);
        this.revalidate();
        this.repaint();
    }
}

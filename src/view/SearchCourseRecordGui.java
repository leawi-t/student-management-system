package view;

import Controller.CourseRecordManager;
import Module.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class SearchCourseRecordGui extends JPanel implements ActionListener {

    JPanel northPanel = new JPanel();
    JPanel titlePanel = new JPanel();
    JLabel title = new JLabel("Search Course Record");
    JPanel filterPanel = new JPanel();
    JLabel filterLabel = new JLabel("Choose the filter");
    JComboBox<String> searchFilters;
    JLabel inputLabel = new JLabel("Enter the id: ");
    JTextField inputField = new JTextField(20);
    JPanel buttonPanel = new JPanel();
    JButton searchButton = new JButton("Search");
    JButton clearButton = new JButton("Clear");

    CourseRecordManager manager = new CourseRecordManager();

    JTable table;
    DefaultTableModel model;
    JScrollPane scroll;

    List<CourseRecord> courseRecords;
    String[] columns = {"Student Id", "Student Name", "Course Id", "Course Name", "Grade", "Credit Hours", "department"};
    String[][] data;

    SearchCourseRecordGui(){

        this.setLayout(new BorderLayout());

        northPanel.setLayout(new BorderLayout());

        title.setFont(new Font("Arial", Font.BOLD, 16));
        titlePanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        titlePanel.add(title);

        String[] filters = {"Student Id", "Student Name", "Course Id", "Course Name", "Credit Hours", "department"};
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
            inputLabel.setText("Enter the id: ");
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

        if (filter.equalsIgnoreCase("Student Id")) {
            courseRecords = manager.getRecordsByStudentId(inputField.getText());
            inputLabel.setText("Enter the student Id");
            populateTable(courseRecords);
        }

        else if (filter.equalsIgnoreCase("Student Name")) {
            courseRecords = manager.getRecordsByStudentName(inputField.getText());
            inputLabel.setText("Enter the student Name");
            populateTable(courseRecords);
        }

        else if (filter.equalsIgnoreCase("Course Id")) {
            courseRecords = manager.getRecordsByCourseId(inputField.getText());
            inputLabel.setText("Enter the course Id");
            populateTable(courseRecords);
        }

        else if (filter.equalsIgnoreCase("Course Name")) {
            courseRecords = manager.getRecordsByCourseName(inputField.getText());
            inputLabel.setText("Enter the course Name");
            populateTable(courseRecords);
        }

        else if (filter.equalsIgnoreCase("credit hours")) {
            try {
                int cHours = Integer.parseInt(inputField.getText());
                inputLabel.setText("Enter the credit hour");
                courseRecords = manager.getRecordsByCreditHours(cHours);
                populateTable(courseRecords);
            } catch (NumberFormatException nfe) {
                JOptionPane.showMessageDialog(this, "Please enter a valid year number.");
            }
        }

        else if (filter.equalsIgnoreCase("Department")) {
            courseRecords = manager.getRecordsByDepartment(inputField.getText().trim());
            inputLabel.setText("Enter the department");
            populateTable(courseRecords);
        }
            }

    private void populateTable(List<CourseRecord> courseRecords) {
        data = new String[courseRecords.size()][8];
        for (int i = 0; i < courseRecords.size(); i++) {
            CourseRecord c = courseRecords.get(i);
            data[i][0] = c.getStudentId();
            data[i][1] = c.getStudentName();
            data[i][2] = c.getCourseId();
            data[i][3] = c.getCourseName();
            data[i][4] = String.valueOf(c.getGrade());
            data[i][5] = String.valueOf(c.getCreditHours());
            data[i][6] = c.getDepartment();
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

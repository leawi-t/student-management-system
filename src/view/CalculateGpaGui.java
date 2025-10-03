package view;

import Controller.CourseRecordManager;
import Controller.StudentManager;
import Module.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class CalculateGpaGui extends JPanel implements ActionListener {

    private final JPanel northPanel = new JPanel();

    private final JPanel titlePanel = new JPanel();
    private final JLabel title = new JLabel("Calculate GPA");

    private final JPanel filterPanel = new JPanel();
    private final JLabel studentIdLabel = new JLabel("Enter the student id: ");
    private final JTextField studentIdField = new JTextField(20);
    private final JButton searchButton = new JButton("Search");

    private final JPanel southPanel = new JPanel();
    private final JLabel gpaLabel = new JLabel("Gpa: ");

    private final CourseRecordManager manager = new CourseRecordManager();
    private final StudentManager studentManager = new StudentManager();

    JTable table;
    DefaultTableModel model;
    JScrollPane scroll;

    List<CourseRecord> courseRecord;
    String[] columns = {"Student Id", "Student Name", "Course Id", "Course Name", "Grade", "Credit Hours", "Department"};
    String[][] data;

    CalculateGpaGui(){

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

        gpaLabel.setFont(new Font("Arail", Font.BOLD, 15));
        southPanel.add(gpaLabel);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;

        gbc.gridy = 0;
        gbc.gridx = 0;
        filterPanel.add(studentIdLabel, gbc);
        gbc.gridx = 1;
        filterPanel.add(studentIdField,gbc);
        gbc.gridx = 2;
        filterPanel.add(searchButton);

        searchButton.addActionListener(this);

        northPanel.add(titlePanel, BorderLayout.NORTH);
        northPanel.add(filterPanel, BorderLayout.CENTER);
        this.add(southPanel, BorderLayout.SOUTH);
        this.add(northPanel, BorderLayout.NORTH);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(!studentIdField.getText().isEmpty()) {
            String id = studentIdField.getText();
            if(studentManager.getStudentById(id) == null){
                JOptionPane.showMessageDialog(null, "Invalid id");
                return;
            }
            assert id != null;
            createTable(id);
            double gpa = manager.calculateGPA(id);
            gpaLabel.setText("Gpa: " + gpa);
        }
        else{
            JOptionPane.showMessageDialog(null, "Please enter a value");
        }
    }

    private void createTable(String id){
        courseRecord = manager.getRecordsByStudentId(id);
        populateTable(courseRecord);
    }

    private void populateTable(List<CourseRecord> courseRecord) {
        data = new String[courseRecord.size()][7];
        for (int i = 0; i < courseRecord.size(); i++) {
            CourseRecord c = courseRecord.get(i);
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

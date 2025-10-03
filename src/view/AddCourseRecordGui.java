package view;

import Controller.*;
import Module.*;
import javax.swing.*;
import java.awt.*;

public class AddCourseRecordGui extends JPanel {

    private final JTextField studentIdField = new JTextField(20);
    private final JTextField courseIdField = new JTextField(20);
    private final JTextField gradeField = new JTextField(20);

    private final JButton clear = new JButton("Clear");
    private final JButton add = new JButton("Add");

    private final CourseRecordManager recordManager = new CourseRecordManager();
    private final CourseManager courseManager = new CourseManager();
    private final StudentManager studentManager = new StudentManager();

    public AddCourseRecordGui() {
        setLayout(new BorderLayout());

        JLabel title = new JLabel("Add New Course Record");
        title.setFont(new Font("Arial", Font.BOLD, 16));
        JPanel northPanel = new JPanel();
        northPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        northPanel.add(title);
        add(northPanel, BorderLayout.NORTH);

        JPanel centerPanel = new JPanel(new GridBagLayout());
        centerPanel.setBorder(BorderFactory.createEmptyBorder(20, 40, 20, 40));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;

        int row = 0;
        addField(centerPanel, gbc, row++, "Student Id:", studentIdField);
        addField(centerPanel, gbc, row++, "Course Id:", courseIdField);
        addField(centerPanel, gbc, row++, "Grade:", gradeField);

        JPanel southPanel = new JPanel();
        southPanel.add(add);
        southPanel.add(clear);

        add(centerPanel, BorderLayout.CENTER);
        add(southPanel, BorderLayout.SOUTH);

        clear.addActionListener(e -> clearFields());

        add.addActionListener(e -> {
            try {
                Student student = studentManager.getStudentById(studentIdField.getText().trim());
                Course course = courseManager.getCourseById(courseIdField.getText().trim());
                CourseRecord record = recordManager.getCourseRecord(studentIdField.getText().trim(), courseIdField.getText().trim());

                if (student == null || course == null) {
                    JOptionPane.showMessageDialog(null, "Non existing entity used");
                    return;
                }

                if (record!=null){
                    JOptionPane.showMessageDialog(null, "Already taking the course");
                    return;
                }

                String studentId = studentIdField.getText().trim();
                String studentName = student.getName();
                String courseId = courseIdField.getText().trim();
                String courseName = course.getName();
                double grade = Double.parseDouble(gradeField.getText());
                int creditHours  = course.getCreditHours();
                String department = student.getDepartment();

                recordManager.addCourseRecord(new CourseRecord(studentId, studentName, courseId, courseName, grade, creditHours, department));
                clearFields();
            }
            catch(Exception ex){
                JOptionPane.showMessageDialog(null, "Invalid format please re enter fields", "Error Message", JOptionPane.ERROR_MESSAGE);
            }

        });
    }

    private void addField(JPanel panel, GridBagConstraints gbc, int y, String labelText, Component input) {
        gbc.gridx = 0;
        gbc.gridy = y;
        panel.add(new JLabel(labelText), gbc);
        gbc.gridx = 1;
        panel.add(input, gbc);
    }

    private void clearFields() {
        studentIdField.setText("");
        courseIdField.setText("");
        gradeField.setText("");
    }
}

package view;

import Controller.CourseRecordManager;
import javax.swing.*;
import java.awt.*;
import Module.*;

public class UpdateCourseRecordGui extends JPanel {

    private final JTextField studentIdField = new JTextField(20);
    private final JTextField courseIdField = new JTextField(20);
    private final JTextField gradeField = new JTextField(20);

    private final JButton clear = new JButton("Clear");
    private final JButton update = new JButton("Update");

    private final CourseRecordManager manager = new CourseRecordManager();

    public UpdateCourseRecordGui() {
        setLayout(new BorderLayout());

        JLabel title = new JLabel("Update Course Record Info");
        title.setFont(new Font("Arial", Font.BOLD, 16));
        JPanel northPanel = new JPanel();
        northPanel.add(title);
        northPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
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
        southPanel.add(update);
        southPanel.add(clear);

        add(centerPanel, BorderLayout.CENTER);
        add(southPanel, BorderLayout.SOUTH);

        clear.addActionListener(e -> clearFields());

        update.addActionListener(e -> {
            if(studentIdField.getText().isEmpty() || courseIdField.getText().isEmpty()){
                JOptionPane.showMessageDialog(null, "Id fields can not be empty");
                return;
            }
            CourseRecord courseRecord = manager.getCourseRecord(studentIdField.getText(), courseIdField.getText());

            if(courseRecord==null){
                JOptionPane.showMessageDialog(null , "Can't find course Record");
                return;
            }


            double existingGrade = courseRecord.getGrade();
            double grade = gradeField.getText().trim().isEmpty()? existingGrade : Double.parseDouble(gradeField.getText().trim());

            manager.updateCourseRecord(new CourseRecord(courseRecord.getStudentId(),courseRecord.getStudentName(),courseRecord.getCourseId(), courseRecord.getCourseName(), grade, courseRecord.getCreditHours(), courseRecord.getDepartment()));
            clearFields();
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

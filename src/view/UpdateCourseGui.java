package view;

import Controller.CourseManager;
import javax.swing.*;
import java.awt.*;
import Module.*;

public class UpdateCourseGui extends JPanel {

    private final JTextField idField = new JTextField(20);
    private final JTextField nameField = new JTextField(20);
    private final JTextField cHourField = new JTextField(20);
    private JComboBox<String> departmentBox;

    private final JButton clear = new JButton("Clear");
    private final JButton update = new JButton("Update");

    private final CourseManager manager = new CourseManager();

    public UpdateCourseGui() {
        setLayout(new BorderLayout());

        JLabel title = new JLabel("Update Course info");
        title.setFont(new Font("Arial", Font.BOLD, 16));
        JPanel northPanel = new JPanel();
        northPanel.add(title);
        northPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        add(northPanel, BorderLayout.NORTH);

        departmentBox = new JComboBox<>(new String[]{"Computer science", "Business", "Engineering", "Arts", "Medicine"});

        JPanel centerPanel = new JPanel(new GridBagLayout());
        centerPanel.setBorder(BorderFactory.createEmptyBorder(20, 40, 20, 40));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;

        int row = 0;
        addField(centerPanel, gbc, row++, "Id:", idField);
        addField(centerPanel, gbc, row++, "Course Name:", nameField);
        addField(centerPanel, gbc, row++, "Credit Hours:", cHourField);
        addField(centerPanel, gbc, row++, "Department:", departmentBox);

        JPanel southPanel = new JPanel();
        southPanel.add(update);
        southPanel.add(clear);

        add(centerPanel, BorderLayout.CENTER);
        add(southPanel, BorderLayout.SOUTH);

        clear.addActionListener(e -> clearFields());

        update.addActionListener(e -> {
            if(idField.getText().isEmpty()){
                JOptionPane.showMessageDialog(null, "Id field can not be empty");
                return;
            }
            Course course = manager.getCourseById(idField.getText());

            if(course==null){
                JOptionPane.showMessageDialog(null , "Can't find course");
                return;
            }

            String existingId = course.getId();
            String existingName = course.getName();
            int existingCHour = course.getCreditHours();
            String existingDepartment = course.getDepartment();

            String id = idField.getText().trim();
            String name = nameField.getText().trim().isEmpty() ? existingName : nameField.getText().trim();
            int cHour = cHourField.getText().trim().isEmpty() ? existingCHour : Integer.parseInt(cHourField.getText().trim());
            String department = departmentBox.getSelectedItem() == null ? existingDepartment : (String)  departmentBox.getSelectedItem();

            manager.updateCourse(new Course(id,name,cHour, department));
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
        idField.setText("");
        nameField.setText("");
        cHourField.setText("");
        departmentBox.setSelectedIndex(0);
    }
}

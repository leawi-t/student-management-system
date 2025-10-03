package view;

import Controller.CourseManager;
import Module.Course;
import javax.swing.*;
import java.awt.*;

public class AddCourseGui extends JPanel {

    private final JTextField idField = new JTextField(20);
    private final JTextField NameField = new JTextField(20);
    private final JTextField cHourField = new JTextField(20);
    private JComboBox<String> departmentBox;

    private final JButton clear = new JButton("Clear");
    private final JButton add = new JButton("Add");

    private final CourseManager manager = new CourseManager();

    public AddCourseGui() {
        setLayout(new BorderLayout());

        JLabel title = new JLabel("Add New Course");
        title.setFont(new Font("Arial", Font.BOLD, 16));
        JPanel northPanel = new JPanel();
        northPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        northPanel.add(title);
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
        addField(centerPanel, gbc, row++, "Name:", NameField);
        addField(centerPanel, gbc, row++, "Credit Hours:", cHourField);
        addField(centerPanel, gbc, row++, "Department:", departmentBox);

        JPanel southPanel = new JPanel();
        southPanel.add(add);
        southPanel.add(clear);

        add(centerPanel, BorderLayout.CENTER);
        add(southPanel, BorderLayout.SOUTH);

        clear.addActionListener(e -> clearFields());

        add.addActionListener(e -> {
            try {
                String id = idField.getText().trim();
                String name = NameField.getText().trim();
                int creditHours  = Integer.parseInt(cHourField.getText().trim());
                String department = (String) departmentBox.getSelectedItem();

                manager.addCourse(new Course(id, name, creditHours, department));
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
        idField.setText("");
        NameField.setText("");

        cHourField.setText("");
        departmentBox.setSelectedIndex(0);
    }
}

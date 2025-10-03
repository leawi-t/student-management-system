package view;

import Controller.StudentManager;
import Module.Student;
import javax.swing.*;
import java.awt.*;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.Month;

public class AddStudentGui extends JPanel {

    private final JTextField idField = new JTextField(20);
    private final JTextField fNameField = new JTextField(20);
    private final JTextField lNameField = new JTextField(20);
    private final JTextField emailField = new JTextField(20);
    private final JTextField phoneField = new JTextField(20);

    private JComboBox<String> genderBox;
    private JComboBox<String> departmentBox;
    private JComboBox<String> yearBox;

    private JComboBox<String> dobDayBox;
    private JComboBox<String> dobMonthBox;
    private JComboBox<String> dobYearBox;

    private final JButton clear = new JButton("Clear");
    private final JButton add = new JButton("Add");

    private final StudentManager manager = new StudentManager();

    public AddStudentGui() {
        setLayout(new BorderLayout());

        JLabel title = new JLabel("Add New Student");
        title.setFont(new Font("Arial", Font.BOLD, 16));
        JPanel northPanel = new JPanel();
        northPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        northPanel.add(title);
        add(northPanel, BorderLayout.NORTH);

        genderBox = new JComboBox<>(new String[]{"Male", "Female"});
        departmentBox = new JComboBox<>(new String[]{"Computer science", "Business", "Engineering", "Arts", "Medicine"});
        yearBox = new JComboBox<>(new String[]{"1","2","3","4"});

        dobDayBox = new JComboBox<>();
        for (int i = 1; i <= 31; i++) dobDayBox.addItem(String.valueOf(i));
        dobMonthBox = new JComboBox<>();
        for (Month m : Month.values()) dobMonthBox.addItem(m.toString());

        int currentYear = java.time.Year.now().getValue();
        String[] dobYears = new String[100];
        for (int i = 0; i < 100; i++) dobYears[i] = String.valueOf(currentYear - i - 10);
        dobYearBox = new JComboBox<>(dobYears);

        JPanel centerPanel = new JPanel(new GridBagLayout());
        centerPanel.setBorder(BorderFactory.createEmptyBorder(20, 40, 20, 40));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;

        int row = 0;
        addField(centerPanel, gbc, row++, "Id:", idField);
        addField(centerPanel, gbc, row++, "First Name:", fNameField);
        addField(centerPanel, gbc, row++, "Last Name:", lNameField);
        addField(centerPanel, gbc, row++, "Gender:", genderBox);
        addField(centerPanel, gbc, row++, "Date of Birth:", buildDatePanel());
        addField(centerPanel, gbc, row++, "Email:", emailField);
        addField(centerPanel, gbc, row++, "Phone Number:", phoneField);
        addField(centerPanel, gbc, row++, "Year of Enrollment:", yearBox);
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
                String fName = fNameField.getText().trim();
                String lName = lNameField.getText().trim();
                String gender = (String) genderBox.getSelectedItem();

                int day = Integer.parseInt((String) dobDayBox.getSelectedItem());
                int month = dobMonthBox.getSelectedIndex() + 1;
                int yearOfBirth = Integer.parseInt((String) dobYearBox.getSelectedItem());
                LocalDate dob = LocalDate.of(yearOfBirth, month, day);
                Date sqlDate = Date.valueOf(dob);

                String email = emailField.getText().trim();
                String phone = phoneField.getText().trim();
                int enrollmentYear = Integer.parseInt((String) yearBox.getSelectedItem());
                String department = (String) departmentBox.getSelectedItem();

                manager.addStudent(new Student(id, fName, lName, sqlDate, email, phone, enrollmentYear, gender, department,0.0));
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

    private JPanel buildDatePanel() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 0));
        dobDayBox.setPreferredSize(new Dimension(150,25));
        dobMonthBox.setPreferredSize(new Dimension(150,25));
        dobYearBox.setPreferredSize(new Dimension(150,25));
        panel.add(dobDayBox);
        panel.add(dobMonthBox);
        panel.add(dobYearBox);
        return panel;
    }

    private void clearFields() {
        idField.setText("");
        fNameField.setText("");
        lNameField.setText("");
        emailField.setText("");
        phoneField.setText("");
        genderBox.setSelectedIndex(0);
        dobDayBox.setSelectedIndex(0);
        dobMonthBox.setSelectedIndex(0);
        dobYearBox.setSelectedIndex(0);
        yearBox.setSelectedIndex(0);
        departmentBox.setSelectedIndex(0);
    }
}
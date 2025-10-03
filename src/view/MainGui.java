package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainGui implements ActionListener {

    JFrame frame = new JFrame();
    JLabel welcomeLabel = new JLabel("Student Management System");

    CardLayout sideCards;
    CardLayout mainCards;

    JPanel studentNavigation = new StudentNavigationPanel(this);
    JPanel courseNavigation = new CourseNavigationPanel(this);
    JPanel recordNavigation = new RecordNavigationPanel(this);

    JPanel northPanel = new JPanel();
    JPanel titlePanel = new JPanel();

    JPanel navigationPanel = new JPanel();
    JButton student = new JButton("Student Menu");
    JButton course = new JButton("Course Menu");
    JButton record = new JButton("Record Menu");

    JPanel westPanel = new JPanel();
    JPanel centerPanel = new JPanel();

    DisplayGui studentDisplay = new DisplayGui(1);
    DisplayGui courseDisplay = new DisplayGui(2);
    DisplayGui courseRecordDisplay = new DisplayGui(3);

    public MainGui(){

        northPanel.setLayout(new BorderLayout());

        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 20));
        titlePanel.add(welcomeLabel);
        titlePanel.setBackground(Color.LIGHT_GRAY);

        navigationPanel.add(student);
        navigationPanel.add(course);
        navigationPanel.add(record);
        navigationPanel.setBackground(Color.LIGHT_GRAY);

        northPanel.add(titlePanel);
        northPanel.add(navigationPanel, BorderLayout.SOUTH);

        sideCards = new CardLayout();
        westPanel.setLayout(sideCards);
        westPanel.add("student", studentNavigation);
        westPanel.add("course", courseNavigation);
        westPanel.add("record", recordNavigation);

        mainCards = new CardLayout();
        centerPanel.setLayout(mainCards);
        centerPanel.add("display Student", studentDisplay);
        centerPanel.add("add Student", new AddStudentGui());
        centerPanel.add("update Student", new UpdateStudentGui());
        centerPanel.add("search Student", new SearchStudentGui());
        centerPanel.add("delete Student", new DeleteStudentGui());

        centerPanel.add("display Course",  courseDisplay);
        centerPanel.add("add Course", new AddCourseGui());
        centerPanel.add("update Course", new UpdateCourseGui());
        centerPanel.add("search Course", new SearchCourseGui());
        centerPanel.add("delete Course", new DeleteCourseGui());

        centerPanel.add("display CourseRecord", courseRecordDisplay);
        centerPanel.add("add CourseRecord", new AddCourseRecordGui());
        centerPanel.add("update CourseRecord", new UpdateCourseRecordGui());
        centerPanel.add("delete CourseRecord", new DeleteCourseRecordGui());
        centerPanel.add("calculate GPA", new CalculateGpaGui());
        centerPanel.add("search CourseRecord", new SearchCourseRecordGui());

        student.setToolTipText("View student-related actions");
        student.setBackground(Color.DARK_GRAY);
        student.setForeground(Color.WHITE);
        student.addActionListener(this);
        course.setToolTipText("View course-related actions");
        course.addActionListener(this);
        record.setToolTipText("View Course record-related actions");
        record.addActionListener(this);

        frame.setSize(1300,800);
        frame.setTitle("Student Management System");
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.add(westPanel, BorderLayout.WEST);
        frame.add(centerPanel);
        frame.add(northPanel, BorderLayout.NORTH);

        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==student) {
            sideCards.show(westPanel, "student");
            mainCards.show(centerPanel, "display Student");
            updateButtonStyles(student);
        }
        else if (e.getSource()==course) {
            sideCards.show(westPanel, "course");
            mainCards.show(centerPanel, "display Course");
            updateButtonStyles(course);
        }
        else if (e.getSource()==record) {
            sideCards.show(westPanel, "record");
            mainCards.show(centerPanel, "display CourseRecord");
            updateButtonStyles(record);
        }
    }

    private void updateButtonStyles(JButton activeButton) {
        JButton[] buttons = {student, course, record};
        for (JButton btn : buttons) {
            if (btn == activeButton) {
                btn.setBackground(Color.DARK_GRAY);
                btn.setForeground(Color.WHITE);
            } else {
                btn.setBackground(null); // resets to default
                btn.setForeground(Color.BLACK);
                btn.setBackground(Color.WHITE);
            }
        }
    }
}

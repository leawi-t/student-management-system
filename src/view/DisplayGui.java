package view;

import Controller.*;
import Module.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class DisplayGui extends JPanel {
    StudentManager studentManager;
    CourseManager courseManager;
    CourseRecordManager courseRecordManager;

    JPanel northPanel = new JPanel();
    JLabel displayLabel = new JLabel();

    DefaultTableModel model;
    JTable table;
    JScrollPane scroll;

    String columns[];
    String data[][];

    DisplayGui(int x){

        this.setLayout(new BorderLayout());
        studentManager = new StudentManager();
        courseManager = new CourseManager();
        courseRecordManager = new CourseRecordManager();

        displayLabel.setText("Display Page");
        displayLabel.setFont(new Font("Arial", Font.BOLD, 16));
        northPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        northPanel.add(displayLabel);

        if (x==1) {

            List<Student> students = studentManager.getAllStudent();

            columns = new String[]{"Id", "Name", "gender", "email", "phone Num", "Date-Of-Birth", "department", "year","GPA"};
            data = new String[students.size()][9];

            for (int i = 0; i < students.size(); i++) {
                data[i][0] = students.get(i).getId();
                data[i][1] = students.get(i).getName();
                data[i][2] = students.get(i).getGender();
                data[i][3] = students.get(i).getEmail();
                data[i][4] = students.get(i).getPhoneNumber();
                data[i][5] = String.valueOf(students.get(i).getDateOfBirth());
                data[i][6] = students.get(i).getDepartment();
                data[i][7] = String.valueOf(students.get(i).getYear());
                data[i][8] = String.valueOf(students.get(i).getGpa());
            }

            model = new DefaultTableModel(data, columns) {
                @Override
                public boolean isCellEditable(int row, int column) {
                    return false;
                }
            };
        }
        else if (x==2) {

            List<Course> course = courseManager.getAllCourse();

            columns = new String[]{"Id", "Name", "credit Hours", "Department"};
            data = new String[course.size()][4];

            for (int i = 0; i < course.size(); i++) {
                data[i][0] = course.get(i).getId();
                data[i][1] = course.get(i).getName();
                data[i][2] = String.valueOf(course.get(i).getCreditHours());
                data[i][3] = course.get(i).getDepartment();
            }

            model = new DefaultTableModel(data, columns) {
                @Override
                public boolean isCellEditable(int row, int column) {
                    return false;
                }
            };
        }
        else if (x==3) {

            List<CourseRecord> courseRecords = courseRecordManager.getAllCourseRecord();

            columns = new String[]{"Student Id", "Student Name", "Course Id", "Course Name", "grade", "credit Hours", "Department"};
            data = new String[courseRecords.size()][7];

            for (int i = 0; i < courseRecords.size(); i++) {
                data[i][0] = courseRecords.get(i).getStudentId();
                data[i][1] = courseRecords.get(i).getStudentName();
                data[i][2] = courseRecords.get(i).getCourseId();
                data[i][3] = courseRecords.get(i).getCourseName();
                data[i][4] = String.valueOf(courseRecords.get(i).getGrade());
                data[i][5] = String.valueOf(courseRecords.get(i).getCreditHours());
                data[i][6] = courseRecords.get(i).getDepartment();
            }

            model = new DefaultTableModel(data, columns) {
                @Override
                public boolean isCellEditable(int row, int column) {
                    return false;
                }
            };
        }

        table = new JTable(model);
        table.setFillsViewportHeight(true);
        scroll = new JScrollPane(table);
        scroll.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        this.add(scroll);
        this.add(northPanel, BorderLayout.NORTH);
    }

    public void refreshTable(int x) {
        if (x==1) {
            List<Student> students = studentManager.getAllStudent();

            String[][] data = new String[students.size()][9];
            for (int i = 0; i < students.size(); i++) {
                Student s = students.get(i);
                data[i][0] = s.getId();
                data[i][1] = s.getName();
                data[i][2] = s.getGender();
                data[i][3] = s.getEmail();
                data[i][4] = s.getPhoneNumber();
                data[i][5] = String.valueOf(s.getDateOfBirth());
                data[i][6] = s.getDepartment();
                data[i][7] = String.valueOf(s.getYear());
                data[i][8] = String.valueOf(s.getGpa());
            }

            model.setDataVector(data, new String[]{"Id", "Name", "gender", "email", "phone Num", "Date-Of-Birth", "department", "year", "GPA"});
        }

        else if (x==2) {
            List<Course> course = courseManager.getAllCourse();

            columns = new String[]{"Id", "Name", "credit Hours", "Department"};
            data = new String[course.size()][4];

            for (int i = 0; i < course.size(); i++) {
                data[i][0] = course.get(i).getId();
                data[i][1] = course.get(i).getName();
                data[i][2] = String.valueOf(course.get(i).getCreditHours());
                data[i][3] = course.get(i).getDepartment();
            }

            model.setDataVector(data, new String[]{"Id", "Name","credit Hours", "department"});
        }

        else if(x==3){
            List<CourseRecord> courseRecords = courseRecordManager.getAllCourseRecord();

            columns = new String[]{"Student Id", "Student Name", "Course Id", "Course Name", "grade", "credit Hours", "Department"};
            data = new String[courseRecords.size()][7];

            for (int i = 0; i < courseRecords.size(); i++) {
                data[i][0] = courseRecords.get(i).getStudentId();
                data[i][1] = courseRecords.get(i).getStudentName();
                data[i][2] = courseRecords.get(i).getCourseId();
                data[i][3] = courseRecords.get(i).getCourseName();
                data[i][4] = String.valueOf(courseRecords.get(i).getGrade());
                data[i][5] = String.valueOf(courseRecords.get(i).getCreditHours());
                data[i][6] = courseRecords.get(i).getDepartment();
            }

            model.setDataVector(data, new String[]{"Student Id", "Student Name", "Course Id", "Course Name", "grade", "credit Hours", "Department"});
        }
    }
}

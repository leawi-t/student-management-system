package Controller;

import DAO.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import Module.*;

import javax.swing.*;

public class StudentManager {

    private Connection connection;

    public StudentManager(){
        try {
            connection = DBConnection.getConnection();
        }
        catch(SQLException e){
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, "Unexpected error");
        }
   }

   private List<Student> queryStudent(ResultSet rs) throws SQLException{
        List<Student> students = new ArrayList<>();
        while(rs.next()){
           String id = rs.getString("id");
           String fname = rs.getString("fname");
           String lname = rs.getString("lname");
           Date dateOfBirth = rs.getDate("date_of_birth");
           String email = rs.getString("email");
           String phoneNum = rs.getString("phone_num");
           double gpa = rs.getDouble("gpa");
           int year = rs.getInt("year_enrolled");
           String gender = rs.getString("gender");
           String department = rs.getString("department");

           students.add(new Student(id,fname,lname,dateOfBirth,email, phoneNum, year, gender,department,gpa));
       }
        return students;
   }

    public List<Student> getAllStudent(){
        List<Student> students = new ArrayList<>();
        try {
            String sql = "SELECT * FROM student";
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet rs = statement.executeQuery();

            students = queryStudent(rs);

        }
        catch(SQLException e){
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, "Unexpected error");
        }
        return students;
    }

    public List<Student> getStudentByFName(String fName){
        List<Student> students = new ArrayList<>();
        try {
            String sql = "SELECT * FROM student WHERE fname = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1,fName);
            ResultSet rs = statement.executeQuery();

            students = queryStudent(rs);
        }
        catch(SQLException e){
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, "Unexpected error");
        }
        return students;
    }

    public List<Student> getStudentByLName(String lName){
        List<Student> students = new ArrayList<>();
        try {
            String sql = "SELECT * FROM student WHERE lname = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1,lName);
            ResultSet rs = statement.executeQuery();

            students = queryStudent(rs);
        }
        catch(SQLException e){
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, "Unexpected error");
        }
        return students;
    }

    public List<Student> getStudentByDepartment(String department){
        List<Student> students = new ArrayList<>();
        try {
            String sql = "SELECT * FROM student WHERE department = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1,department);
            ResultSet rs = statement.executeQuery();

            students = queryStudent(rs);
        }
        catch(SQLException e){
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, "Unexpected error");
        }
        return students;
    }

    public List<Student> getStudentByYear(int Year){
        List<Student> students = new ArrayList<>();
        try {
            String sql = "SELECT * FROM student WHERE enrollment_year = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1,Year);
            ResultSet rs = statement.executeQuery();

            students = queryStudent(rs);
        }
        catch(SQLException e){
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, "Unexpected error");
        }
        return students;
    }

    public List<Student> getStudentByGender(String Gender){
        List<Student> students = new ArrayList<>();
        try {
            String sql = "SELECT * FROM student WHERE gender = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1,Gender);
            ResultSet rs = statement.executeQuery();

            students = queryStudent(rs);
        }
        catch(SQLException e){
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, "Unexpected error");
        }
        return students;
    }

    public Student getStudentById(String studentId){
        try {
            String sql = "SELECT * FROM student WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, studentId);
            ResultSet rs = statement.executeQuery();

            if(rs.next()){
                String id = rs.getString("id");
                String fname = rs.getString("fname");
                String lname = rs.getString("lname");
                Date dateOfBirth = rs.getDate("date_of_birth");
                String email = rs.getString("email");
                String phoneNum = rs.getString("phone_num");
                double gpa = rs.getDouble("gpa");
                int year = rs.getInt("year_enrolled");
                String gender = rs.getString("gender");
                String department = rs.getString("department");

                return new Student(id,fname,lname,dateOfBirth,email, phoneNum, year, gender,department, gpa);
            }
            return null;
        }
        catch(SQLException e){
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, "Unexpected error");
        }
        return null;
    }

    public void addStudent(Student student){
        try {
            String sql = "INSERT INTO student(id,fname,lname,gpa,year_enrolled,department,gender, date_of_birth, email, phone_num) VALUES (?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, student.getId());
            statement.setString(2, student.getfName());
            statement.setString(3, student.getlName());
            statement.setDouble(4, student.getGpa());
            statement.setInt(5, student.getYear());
            statement.setString(6, student.getDepartment());
            statement.setString(7, student.getGender());
            statement.setDate(8, new java.sql.Date(student.getDateOfBirth().getTime()));
            statement.setString(9, student.getEmail());
            statement.setString(10, student.getPhoneNumber());
            int rows = statement.executeUpdate();

            if (rows>=1) {
                JOptionPane.showMessageDialog(null, "adding student successful");
                return;
            }
            JOptionPane.showMessageDialog(null, "adding student not successful");

        }
        catch(SQLException e){
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, "Unexpected error");
        }
    }

    public void deleteStudent(String id){
        try {
            String sql = "DELETE FROM student WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, id);
            int rows = statement.executeUpdate();

            if (rows>=1) {
                JOptionPane.showMessageDialog(null, "Deleting student successfull");
                return;
            }
            JOptionPane.showMessageDialog(null, "Deleting student not successfull");

        }
        catch(SQLException e){
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, "Unexpected error");
        }
    }

    public void updateStudent(Student student){
        try{
            String sql = "UPDATE student SET fname = ?, lname = ?, year_enrolled = ?, department = ?, gender = ?, date_of_birth = ?, phone_num = ?, email = ? WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1,student.getfName());
            statement.setString(2,student.getlName());
            statement.setInt(3,student.getYear());
            statement.setString(4,student.getDepartment());
            statement.setString(5,student.getGender());
            statement.setDate(6,(java.sql.Date)student.getDateOfBirth());
            statement.setString(7,student.getPhoneNumber());
            statement.setString(8,student.getEmail());
            statement.setString(9,student.getId());

            int rows = statement.executeUpdate();

            if(rows>=1) JOptionPane.showMessageDialog(null, "Update successful");
            else JOptionPane.showMessageDialog(null, "update unsuccessful");
        }
        catch(SQLException e){
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, "Unexpected error");
        }
    }
}

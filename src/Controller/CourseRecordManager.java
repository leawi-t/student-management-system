package Controller;

import DAO.*;
import Module.*;
import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CourseRecordManager {

    Connection connection;
    private final StudentManager studentManager = new StudentManager();
    private final CourseManager courseManager = new CourseManager();

    public CourseRecordManager() {
        try {
            connection = DBConnection.getConnection();
        } catch(SQLException e){
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, "Unexpected error");
        }
    }

    private List<CourseRecord> queryCourseRecord(ResultSet rs) throws SQLException {
        List<CourseRecord> courseRecords = new ArrayList<>();
        while (rs.next()) {
            String studentId = rs.getString("student_id");
            String studentName = rs.getString("student_name");
            String courseId = rs.getString("course_id");
            String courseName = rs.getString("course_name");
            int creditHours = rs.getInt("credit_hours");
            double grade = rs.getDouble("grade");
            String department = rs.getString("department");

            courseRecords.add(new CourseRecord(studentId, studentName, courseId, courseName, grade, creditHours, department));
        }
        return courseRecords;
    }

    public CourseRecord getCourseRecord(String sId, String cId) {
        try {
            String sql = "SELECT * FROM course_record WHERE student_id = ? AND course_id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1,sId);
            statement.setString(2,cId);
            ResultSet rs = statement.executeQuery();
            List<CourseRecord> courseRecords = new ArrayList<>();
            if (rs.next()) {
                String studentId = rs.getString("student_id");
                String studentName = rs.getString("student_name");
                String courseId = rs.getString("course_id");
                String courseName = rs.getString("course_name");
                int creditHours = rs.getInt("credit_hours");
                double grade = rs.getDouble("grade");
                String department = rs.getString("department");
                return new CourseRecord(studentId, studentName, courseId, courseName, grade, creditHours, department);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error Message", JOptionPane.ERROR_MESSAGE);
            return null;
        }
        return null;
    }

    public List<CourseRecord> getAllCourseRecord() {
        List<CourseRecord> courseRecords = new ArrayList<>();
        try {
            String sql = "SELECT * FROM course_record";
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet rs = statement.executeQuery();
            courseRecords = queryCourseRecord(rs);
        } catch(SQLException e){
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, "Unexpected error");
        }
        return courseRecords;
    }

    public List<CourseRecord> getRecordsByStudentId(String id) {
        List<CourseRecord> courseRecords = new ArrayList<>();
        try {
            String sql = "SELECT * FROM course_record WHERE student_id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, id);
            ResultSet rs = statement.executeQuery();

            courseRecords = queryCourseRecord(rs);
        } catch(SQLException e){
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, "Unexpected error");
        }
        return courseRecords;
    }

    public List<CourseRecord> getRecordsByStudentName(String name) {
        List<CourseRecord> courseRecords = new ArrayList<>();
        try {
            String sql = "SELECT * FROM course_record WHERE student_name = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, name);
            ResultSet rs = statement.executeQuery();

            courseRecords = queryCourseRecord(rs);
        } catch(SQLException e){
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, "Unexpected error");
        }
        return courseRecords;
    }

    public List<CourseRecord> getRecordsByCourseId(String id) {
        List<CourseRecord> courseRecords = new ArrayList<>();
        try {
            String sql = "SELECT * FROM course_record WHERE course_id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, id);
            ResultSet rs = statement.executeQuery();

            courseRecords = queryCourseRecord(rs);
        } catch(SQLException e){
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, "Unexpected error");
        }
        return courseRecords;
    }

    public List<CourseRecord> getRecordsByCourseName(String name) {
        List<CourseRecord> courseRecords = new ArrayList<>();
        try {
            String sql = "SELECT * FROM course_record WHERE course_name = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, name);
            ResultSet rs = statement.executeQuery();

            courseRecords = queryCourseRecord(rs);
        } catch(SQLException e){
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, "Unexpected error");
        }
        return courseRecords;
    }

    public List<CourseRecord> getRecordsByDepartment(String department) {
        List<CourseRecord> courseRecords = new ArrayList<>();
        try {
            String sql = "SELECT * FROM course_record WHERE department = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, department);
            ResultSet rs = statement.executeQuery();

            courseRecords = queryCourseRecord(rs);
        } catch(SQLException e){
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, "Unexpected error");
        }
        return courseRecords;
    }

    public List<CourseRecord> getRecordsByCreditHours(int cHours) {
        List<CourseRecord> courseRecords = new ArrayList<>();
        try {
            String sql = "SELECT * FROM course_record WHERE credit_hours = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, cHours);
            ResultSet rs = statement.executeQuery();

            courseRecords = queryCourseRecord(rs);
        } catch(SQLException e){
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, "Unexpected error");
        }
        return courseRecords;
    }


    public List<CourseRecord> getCourseRecordByDepartment(String dept) {
        List<CourseRecord> courseRecords = new ArrayList<>();
        try {
            String sql = "SELECT * FROM course_record WHERE department = ?";

            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, dept);
            ResultSet rs = statement.executeQuery();

            courseRecords = queryCourseRecord(rs);
        }
        catch(SQLException e){
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, "Unexpected error");
        }
        return courseRecords;
    }

    public void addCourseRecord(CourseRecord record) {
        try {

            Student s = studentManager.getStudentById(record.getStudentId());
            Course c = courseManager.getCourseById(record.getCourseId());

            if (!s.getDepartment().equals(c.getDepartment())) {
                JOptionPane.showMessageDialog(null, "Student can only enroll in courses from their department.");
                return;
            }

            String sql = "INSERT INTO course_record (student_id, student_name, course_id, course_name, grade, credit_hours, department) VALUES (?,?,?,?,?,?,?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, record.getStudentId());
            statement.setString(2, record.getStudentName());
            statement.setString(3, record.getCourseId());
            statement.setString(4, record.getCourseName());
            statement.setDouble(5, record.getGrade());
            statement.setInt(6, record.getCreditHours());
            statement.setString(7, record.getDepartment());
            int rows = statement.executeUpdate();

            if (rows >= 1) {
                JOptionPane.showMessageDialog(null, "Adding record successful");
                return;
            }
            JOptionPane.showMessageDialog(null, "Adding record not successful");

        } catch(SQLException e){
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, "Unexpected error");
        }
    }

    public void updateCourseRecord(CourseRecord courseRecord){
        try{
            String sql = "UPDATE course_record SET student_name = ?, course_name = ?, grade = ?, credit_hours = ?, department = ? WHERE student_id = ? AND course_id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1,courseRecord.getStudentName());
            statement.setString(2,courseRecord.getCourseName());
            statement.setDouble(3, courseRecord.getGrade());
            statement.setInt(4,courseRecord.getCreditHours());
            statement.setString(5,courseRecord.getDepartment());
            statement.setString(6,courseRecord.getStudentId());
            statement.setString(7,courseRecord.getCourseId());

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

    public double calculateGPA(String id) {
        List<CourseRecord> courseRecords = getRecordsByStudentId(id);
        double gpa = 0.0;
        double totalGradePoints = 0;
        double totalCredits = 0;

        for (CourseRecord cr : courseRecords) {
            Course course = courseManager.getCourseById(cr.getCourseId());
            totalGradePoints += cr.getGrade() * course.getCreditHours();
            totalCredits += course.getCreditHours();
        }

        gpa = totalGradePoints / totalCredits;
        gpa = Math.round(gpa * 100.0) / 100.0;

        try {
            String sql = "UPDATE student SET gpa = ? WHERE id = ?";
            PreparedStatement updateStatement = connection.prepareStatement(sql);
            updateStatement.setDouble(1, gpa);
            updateStatement.setString(2, id);
            int rows = updateStatement.executeUpdate();

            if (rows >= 1) {
                JOptionPane.showMessageDialog(null, "Update successful");
                return gpa;
            } else {
                JOptionPane.showMessageDialog(null, "Update unsuccessful");
            }
        } catch(SQLException e){
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, "Unexpected error");
        }
        return 0;
    }

    public void deleteCourseRecord(String studentId, String courseId){
        try {
            String sql = "DELETE FROM course_record WHERE student_id = ? AND course_id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, studentId);
            statement.setString(2, courseId);
            int rows = statement.executeUpdate();

            if (rows>=1) {
                JOptionPane.showMessageDialog(null, "Deleting record successful");
                return;
            }
            JOptionPane.showMessageDialog(null, "Deleting record not successful");

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
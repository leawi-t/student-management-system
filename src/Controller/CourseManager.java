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

public class CourseManager {

    Connection connection;

    public CourseManager() {
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

    private List<Course> queryCourse(ResultSet rs) throws SQLException{
        List<Course> course = new ArrayList<>();
        while(rs.next()){
            String id = rs.getString("id");
            String name = rs.getString("name");
            int creditHours = rs.getInt("credit_hours");
            String department = rs.getString("department");

            course.add(new Course(id,name,creditHours,department));
        }
        return course;
    }

    public List<Course> getAllCourse(){
        List<Course> course = new ArrayList<>();
        try {
            String sql = "SELECT * FROM course";
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet rs = statement.executeQuery();

            course = queryCourse(rs);
        }
        catch(SQLException e){
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, "Unexpected error");
        }
        return course;
    }

    public Course getCourseById(String courseId) {
        try {
            String sql = "SELECT * FROM course WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, courseId);
            ResultSet rs = statement.executeQuery();

            if(rs.next()){
                String id = rs.getString("id");
                String name = rs.getString("name");
                int creditHours = rs.getInt("credit_hours");
                String department = rs.getString("department");

                return new Course(id,name,creditHours,department);
            }
        }
        catch(SQLException e){
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, "Unexpected error");
        }
        return  null;
    }

    public void addCourse(Course course){
        try {
            String sql = "INSERT INTO course(id,name,credit_hours,department) VALUES (?,?,?,?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, course.getId());
            statement.setString(2, course.getName());
            statement.setInt(3, course.getCreditHours());
            statement.setString(4, course.getDepartment());
            int rows = statement.executeUpdate();

            if (rows>=1) {
                JOptionPane.showMessageDialog(null, "adding course successful");
                return;
            }
            JOptionPane.showMessageDialog(null, "adding course not successful");

        }
        catch (SQLException e) {
            e.printStackTrace(); // You can log this too
            JOptionPane.showMessageDialog(null, "SQL Error: " + e.getMessage());
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Unexpected Error: " + ex.getMessage());
        }
    }

    public void updateCourse(Course course){
        try{
            String sql = "UPDATE course SET name = ?, credit_hours = ?, department = ? WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1,course.getName());
            statement.setInt(2,course.getCreditHours());
            statement.setString(3,course.getDepartment());
            statement.setString(4,course.getId());

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

    public List<Course> getCourseByName(String Name){
        List<Course> course = new ArrayList<>();
        try {
            String sql = "SELECT * FROM course WHERE name = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1,Name);
            ResultSet rs = statement.executeQuery();

            course = queryCourse(rs);
        }
        catch(SQLException e){
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, "Unexpected error");
        }
        return course;
    }

    public List<Course> getCourseByDepartment(String department){
        List<Course> course = new ArrayList<>();
        try {
            String sql = "SELECT * FROM course WHERE department = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, department);
            ResultSet rs = statement.executeQuery();

            course = queryCourse(rs);
        }
        catch(SQLException e){
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, "Unexpected error");
        }
        return course;
    }

    public List<Course> getCourseByCHours(int cHours){
        List<Course> course = new ArrayList<>();
        try {
            String sql = "SELECT * FROM course WHERE credit_hours = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1,cHours);
            ResultSet rs = statement.executeQuery();

            course = queryCourse(rs);
        }
        catch(SQLException e){
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, "Unexpected error");
        }
        return course;
    }

    public void deleteCourse(String id){
        try {
            String sql = "DELETE FROM course WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, id);
            int rows = statement.executeUpdate();

            if (rows>=1) {
                JOptionPane.showMessageDialog(null, "Deleting course successful");
                return;
            }
            JOptionPane.showMessageDialog(null, "Deleting course not successful");

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

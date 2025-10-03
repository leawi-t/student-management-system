package Module;
public class CourseRecord {

    private String studentId;
    private String studentName;
    private String courseId;
    private String courseName;
    private double grade;
    private int creditHours;
    private String department;

    public CourseRecord(String studentId, String studentName, String courseId, String courseName, double grade, int creditHours, String department) {
        this.courseId = courseId;
        this.courseName = courseName;
        this.studentId = studentId;
        this.studentName = studentName;
        this.grade = grade;
        this.creditHours = creditHours;
        this.department = department;
    }

    public String displayInfo(){
        return "Student Id: " + studentId + "\nStudent Name: " + studentName + "\nCourse Id: " + courseId + "\nCourse Name: " + courseName + "\nGrade: " + String.valueOf(grade) + "\nCredit Hours: " + String.valueOf(creditHours) + "\nDepartment: " + department;
    }

    public String getStudentId() {
        return studentId;
    }

    public String getCourseId() {
        return courseId;
    }

    public double getGrade() {
        return grade;
    }

    public String getStudentName() {
        return studentName;
    }

    public String getCourseName() {
        return courseName;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public int getCreditHours() {
        return creditHours;
    }
}

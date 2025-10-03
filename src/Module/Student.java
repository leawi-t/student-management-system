package Module;
import java.util.Date;

public class Student {

    private String id;
    private String fName;
    private String lName;
    private Date dateOfBirth;
    private String email;
    private String phoneNumber;
    private double gpa;
    private String gender;
    private String department;
    private int year;

    public Student(String id, String fName, String lName, Date dateOfBirth, String email, String phoneNumber, int year, String gender, String department, double gpa){
        this.id = id;
        this.fName = fName;
        this.lName = lName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.gender = gender;
        this.department = department;
        this.dateOfBirth = dateOfBirth;
        this.year = year;
        this.gpa = gpa;
    }

    public String displayInfo(){
        return "Id: " + id + "\nName: " + getName()+ "\nDate of Birth: " + dateOfBirth + "\nGender: " + gender + "\nemail: " + email + "\nYear: " + year + "\nDepartment: " + department;
    }

    public String getName(){
        return fName + " " + lName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getfName() {
        return fName;
    }

    public String getlName() {
        return lName;
    }

    public double getGpa() {
        return gpa;
    }

    public String getGender() {
        return gender;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public int getYear() {
        return year;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

}

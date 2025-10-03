package Module;
public class Course {

    private String id;
    private String name;
    private int creditHours;
    private String department;

    public Course(String id, String name, int creditHours, String department) {
        this.id = id;
        this.name = name;
        this.creditHours = creditHours;
        this.department = department;
    }

    public String displayInfo(){
        return "Id: " + id + "\nName: " + name + "\nCredit Hours: " + creditHours + "\nDepartment: " + department;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCreditHours() {
        return creditHours;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }
}

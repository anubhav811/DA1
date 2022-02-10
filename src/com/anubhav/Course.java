

public class Course {
    private String courseCode;
    private String courseName;
    private int credits;

    // // Constructor for Course class.
    // public Course(String courseCode, String courseName, int credits) {
    //     this.courseCode = courseCode;
    //     this.courseName = courseName;
    //     this.credits = credits;
    // }

    // Mutator methods
    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public void setCredits(int credits) {
        this.credits = credits;
    }

    // Accessor Methods
    public String getCourseCode() {
        return courseCode;
    }

    public String getCourseName() {
        return courseName;
    }

    public int getCredits() {
        return credits;
    }

    // Overriden toString() method to display Course details.
    @Override
    public String toString() {
        return "Course Name = " + courseName + ", Course Code = " + courseCode + ", Credits = " + credits;
    }

    
}


import java.util.*;

public class Student {

    private String regNo;
    private String name;
    private Date dob;
    private String address;
    private String mobileNo;
    private ArrayList<Course> allotedCourses = new ArrayList<Course>(); // an arraylist of type "Course".
    private int totalCredits;   // total credits of all the courses.
    private int[] marks;        // int array to store marks.
    private char[] grades;      // char array to store grades.

    // Mutator methods
    public void setRegNo(String regNo) {
        this.regNo = regNo;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }

    // Method to populate the marks array with random values between 0 and 100.
    public void setMarks() {
        Random rand = new Random();
        marks = new int[allotedCourses.size()];
        for (int i = 0; i < marks.length; i++) {
            this.marks[i] = rand.nextInt(100);
        }
    }
    
    // Method to populate grades array according to marks
    public void setGrades() {
        this.grades = new char[marks.length];
        for (int i = 0; i < marks.length; i++) {
            if (marks[i] >= 90) {
                grades[i] = 'S';
            } else if (marks[i] >= 80) {
                grades[i] = 'A';
            } else if (marks[i] >= 70) {
                grades[i] = 'B';
            } else if (marks[i] >= 60) {
                grades[i] = 'C';
            } else {
                grades[i] = 'D';
            }
        }
    }

    // Method to add a new course to allocated courses
    public void addCourse(Course course) {
        this.allotedCourses.add(course);
        this.totalCredits += course.getCredits();
    }

    // Method to check if a course is already allocated to the student
    public boolean isCourseAllocated(String cc) {
        for (Course c : allotedCourses) {
            if (c.getCourseCode().equals(cc)) {
                return true;
            }
        }

        return false;
    }

    // Accessor methods
    public String getRegNo() {
        return regNo;
    }

    public String getAddress() {
        return address;
    }

    public String getName() {
        return name;
    }

    public Date getDob() {
        return dob;
    }

    public String getMobileNo() {
        return mobileNo;
    }

    public ArrayList<Course> getAllotedCourses() {
        return allotedCourses;
    }

    // Method to return alloted courses in string format
    public String getAllotedCoursesString() {
        ArrayList<Course> courses = getAllotedCourses();
        StringBuilder sb = new StringBuilder();
        for (Course c : courses) {
            sb.append(c.getCourseName() + ",");
        }
        return sb.toString();
    }

    public int[] getMarks() {
        return marks;
    }

    public char[] getGrades() {
        return grades;
    }

    public int getTotalCredits() {
        return totalCredits;
    }

    // Method to calculate GPA.
    public String getGPA() {

        float gpa, sum = 0;
        for (int i = 0; i < this.getGrades().length; i++) {
            if (this.getGrades()[i] == 'S') {
                sum += 10 * this.getAllotedCourses().get(i).getCredits();
            } else if (this.getGrades()[i] == 'A') {
                sum += 9 * this.getAllotedCourses().get(i).getCredits();
            } else if (this.getGrades()[i] == 'B') {
                sum += 8 * this.getAllotedCourses().get(i).getCredits();
            } else if (this.getGrades()[i] == 'C') {
                sum += 7 * this.getAllotedCourses().get(i).getCredits();
            } else {
                sum += 6 * this.getAllotedCourses().get(i).getCredits();
            }
        }
        gpa = sum / this.totalCredits;
        String GPA = String.format("%.2f", gpa);
        return GPA;
    }

    // Overriden toString method to display Student details.
    @Override
    public String toString() {
        String courses = " ";
        for (Course c : allotedCourses) {
            courses += "Course Code:" + c.getCourseCode() + " , Course Name:" + c.getCourseName() + " , Credits:"
                    + c.getCredits() + "\n\t\t  ";
        }
        return "Student Detail \n-------------------------------------\n" + "Name = " + name + "\n\nRegister No = "
                + regNo
                + "\n\nAlloted Courses ="
                + courses + "\n-------------------------------------";

    }

}

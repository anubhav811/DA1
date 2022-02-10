
// Anubhav Chachra
// 20BIT0104

import java.io.*;
import java.text.*;
import java.util.*;

public class Da1 {

    // Constants
    final static int minCredit = 12;
    final static int maxCredit = 24;

    public static void main(String[] args) throws InstantiationException, IOException {

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        System.out.println("\nADDING STUDENT DETAILS");
        System.out.println("........................");
        System.out.println("\nEnter number of students in system");

        int n = Integer.parseInt(bf.readLine());
        Student[] students = new Student[n];
        ArrayList<String> reg = new ArrayList<>();
        boolean flag = false;

        // Populating students array with student objects.
        for (int s = 0; s < n; s++) {

            students[s] = new Student();

            flag = false;
            while (!flag) {
                System.out.println("Enter register no for student " + (s + 1) + " : ");
                String regNo = bf.readLine();
                if (reg.contains(regNo)) {
                    System.out.println("Register no already exists");
                } else {
                    reg.add(regNo);
                    flag = true;
                    students[s].setRegNo(regNo);
                }
            }

            System.out.println("Enter Name for student " + (s + 1) + " : ");
            String name = bf.readLine();
            students[s].setName(name);

            Date dob = null;
            SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
            System.out.println("Enter DOB (dd/MM/yyyy) for student " + (s + 1) + " : ");
            String input = bf.readLine();
            if (null != input && input.trim().length() > 0) {
                try {
                    dob = format.parse(input);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }

            students[s].setDob(dob);

            System.out.println("Enter Address for student " + (s + 1) + " : ");
            String addresString = bf.readLine();
            students[s].setAddress(addresString);

            System.out.println("Enter Mobile no for student " + (s + 1) + " : ");
            String mobileNo = bf.readLine();
            students[s].setMobileNo(mobileNo);
        }

        flag = false;

        System.out.println("\nADDING COURSES");
        System.out.println(".....................");
        System.out.println("\nEnter number of courses");
        int c = Integer.parseInt(bf.readLine());

        ArrayList<Course> courses = new ArrayList<>();
        ArrayList<String> coName = new ArrayList<>();
        ArrayList<String> coCode = new ArrayList<>();

        // Populating courses array with course objects.
        for (int i = 0; i < c; i++) {
            courses.add(new Course());
            flag = false;
            while (!flag) {
                System.out.println("Enter course code for course " + (i + 1) + ": ");
                String courseCode = bf.readLine();
                if (coCode.contains(courseCode)) {
                    System.out.println("Course code already exists");
                } else {
                    coCode.add(courseCode);
                    flag = true;
                    courses.get(i).setCourseCode(courseCode);
                }
            }

            flag = false;
            while (!flag) {
                System.out.println("Enter course name for course " + (i + 1) + ":");
                String courseName = bf.readLine();
                if (coName.contains(courseName)) {
                    System.out.println("Course name already exists");
                } else {
                    coName.add(courseName);
                    flag = true;
                    courses.get(i).setCourseName(courseName);
                }
            }

            flag = false;
            while (!flag) {
                System.out.println("Enter course credits for coures " + (i + 1) + ":");
                int courseCredits = Integer.parseInt(bf.readLine());
                if (courseCredits == 3 || courseCredits == 4) {
                    courses.get(i).setCredits(courseCredits);
                    flag = true;
                } else {
                    System.out.println("Course credits can be either 3 or 4");
                }
            }
        }

        System.out.println("\nALLOCATING COURSES");
        System.out.println(".......................");
        for (int a = 0; a < students.length; a++) {
            System.out.println("\nFor student " + (a + 1) + "");
            System.out.println("-----------------------------");
            System.out.println("Select the course code of the course you want toregister:\n");
            for (Course cr : courses) {
                System.out.println(cr.toString());
            }

            boolean check = true;

            while (check) {

                for (int i = 0; i < courses.size(); i++) {
                    System.out.println("Total credits registered : " +
                            students[a].getTotalCredits());
                    System.out.println("Enter course code: ");
                    String cn = bf.readLine();

                    if (coCode.contains(cn)) {
                        if (!(students[a].isCourseAllocated(cn))) {

                            if (students[a].getTotalCredits() < minCredit) {

                                students[a].addCourse(courses.get(coCode.indexOf(cn)));
                                System.out.println("Course allocated successfully ! .\n");
                            } else if (students[a].getTotalCredits() >= maxCredit) {
                                System.out.println("\nYou have registered for max no credits.\n");
                                check = false;

                            } else if ((students[a].getTotalCredits() >= minCredit)
                                    && (students[a].getTotalCredits() < maxCredit)) {
                                coCode.indexOf(cn);
                                students[a].addCourse(courses.get(coCode.indexOf(cn)));
                                System.out.println("\nCourse allocated successfully ! .\n");

                                System.out.println("\nTotal credits registered : " +
                                        students[a].getTotalCredits());
                                System.out.println(
                                        "You have registered your minimum no of credits. Do you want to register formore credits? (y/n)");
                                String ans = bf.readLine();
                                if (ans.equalsIgnoreCase("y")) {
                                } else if (ans.equalsIgnoreCase("n")) {
                                    check = false;
                                }
                            }
                        } else if ((students[a].isCourseAllocated(cn))) {
                            System.out.println("\nCourse already allocated");
                            check = true;
                        }
                    } else if (!(coCode.contains(cn))) {
                        System.out.println("\nCourse code does not exist !");
                    } else {
                        check = false;
                    }
                }
            }

            System.out.println("Courses Allocated for student " + (a + 1) + "\n");

        }

        System.out.println("Courses Allocation complete for all students\n");

        int choice = 0;
        do {
            System.out.println("\t\t------");
            System.out.println("\t\t MENU");
            System.out.println("\t\t------");
            System.out.println("1. Print all students' details");
            System.out.println("2. Print students who have registered for same courses' details");
            System.out.println("3. Print students results");
            System.out.println("4. Exit Program");
            System.out.println("\nEnter your choice below:");

            choice = Integer.parseInt(bf.readLine());
            switch (choice) {
                case 1:
                    System.out.println();
                    printStudentDetails(students);
                    break;
                case 2:
                    System.out.println();
                    studentsWithSameCourse(students);
                    break;
                case 3:
                    System.out.println();
                    studentsResult(students);
                    break;
                case 4:
                    choice = 4;
                    System.exit(0);
                default:
                    System.out.println("\nInvalid choice");
            }
        } while (choice != 4);

        bf.close();

        // End of main method.
    }

    // Method to print students details
    static void printStudentDetails(Student[] students) {
        for (Student s : students) {
            System.out.println(s.toString());
        }
    }

    // Method to print details of students registered for the same courses
    static void studentsWithSameCourse(Student[] students) {
        int flag = 0;
        for (int i = 0; i < students.length; i++) {
            for (int j = i + 1; j < students.length; j++) {
                if ((students[i].getAllotedCourses()).equals(students[j].getAllotedCourses())) {

                    flag = 1;
                    System.out.println("-----------------------------------------------------------------");
                    System.out.println("For these courses: " + (students[i].getAllotedCoursesString()) + "\n");
                    System.out.println("These students have registered : \n");
                    System.out.println("Name :" + students[i].getName() + ", Register No. :" + students[i].getRegNo());
                    System.out.println("Name :" + students[j].getName() + ", Register No. :" + students[j].getRegNo());
                    System.out.println("-----------------------------------------------------------------");
                }

            }
        }

        // in case no students have common courses.
        if (flag == 0) {
            System.out.println("No students are having the same courses");

        }
    }

    // Method to print students results
    static void studentsResult(Student[] students) {
        for (Student s : students) {
            s.setMarks();
            s.setGrades();

            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < s.getAllotedCourses().size(); i++) {
                sb.append(s.getAllotedCourses().get(i).getCourseName() + "\t\t\t\t  " + s.getGrades()[i] + "\n");
            }
            System.out.println("\t   Student Result\n\t   --------------\n\nRegister No: " + s.getRegNo() + "\t\t"
                    + "Name : "
                    + s.getName() + "\n" + "\nSubject\t\t\t\tGrades\n------------------------------------------\n" + sb
                    + "------------------------------------------\n\n\t\tGPA: " + s.getGPA() + "\n\n"
                    + "------------------------------------------");
        }
    }
}

// students[0] = new Student("001", "Anubhav", "08/11/2001", "Faridabad",
// "7217818288");
// students[1] = new Student("002", "Bhavik", new Date(02/02/2001), "Gujarat",
// "97876543210");
// students[2] = new Student("003", "Chetan", "3/3/2002", "Mumbai",
// "9451562948");
// students[3] = new Student("004", "Dheeraj", "4/4/2001", "Chennai",
// "7811556181");
// students[4] = new Student("005", "Eshan", "5/5/2002", "Delhi", "9894533210");
// students[5] = new Student("006", "Farhan", "6/6/2001", "Lucknow",
// "7893254160");
// students[6] = new Student("007", "Gauri", "7/7/2002", "Punjab",
// "9899955210");
// students[7] = new Student("008", "Harsh", "8/8/2001", "Kerela",
// "9786453021");
// students[8] = new Student("009", "Ishank", "9/9/2002", "Tamil Nadu",
// "8523697410");
// students[9] = new Student("010", "Jasmine", "10/10/2001", "West Bengal",
// "7987654321");

// courses[0] = new Course("CSE1002", "OOPS", 3);
// courses[1] = new Course("ITE1015", "SwE", 3);
// courses[2] = new Course("MAT2001", "Calc", 4);
// courses[3] = new Course("MAT3004", "Ala", 4);
// courses[4] = new Course("CSE1007", "Java", 3);
// courses[5] = new Course("ITE1004", "DSA", 3);
// courses[6] = new Course("ITE2002", "OS", 4);
// courses[7] = new Course("ITE3001", "DCCN", 4);
// courses[8] = new Course("ITE1003", "DLM", 4);
// courses[9] = new Course("ITE3002", "CAO", 3);

// students[0].setCourses(new Course[] { courses[0], courses[1], courses[2],
// courses[4] });
// students[1].setCourses(new Course[] { courses[3], courses[4], courses[5],
// courses[6] });
// students[2].setCourses(new Course[] { courses[7], courses[8], courses[9],
// courses[0] });
// students[3].setCourses(new Course[] { courses[1], courses[2], courses[3],
// courses[4] });
// students[4].setCourses(new Course[] { courses[5], courses[6], courses[7],
// courses[8] });
// students[5].setCourses(new Course[] { courses[9], courses[0], courses[1],
// courses[2] });
// students[6].setCourses(new Course[] { courses[3], courses[4], courses[5],
// courses[6] });
// students[7].setCourses(new Course[] { courses[7], courses[8], courses[9],
// courses[0] });
// students[8].setCourses(new Course[] { courses[1], courses[2], courses[3],
// courses[4] });
// students[9].setCourses(new Course[] { courses[5], courses[6], courses[7],
// courses[8] });

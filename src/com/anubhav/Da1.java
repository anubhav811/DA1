
// Anubhav Chachra
// 20BIT0104

package com.anubhav;

import java.text.*;
import java.util.*;
import com.anubhav.*;

public class Da1 {

    // Constants
    final static int minCredit = 12;
    final static int maxCredit = 24;

    static String[] coName;
    static String[] coCode;

    public static void main(String[] args) throws InstantiationException {

        // Declaring students and courses arrayss.

        Scanner sc = new Scanner(System.in);

        System.out.println("Enter number of students");
        int n = sc.nextInt();

        Student[] students = new Student[n];
        String[] reg = new String[n];
        for (int i = 0; i < n; i++) {
            reg[i] = "";
        }

        boolean flag = false;

        // Populating students array with student objects.
        for (int s = 0; s < n; s++) {

            students[s] = new Student("", "", new Date(), "", "");

            while (!flag) {
                System.out.println("Enter register no: ");
                String regNo = sc.nextLine();
                for (String rgn : reg) {
                    if (rgn.equals(regNo)) {
                        System.out.println("Register no already exists");
                    } else {
                        rgn = regNo;
                        flag = true;
                        students[s].setRegNo(regNo);
                    }
                }
            }

            System.out.println("Enter Name: ");
            String name = sc.nextLine();
            students[s].setName(name);

            Date dob = null;
            SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
            System.out.println("Enter DOB (dd/MM/yyyy) : ");
            String input = sc.nextLine();
            if (null != input && input.trim().length() > 0) {
                try {
                    dob = format.parse(input);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }

            students[s].setDob(dob);

            System.out.println("Enter Address: ");
            String addresString = sc.nextLine();
            students[s].setAddress(addresString);

            System.out.println("Enter Mobile no: ");
            String mobileNo = sc.nextLine();
            students[s].setMobileNo(mobileNo);
        }

        flag = false;
        System.out.println("Enter number of courses");
        int c = sc.nextInt();
        Course[] courses = new Course[c];
        coName = new String[c];
        coCode = new String[c];

        // Populating courses array with course objects.
        for (int i = 0; i < c; i++) {

            courses[i] = new Course("", "", 0);

            while (!flag) {
                System.out.println("Enter course code: ");
                String courseCode = sc.nextLine();
                for (String str : coCode) {
                    if (str == courseCode) {
                        System.out.println("Course code already exists");
                    } else {
                        str = courseCode;
                        flag = true;
                        courses[i].setCourseCode(courseCode);
                    }
                }
            }

            flag = false;
            while (!flag) {
                System.out.println("Enter course name: ");
                String courseName = sc.nextLine();
                for (String str : coName) {
                    if (str == courseName) {
                        System.out.println("Course name already exists");
                    } else {
                        str = courseName;
                        flag = true;
                        courses[i].setCourseName(courseName);
                    }
                }
            }

            flag = false;
            while (!flag) {
                System.out.println("Enter course credits: ");
                int courseCredits = sc.nextInt();
                if (courseCredits == 3 || courseCredits == 4) {
                    courses[i].setCredits(courseCredits);
                    flag = true;
                } else {
                    System.out.println("Course credits can be either 3 or 4");
                }
            }
        }

        // Allocating courses to students

        for (int a = 0; a < students.length; a++) {
            System.out.println("Allocating course for Student " + (a + 1));
            System.out.println("Select the course code of the course you want to register :\n");
            for (Course cr : courses) {
                System.out.println(cr.toString());
            }

            String cn;
            int noOfCourse = 0;
            boolean check = false;

            while (!check) {
                for (int i = 0; i < courses.length; i++) {
                    System.out.println("Enter course code: ");
                    cn = sc.next();

                    if (courses[i].getCourseName().equals(cn) && !students[a].isCourseAllocated(cn)) {
                        if ((students[a].getTotalCredits() < minCredit) && (students[a].getTotalCredits() > 0)) {
                            System.out.println("You have not registered for min no credits i.e 12 , yet");
                            students[a].setCourses(courses[i]);
                            noOfCourse++;
                            System.out.println("Course allocated successfully ! . Total credits : "
                                    + students[a].getTotalCredits());
                            check = true;
                        }

                        if (students[a].getTotalCredits() >= maxCredit) {
                            System.out.println("You have registered for max no credits i.e 24");
                            check = false;
                        }

                    } else if (students[a].isCourseAllocated(cn)) {
                        System.out.println("Course already allocated !");
                        check = true;
                    } else {
                        System.out.println("Course code does not exist !");
                        check = true;
                    }
                }

            }
        }

        // Allocating courses to students.

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

        sc.close();
        // Displaying students details.
        // printStudentDetails(students);

        // // Students who have registered for the same courses.
        // studentsWithSameCourse(students);

        // // Results of students
        // studentResult(students);

        // End of main method.
    }

    static void printStudentDetails(Student[] students) {
        for (Student s : students) {
            System.out.println(s.toString());
        }
    }

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

    static void studentResult(Student[] students) {
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
// students[1] = new Student("002", "Bhavik", "2/2/2001", "Gujarat",
// "9876543210");
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

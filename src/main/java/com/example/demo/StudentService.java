package com.example.demo;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/*Service (business logic)
Tee palveluluokka, joka tarjoaa metodit tiedon hallintaan. Käytännössä tämä luokka sisältää esim. toiminnot,
joilla opiskelijat lisätään kurssille, lisätään uusia opiskelijoita tai haetaan tietoja jne. Tyypillisesti kontrolleri
sisältää esim. koosteita, joissa datamallit ovat. Lisähaaste: Tee kontrollerista ensin interface, joka määrittelee
tarvittavat toiminnot ja toteuta sitten kyseinen rajapinta.
REST-rajapinta tulee käyttämään tätä luokkaa tiedon hakuun jne. */
public interface StudentService {
    public void CreateStudent(String firstName, String lastName);
    String CreateStudentID(String firstName, String lastName);
    public void CreateOnlineCourse(String id, String name, String teacher, String startDate, String endDate, int credit, String remoteLink, String info);
    public void CreateClassRoomCourse(String id, String name, String teacher, String startDate, String endDate, int credit, String classRoom, String info);
    public void AddStudentToCourse(String studentID, String courseID);
    public void RemoveStudentFromCourse(String studentID, String courseID);
    public List<Student> GetAllStudents();
    public List<Course> GetAllCourses();
    public void SetLoadedCourses(List<Course> courses);
    public void SetLoadedStudents(List<Student> students);
    public Course FindCourseByID(String id);
    public void DeleteStudent(String id);
    public void DeleteCourse(String id);
}

class ServiceInstance implements StudentService {
    List<Student> students;
    List<Course> courses;

    public ServiceInstance(){
        students = new ArrayList<Student>();
        courses = new ArrayList<Course>();
    }

    //Creates a new student with firstName and lastName
    public void CreateStudent(String firstName, String lastName){
        students.add(new Student(firstName, lastName, CreateStudentID(firstName, lastName)));
        System.out.println("Created student " + students.get(students.size() -1 ).GetFirstName() + " " + students.get(students.size() -1 ).GetLastName() + " with id " + students.get(students.size() -1 ).GetID());
        
        try {
            Application.fileService.SaveStudentInfo(students);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Creates a unique student ID from first name, last name, the 
    // last two digits of the current year and checks for duplicates to
    // create a unique key
    public String CreateStudentID(String firstName, String lastName){
        char[] id = new char[8];
        id[0] = Character.toLowerCase(firstName.charAt(0));
        id[1] = Character.toLowerCase(firstName.charAt(1));
        id[2] = Character.toLowerCase(lastName.charAt(0));
        id[3] = Character.toLowerCase(lastName.charAt(1));
        String year = Integer.toString(Calendar.getInstance().get(Calendar.YEAR));
        id[4] = year.charAt(2);
        id[5] = year.charAt(3);
        id[6] = '0';
        id[7] = '0';
        int unique = 0;
        
        //Checks for duplicates, only goes up to 9 because I don't want to spend too
        //much time at this since this is irrelevant
        for(Student student : students){
            if(unique<9) if(student.GetID().equals(new String(id))) unique++;
        }
        id[7] = String.valueOf(unique).charAt(0);

        return String.valueOf(id);
    }


    //Creates a new online course with a remote link to a meeting/live stream
    public void CreateOnlineCourse(String id, String name, String teacher, String startDate, String endDate, int credit, String remoteLink, String info){
        courses.add(new OnlineCourse(id, name, teacher, startDate, endDate, credit, remoteLink, info));
        System.out.println("Created course " + courses.get(courses.size() - 1).GetID() + " " + courses.get(courses.size() - 1).GetName());
        try {
            Application.fileService.SaveCourseInfo(courses);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //Creates a new classroom course with a classroom number
    public void CreateClassRoomCourse(String id, String name, String teacher, String startDate, String endDate, int credit, String classRoom, String info){
        courses.add(new ClassRoomCourse(id, name, teacher, startDate, endDate, credit, classRoom, info));
        System.out.println("Created course " + courses.get(courses.size() - 1).GetID() + " " + courses.get(courses.size() - 1).GetName());
        try {
            Application.fileService.SaveCourseInfo(courses);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void AddStudentToCourse(String studentID, String courseID){
        Course course = FindCourseByID(courseID);
        Student student = FindStudentByID(studentID);

        if(course == null || student == null) {
            System.out.println(course + " is null or " + student + " is null");
            return;
        }

        if(course.students.contains(student)){
            System.out.println("Student already on course");
            return;
        }
        course.AddStudentToCourse(student);
        student.AddToCourse(course);
        System.out.println("Added student " + student.GetFirstName() + " " + student.GetLastName() + " to course " + course.GetID() + " " + course.GetName());
        try {
            Application.fileService.SaveCourseInfo(courses);
            Application.fileService.SaveStudentInfo(students);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void RemoveStudentFromCourse(String studentID, String courseID){
        Course course = FindCourseByID(courseID);
        Student student = FindStudentByID(studentID);

        if(course == null || student == null) {
            System.out.println(course + " is null or " + student + " is null");
            return;
        }

        if(!course.students.contains(student)){
            System.out.println("Student not on course");
            return;
        }
        course.RemoveStudentFromCourse(student);
        student.RemoveFromCourse(course);
        System.out.println("Removed student " + student.GetFirstName() + " " + student.GetLastName() + " from course " + course.GetID() + " " + course.GetName());
        
        try {
            Application.fileService.SaveCourseInfo(courses);
            Application.fileService.SaveStudentInfo(students);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<Student> GetAllStudents(){
        return students;
    }
    public List<Course> GetAllCourses(){
        return courses;
    }

    public Student FindStudentByID(String id){
        for(Student s : students){
            if(id.trim().equals(s.GetID())) return s;
        }
        return null;
    }

    public Course FindCourseByID(String id){
        for(Course c : courses){
            if(id.trim().equals(c.GetID())) return c;
        }
        return null;
    }

    public void SetLoadedCourses(List<Course> courses){
        if(courses == null) return;
        this.courses = courses;
    }

    public void SetLoadedStudents(List<Student> students){
        if(students == null) return;
        this.students = students;
    }

    //Removes student from students list and from any courses where the student is enlisted
    public void DeleteStudent(String id){
        Student student = FindStudentByID(id);
        if(students.contains(student)) {
            students.remove(student);
            for(Course c : courses){
                if(c.students.contains(student)) c.students.remove(student);
            }
        }
        else System.out.println("Student doesnt exist with this id");

        try {
            Application.fileService.SaveStudentInfo(students);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //Deletes course from courses list and from any students who have course in their courselist
    public void DeleteCourse(String id){
        Course course = FindCourseByID(id);
        if(courses.contains(course)) {
            courses.remove(course);
            for(Student s : students){
                if(s.courses.contains(course)) s.courses.remove(course);
            }
        }
        else System.out.println("Course doesnt exist with this id");

        try {
            Application.fileService.SaveCourseInfo(courses);
        } catch (IOException e) {
            e.printStackTrace();
        }
        
    }
}
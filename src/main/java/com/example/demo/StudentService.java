package com.example.demo;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import org.springframework.ui.Model;

public interface StudentService {
    public void CreateStudent(String firstName, String lastName)  throws IOException;
    String CreateStudentID(String firstName, String lastName);
    public void CreateOnlineCourse(String id, String name, String teacher, String startDate, String endDate, int credit, String remoteLink, String info)  throws IOException;
    public void CreateClassRoomCourse(String id, String name, String teacher, String startDate, String endDate, int credit, String classRoom, String info)  throws IOException;
    public void AddStudentToCourse(String studentID, String courseID)  throws IOException;
    public void RemoveStudentFromCourse(String studentID, String courseID)  throws IOException;
    public List<Student> GetAllStudents();
    public List<Course> GetAllCourses();
    public void SetLoadedCourses(List<Course> courses);
    public void SetLoadedStudents(List<Student> students);
    public Course FindCourseByID(String id);
    public void EditCourse(String courseID, String id, String name, String teacher, String startDate, String endDate, int credit, String location, String info);
    public void DeleteStudent(String id) throws IOException;
    public void DeleteCourse(String id) throws IOException;
    public Model GetCourseInfo(String courseID, Model model);
}

class ServiceInstance implements StudentService {
    List<Student> students;
    List<Course> courses;
    
    public ServiceInstance(){
        students = new ArrayList<Student>();
        courses = new ArrayList<Course>();
    }

    //Creates a new student with firstName and lastName
    public void CreateStudent(String firstName, String lastName) throws IOException{
        students.add(new Student(firstName, lastName, CreateStudentID(firstName, lastName)));
        System.out.println("Created student " + students.get(students.size() -1 ).GetFirstName() + " " + students.get(students.size() -1 ).GetLastName() + " with id " + students.get(students.size() -1 ).GetID());
        Application.fileService.SaveStudentInfo(students);
    }
    
    // Creates a unique student ID from first name, last name, the last two digits of the current year and checks for duplicates to create a unique key
    public String CreateStudentID(String firstName, String lastName){
        String year = Integer.toString(Calendar.getInstance().get(Calendar.YEAR));
        char[] id = new char[8];
        id[0] = year.charAt(2);
        id[1] = year.charAt(3);
        id[2] = Character.toLowerCase(firstName.charAt(0));
        id[3] = Character.toLowerCase(firstName.charAt(1));
        id[4] = Character.toLowerCase(lastName.charAt(0));
        id[5] = Character.toLowerCase(lastName.charAt(1));
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
    public void CreateOnlineCourse(String id, String name, String teacher, String startDate, String endDate, int credit, String remoteLink, String info) throws IOException{
        courses.add(new OnlineCourse(id, name, teacher, startDate, endDate, credit, remoteLink, info));
        System.out.println("Created course " + courses.get(courses.size() - 1).GetID() + " " + courses.get(courses.size() - 1).GetName());
        Application.fileService.SaveCourseInfo(courses);
    }

    //Creates a new classroom course with a classroom number
    public void CreateClassRoomCourse(String id, String name, String teacher, String startDate, String endDate, int credit, String classRoom, String info) throws IOException{
        courses.add(new ClassRoomCourse(id, name, teacher, startDate, endDate, credit, classRoom, info));
        System.out.println("Created course " + courses.get(courses.size() - 1).GetID() + " " + courses.get(courses.size() - 1).GetName());
        Application.fileService.SaveCourseInfo(courses);
    }

    //If student found and course found, add student to course
    public void AddStudentToCourse(String studentID, String courseID)  throws IOException{
        System.out.println("AddStudentToCourse() " + studentID + " " + courseID);
        Course course = FindCourseByID(courseID);
        Student student = FindStudentByID(studentID);

        if(course == null || student == null) {
            System.out.println(course + " is null or " + student + " is null");
            return;
        }

        for(Student s : course.students){
            if(s.GetID().equals(studentID)){
                System.out.println("Student already on course");
                return;
            }
        }

        course.AddStudentToCourse(student);
        System.out.println("Added student " + student.GetFirstName() + " " + student.GetLastName() + " to course " + course.GetID() + " " + course.GetName());
        Application.fileService.SaveCourseInfo(courses);
        Application.fileService.SaveStudentInfo(students);
    }

    //If student found on course, remove student from course
    public void RemoveStudentFromCourse(String studentID, String courseID) throws IOException{
        Course course = FindCourseByID(courseID);
        Student student = FindStudentByID(studentID);
        if(course == null || student == null) {
            System.out.println(course + " is null or " + student + " is null");
            return;
        }
        course.RemoveStudentFromCourse(student);
        System.out.println("Removed student " + student.GetFirstName() + " " + student.GetLastName() + " from course " + course.GetID() + " " + course.GetName());
        Application.fileService.SaveCourseInfo(courses);
        Application.fileService.SaveStudentInfo(students);
    }

    
    //Removes student from students list and from any courses where the student is enlisted
    public void DeleteStudent(String id) throws IOException{
        Student student = FindStudentByID(id);
        if(students.contains(student)) {
            students.remove(student);
            for(Course c : courses){
                if(c.students.contains(student)) c.students.remove(student);
            }
        }
        else System.out.println("Student doesnt exist with this id");

       Application.fileService.SaveStudentInfo(students);
    
    }

    //Removes course from courses list
    public void DeleteCourse(String id) throws IOException{
        Course course = FindCourseByID(id);
        if(courses.contains(course)) courses.remove(course);
        else System.out.println("Course doesnt exist with this id");
        Application.fileService.SaveCourseInfo(courses);
    }


    //Edit course after checking for subclass
    public void EditCourse(String courseID, String id, String name, String teacher, String startDate, String endDate, int credit, String location, String info){
        Course c = Application.studentService.FindCourseByID(courseID);
        
        if(c instanceof OnlineCourse){
            OnlineCourse oc = (OnlineCourse) c;
            oc.EditCourse(id, name, teacher, startDate, endDate, credit, location, info);
        }
        else if(c instanceof ClassRoomCourse) {
            ClassRoomCourse cc = (ClassRoomCourse) c;
            cc.EditCourse(id, name, teacher, startDate, endDate, credit, location, info);
        }
    }

    //Get course info after checking subclass
    public Model GetCourseInfo(String courseID, Model model){
        Course course = Application.studentService.FindCourseByID(courseID);
        model.addAttribute("course", course);

        if(course instanceof OnlineCourse){
            OnlineCourse oc = (OnlineCourse) course;
            model.addAttribute("location", oc.GetLink());
        }
        if(course instanceof ClassRoomCourse){
            ClassRoomCourse cc = (ClassRoomCourse) course;
            model.addAttribute("location", cc.GetClassRoom());
        }
        return model;
    }

    //Get all students and courses
    public List<Student> GetAllStudents(){ return students;}
    public List<Course> GetAllCourses(){ return courses; }

    //Find by id
    public Course FindCourseByID(String id){
        for(Course c : courses){ if(id.trim().equals(c.GetID())) return c; }
        return null;
    }

    public Student FindStudentByID(String id){
        for(Student s : students){ if(id.trim().equals(s.GetID())) return s; }
        return null;
    }

    //Set lists loaded via fileservice
    public void SetLoadedCourses(List<Course> courses){
        if(courses != null)  this.courses = courses;
    }

    public void SetLoadedStudents(List<Student> students){
        if(students != null) this.students = students;
    }

}
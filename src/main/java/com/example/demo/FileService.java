package com.example.demo;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

//A class to track the info and execute some functions of a file
class FileInfo {
    File file;
    String path;

    FileInfo(String path){
        this.path = path;
    }

    public void NewFile() throws IOException{
        file = new File(path);
        file.createNewFile();
        file.canRead();
        file.canWrite();
    }
}

public class FileService implements Serializable {
    FileInfo studentData;
    FileInfo courseData;

    public FileService(){
        //Paths go to project root
        studentData = new FileInfo("StudentInfo.dat");
        courseData  = new FileInfo("CourseInfo.dat");
    }

    
    // Saves the Student list as an object to the path designated in the studentData FileInfo
    public void SaveStudentInfo(List<Student> students) throws IOException{
        FileOutputStream stream = new FileOutputStream(studentData.path);
        ObjectOutputStream objStream = new ObjectOutputStream(stream);
        objStream.writeObject(students);
        objStream.close();
        System.out.println("Saved student data");
    }

    // Saves the Course list as an object to the path designated in the courseData FileInfo
    public void SaveCourseInfo(List<Course> courses) throws IOException {
        FileOutputStream stream = new FileOutputStream(this.courseData.path);
        ObjectOutputStream objStream = new ObjectOutputStream(stream);
        objStream.writeObject(courses);
        objStream.close();
        System.out.println("Saved course data");
    }

    //Loads student info from file
    public List<Student> LoadStudentInfo() throws IOException{
        if(!new File(this.studentData.path).exists()) return null;
        ArrayList<Student> students = new ArrayList<Student>();
        Iterable<?> array = (Iterable<?>) ReadStream(this.studentData.path);
        for(Object o : array) students.add((Student)o);
        return students;
    }
    
    //Loads course info from file
    public List<Course> LoadCourseInfo() throws IOException{
        if(!new File(this.courseData.path).exists()) return null;
        ArrayList<Course> courses = new ArrayList<Course>();
        Iterable<?> array = (Iterable<?>) ReadStream(this.courseData.path);
        for(Object o : array) courses.add((Course)o);
        return courses;
    }

    //Reads an object via ObjectInputStream with the given path
    Object ReadStream(String path) throws IOException{
        ObjectInputStream stream = new ObjectInputStream(new FileInputStream(path));

        Object object = null;
        try {
            object = stream.readObject();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        stream.close();
        return object;
    }
}

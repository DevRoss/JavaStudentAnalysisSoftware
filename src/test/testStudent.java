package test;

import com.ross.Course;
import com.ross.Student;

import java.io.IOException;

public class testStudent {
    public static void main(String[] args) throws IOException{
        Student student = new Student("testStudent");
        for (Course course : student.getCourses()) {
            System.out.println(course.getName());
            System.out.println(course.getScore());

        }
    }

}

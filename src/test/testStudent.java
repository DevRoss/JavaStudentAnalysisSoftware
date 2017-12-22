package test;

import com.ross.model.Course;
import com.ross.model.Student;

import java.io.IOException;

public class testStudent {
    public static void main(String[] args) throws IOException {
        Student student = new Student("11111111111111", "testStudent");
        System.out.println(student.getNum() + "\t" + student.getName());
        for (Course course : student.getCourses()) {
            System.out.println(course.getName() + "\t" + course.getScore());

        }
    }

}

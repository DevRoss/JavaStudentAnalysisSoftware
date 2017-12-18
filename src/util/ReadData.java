package util;

import com.ross.Course;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * 读取文件中的数据到内存中
 */

public class ReadData {


    // 从course.txt读取课程，返回 ArrayList<Course>
    public static ArrayList<Course> readCourseFile() throws IOException {
        ArrayList<Course> courses = new ArrayList<Course>();
        try (BufferedReader courseFile = new BufferedReader(new FileReader("course.txt"))) {
            String courseName;
            while ((courseName = courseFile.readLine()) != null) {
                courses.add(new Course(courseName));
            }
        }
        return courses;
    }
}

package util;

import com.ross.Course;
import com.ross.ScoreTable;

import java.io.*;
import java.util.ArrayList;

/**
 * 读取文件中的数据到内存中
 * 数据文件夹为 resource/
 * 文件格式为 className-courseName.dat
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

    // 从.dat文件中读取数据到内存中
    public static ScoreTable readTableData(String className, String courseName)
            throws FileNotFoundException, IOException, ClassNotFoundException {
        String filePath = "resource/" + className + "-" + courseName + ".dat";
        try (ObjectInputStream tableObj = new ObjectInputStream(new FileInputStream(filePath))) {
            return (ScoreTable) tableObj.readObject();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }
}

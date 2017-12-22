package util;

import com.ross.model.Course;
import com.ross.model.ScoreTable;
import com.ross.model.Student;

import java.io.*;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 读取文件中的数据到内存中
 * 数据文件夹为 resource/
 * 文件格式为 className-courseName.dat
 */

public class ReadData {

    // .dat文件的文件夹
    final static String resourcePrefix = "resource/";

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

    public static ArrayList<Student> readStudentFile(String filePath) throws IOException {
        ArrayList<Student> students = new ArrayList<Student>();
        try (BufferedReader studentFile = new BufferedReader(new FileReader(filePath))) {
            Pattern r = Pattern.compile("(\\d+) (.+)");
            String line;
            String num;
            String name;
            while ((line = studentFile.readLine()) != null) {
                Matcher m = r.matcher(line);
                if (m.find()) {
                    num = m.group(1);
                    name = m.group(2);
                    students.add(new Student(num, name));
                }
            }
        }
        return students;
    }

    // 从.dat文件中读取数据到内存中
    public static ScoreTable readTableData(String filePath)
            throws FileNotFoundException, IOException, ClassNotFoundException {
//        String filePath = "resource/" + className + "-" + courseName + ".dat";
        try (ObjectInputStream tableObj = new ObjectInputStream(new FileInputStream(filePath))) {
            return (ScoreTable) tableObj.readObject();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    // 获得去不带扩展名的文件名
    public static String getFileNameNoEx(String filename) {
        if ((filename != null) && (filename.length() > 0)) {
            int dot = filename.lastIndexOf('.');
            if ((dot > -1) && (dot < (filename.length()))) {
                return filename.substring(0, dot);
            }
        }
        return filename;
    }

    public static String getDatPath(File studentFile, String courseName) {
        return resourcePrefix + getFileNameNoEx(studentFile.getName()) + "-" + courseName + ".dat";
    }

}

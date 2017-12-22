package test;

import com.ross.model.Student;
import util.ReadData;

import java.io.IOException;
import java.util.ArrayList;

public class testReadFile {
    static ArrayList<Student> students;

    public static void main(String[] args) throws IOException {
        students = ReadData.readStudentFile("2011级网络工程1k.txt");
        Integer count = 0;
        for (Student student : students) {
            count++;
            if (count % 100 == 0) {
                System.out.printf("%s %s\n", student.getNum(), student.getName());
            }
        }
        System.out.printf("共有%d个\n", count);
    }
}

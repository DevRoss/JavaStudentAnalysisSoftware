package test;

import com.ross.ScoreTable;
import com.ross.Student;
import util.WriteData;

import java.io.IOException;
import java.util.ArrayList;

public class testTable {

    static ArrayList<Student> students = new ArrayList<Student>();

    static ScoreTable scoreTable;

    public static void main(String[] args) throws IOException {
        students.add(new Student("1234567898", "阿姆"));
        scoreTable = new ScoreTable(students, "数据库系统");
        WriteData.writeTable2File(scoreTable, "2016软工", "数据结构");
    }
}

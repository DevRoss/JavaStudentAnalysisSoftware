package test;

import com.ross.model.ScoreTable;
import com.ross.model.Student;
import com.ross.model.TableItem;
import util.ReadData;
import util.WriteData;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class testTable {

    static ArrayList<Student> students = new ArrayList<Student>();

    static ScoreTable scoreTable;

    public static void main(String[] args) throws IOException, ClassNotFoundException {
//        students.add(new Student("1234567898", "阿姆"));
//        students.add(new Student("123456789", "姆"));
        students = ReadData.readStudentFile("2011级网络工程1k.txt");
        scoreTable = new ScoreTable(students, "数据库系统");
        WriteData.writeTable2File(scoreTable, "2011级网络工程1k", "数据库系统");

        scoreTable = ReadData.readTableData(ReadData.getDatPath(new File("2011级网络工程1k.txt"), "数据库系统"));
        for (TableItem tableItem : scoreTable.getItems()) {
            System.out.println(tableItem.num.get() + "\t" + tableItem.name.get() + "\t" +tableItem.score.getValue());
        }
    }
}

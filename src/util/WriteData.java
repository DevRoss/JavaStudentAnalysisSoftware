package util;

import com.ross.model.ScoreTable;

import java.io.*;

/**
 * 持久化数据
 */
public class WriteData {

    // 传入成绩单对象，将对象储存但文件中
    public static void writeTable2File(ScoreTable scoreTable, String className, String courseName) throws FileNotFoundException, IOException {
        String filePath = "resource/" + className + "-" + courseName + ".dat";

        File f = new File("resource/");
        if (!f.exists()) f.mkdir();

        try (ObjectOutputStream _scoreTable = new ObjectOutputStream(new FileOutputStream(filePath))) {

            _scoreTable.writeObject(scoreTable);
        }
    }

}

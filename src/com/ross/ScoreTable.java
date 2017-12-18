package com.ross;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * 成绩单对象，里面存储某门课的学生成绩
 */
public class ScoreTable implements Serializable{
    private ArrayList<TableItem> items;
    private Double score;

    public ScoreTable(ArrayList<Student> students, String course) {
        this.items = new ArrayList<TableItem>();
        for (Student student : students) {
            this.items.add(new TableItem(student.getName(), student.getNum(), student.getCourseScore(course)));
        }
    }
}

class TableItem implements Serializable{
    private String name;
    private String num;
    private Double score;

    TableItem(String name, String num, Double score) {
        this.name = name;
        this.num = num;
        this.score = score;
    }
}

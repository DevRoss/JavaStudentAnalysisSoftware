package com.ross;

import java.io.Serializable;
import java.util.ArrayList;


/**
 * 成绩单对象，里面存储某门课的学生成绩
 */
public class ScoreTable implements Serializable{
    private ArrayList<TableItem> items;


    public ScoreTable(ArrayList<Student> students, String course) {
        this.items = new ArrayList<TableItem>();
        for (Student student : students) {
            this.items.add(new TableItem(student.getName(), student.getNum(), student.getCourseScore(course)));
        }
    }

    public ArrayList<TableItem> getItems() {
        return items;
    }
}


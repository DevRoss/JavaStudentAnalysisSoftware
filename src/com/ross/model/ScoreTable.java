package com.ross.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;


/**
 * 成绩单对象，里面存储某门课的学生成绩
 */
public class ScoreTable implements Serializable {
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

    public Integer getSize() {
        return items.size();
    }

    // 返回在区间 [min, max) 的人数
    public Integer range(Double min, Double max) {
        Integer count = 0;
        for (TableItem item : items) {
            if (min <= item.score.getValue() && item.score.getValue() < max) {
                count++;
            }
        }
        return count;
    }

    // 返回在区间 [min, max) 的人数百分比
    public Double rangePercentage(Double min, Double max) {
        return range(min, max) / getSize().doubleValue();
    }

    public Double getHighest() {
        return Collections.max(items).getScore();
    }

    public Double getLowest() {
        return Collections.min(items).getScore();
    }

    public Double getAvg() {
        Double sum = 0.0;
        for (TableItem i : items) sum += i.getScore();
        return sum / this.getSize();
    }

}


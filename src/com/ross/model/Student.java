package com.ross.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import util.ReadData;

import java.io.IOException;
import java.util.ArrayList;

public class Student {

    private ArrayList<Course> courses;
    private StringProperty name;
    private StringProperty num;

    public Student(String num, String name) throws IOException {
//        this.num = num;
//        this.name = name;
        this.num = new SimpleStringProperty(num);
        this.name = new SimpleStringProperty(name);

        this.courses = ReadData.readCourseFile();
    }

    public String getName() {
        return name.get();
    }

    public ArrayList<Course> getCourses() {
        return courses;
    }

    public String getNum() {
        return num.get();
    }

    Double getCourseScore(String course) {
        for (Course _course : courses) {
            if (_course.getName().equals(course))
                return _course.getScore();
        }
        return null;
    }
}

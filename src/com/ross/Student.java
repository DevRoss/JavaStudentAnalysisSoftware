package com.ross;

import util.ReadData;

import java.io.IOException;
import java.util.ArrayList;

public class Student {

    private ArrayList<Course> courses;
    private String name;
    private String num;

    public Student(String num, String name) throws IOException {
        this.num = num;
        this.name = name;
        this.courses = ReadData.readCourseFile();
    }

    public String getName() {
        return name;
    }

    public ArrayList<Course> getCourses() {
        return courses;
    }

    public String getNum() {
        return num;
    }

    Double getCourseScore(String course) {
        for (Course _course : courses) {
            if (_course.getName().equals(course))
                return _course.getScore();
        }
        return null;
    }
}

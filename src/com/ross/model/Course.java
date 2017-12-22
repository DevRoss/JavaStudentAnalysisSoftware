package com.ross.model;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Course {
    private StringProperty name;
    private DoubleProperty score;

    public Course(String name, Double score) {
        this.name = new SimpleStringProperty(name);
        this.score = new SimpleDoubleProperty(score);
    }

    public Course(String name) {
        this.name = new SimpleStringProperty(name);
        this.score = new SimpleDoubleProperty(0.00);
    }

    public void setScore(Double score) {
        this.score = new SimpleDoubleProperty(score);
    }

    public String getName() {
        return name.get();
    }

    public Double getScore() {
        return score.get();
    }
}

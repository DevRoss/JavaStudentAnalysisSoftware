package com.ross;

public class Course {
    private String name;
    private Double score;

    public Course(String name, Double score) {
        this.name = name;
        this.score = score;
    }

    public Course(String name) {
        this.name = name;
        this.score = 0.00;
    }

    public void setScore(Double score) {
        this.score = score;
    }

    public String getName() {
        return name;
    }

    public Double getScore() {
        return score;
    }
}

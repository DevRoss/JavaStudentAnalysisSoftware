package com.ross.model;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ObservableValue;

import java.io.Serializable;

public class TableItem implements Serializable {
    public StringProperty name;
    public StringProperty num;
    public ObservableValue<Double> score;

    public TableItem(String name, String num, Double score) {
        this.name = new SimpleStringProperty(name);
        this.num = new SimpleStringProperty(num);
        this.score = new SimpleDoubleProperty(score).asObject();
    }

    public Double getScore() {
        return score.getValue();
    }

    public String getName() {
        return name.get();
    }

    public String getNum() {
        return num.get();
    }
}

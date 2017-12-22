package com.ross.model;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ObservableValue;

import java.io.*;

public class TableItem implements Externalizable {
    public StringProperty name;
    public StringProperty num;
    public ObservableValue<Double> score;

    public TableItem(){
        super();
    }

    public TableItem(String name, String num, Double score) {
        this.name = new SimpleStringProperty(name);
        this.num = new SimpleStringProperty(num);
        this.score = new SimpleDoubleProperty(score).asObject();
//        setName(name);
//        setNum(num);
//        setScore(score);
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

    public void setName(String name) {
        this.name.set(name);
    }

    public void setNum(String num) {
        this.num.set(num);
    }

    public void setScore(Double score) {
        this.score = new SimpleDoubleProperty(score).asObject();
    }

//    @Override
//    private void writeObject(ObjectOutputStream out) throws IOException {
//        out.defaultWriteObject();
//        out.writeObject(getName());
//        out.writeObject(getNum());
//        out.writeObject(getScore());
//    }
//    @Override
//    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
//        this.setName((String) in.readObject());
//        this.setNum((String) in.readObject());
//        this.setScore((Double) in.readObject());
//    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        this.name = new SimpleStringProperty((String) in.readUTF());
        this.num = new SimpleStringProperty((String) in.readUTF());
        this.score = new SimpleDoubleProperty((Double) in.readDouble()).asObject();
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeUTF(getName());
        out.writeUTF(getNum());
        out.writeDouble(getScore());
    }
}

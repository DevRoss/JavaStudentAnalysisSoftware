package com.ross;

import java.io.Serializable;

public class TableItem implements Serializable {
    public String name;
    public String num;
    public Double score;

    public TableItem(String name, String num, Double score) {
        this.name = name;
        this.num = num;
        this.score = score;
    }
}

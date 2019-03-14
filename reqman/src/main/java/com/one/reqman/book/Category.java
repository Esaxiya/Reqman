package com.one.reqman.book;

import javax.persistence.Entity;
import java.io.Serializable;

@Entity
public class Category implements Serializable {

    private static final  long SerialVersionUID=1904980719741010798L;

    private int id;

    private String name;

    public Category() {
    }

    public static long getSerialVersionUID() {
        return SerialVersionUID;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Category{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}

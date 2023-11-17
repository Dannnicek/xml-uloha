package com.idea.entity;

import jakarta.persistence.*;

@Entity
public class Obec {

    @Id
    private String code;
    private String name;

    public Obec() {
    }

    public Obec(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

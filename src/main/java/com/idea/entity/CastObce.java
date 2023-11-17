package com.idea.entity;

import jakarta.persistence.*;

@Entity
public class CastObce {

    @Id
    private String code;
    private String name;

    @ManyToOne
    @JoinColumn(name = "obec_code")
    private Obec villageBelongsToCode;

    public CastObce() {
    }

    public CastObce(String code, String name, Obec villageBelongsToCode) {
        this.code = code;
        this.name = name;
        this.villageBelongsToCode = villageBelongsToCode;
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

    public Obec getvillageBelongsToCode() {
        return villageBelongsToCode;
    }

    public void setvillageBelongsToCode(Obec villageBelongsToCode) {
        this.villageBelongsToCode = villageBelongsToCode;
    }
}

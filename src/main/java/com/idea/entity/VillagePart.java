package com.idea.entity;

import jakarta.persistence.*;

@Entity
public class VillagePart {

    @Id
    private String code;
    private String name;

    @ManyToOne
    @JoinColumn(name = "obec_code")
    private Village villageBelongsToCode;

    public VillagePart() {
    }

    public VillagePart(String code, String name, Village villageBelongsToCode) {
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

    public Village getVillageBelongsToCode() {
        return villageBelongsToCode;
    }

    public void setVillageBelongsToCode(Village villageBelongsToCode) {
        this.villageBelongsToCode = villageBelongsToCode;
    }
}

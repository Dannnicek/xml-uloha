package com.idea.entity;

import jakarta.persistence.*;

@Entity
public class CastObce {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String code;
    private String name;

    @ManyToOne
    @JoinColumn(name = "obec_code")
    private Obec obec;
}

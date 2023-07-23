package com.myboard3.myboard3.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String title;
    private String content;

    @Transient
    private Integer virtualId;
}

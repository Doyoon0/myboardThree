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
    private String password; // 비밀번호 필드 추가
    private String username;

    @Transient
    private Integer virtualId;
}

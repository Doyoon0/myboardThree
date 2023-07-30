package com.myboard3.myboard3.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    @ManyToMany(mappedBy = "roles") // User 클래스에 있는 컬럼 이름 (양방향 매핑)
    private List<User> users;
}

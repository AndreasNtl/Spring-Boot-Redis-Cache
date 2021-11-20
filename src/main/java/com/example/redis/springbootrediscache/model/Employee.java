package com.example.redis.springbootrediscache.model;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "employee")
public class Employee implements Serializable {

    @Id
    @GeneratedValue
    private int id;

    @Column(name = "name", length = 30)
    private String name;

}

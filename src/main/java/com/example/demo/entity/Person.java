package com.example.demo.entity;


import jdk.nashorn.internal.objects.annotations.Constructor;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name="person")
public class Person {

    @Id
    @NotNull
    @Setter
    private long pid;


    @NotNull
    @NotBlank
    @Column(name="first_name")
    private String name;


    @Column(name="middle_name")
    private String middleName;

    @NotNull
    @NotBlank
    @Column(name="last_name")
    private String surName;

    private String email;

    private String phone;

}

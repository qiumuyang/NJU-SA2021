package com.sa2021.stulist.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "Students")
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class Student implements Serializable {

    private static final long serialVersionUID = 1L;

    @Column(name = "id", unique = true)
    @NotBlank
    @Id
    private String id;

    @Column(name = "name")
    @NotBlank
    private String name;

    @Column(name = "sex")
    private String sex;

    @Column(name = "birthplace")
    @Size(max = 20)
    private String birthPlace;

    @Column(name = "birthdate")
    @DateTimeFormat(pattern = "yyyy-MM")
    private String birthDate;

    @Column(name = "department")
    @Size(max = 20)
    private String department;

    @Size(min = 11, max = 11)
    private String phone;

    public Student(String id, String name, String gender, String birthplace, String birthdate, String dept) {
        this.id = id;
        this.name = name;
        this.sex = gender;
        this.birthPlace = birthplace;
        this.birthDate = birthdate;
        this.department = dept;
        this.phone = null;
    }

    public String toString() {
        return String.format("%s Name: %s Dept: %s City: %s", this.id, this.name, this.department, this.birthPlace);
    }

    public boolean isNew() {
        return this.id == null;
    }
}
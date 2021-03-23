package com.sa2021.stulist.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "Students")
public class Student {
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getBirthPlace() {
        return birthPlace;
    }

    public void setBirthPlace(String birthPlace) {
        this.birthPlace = birthPlace;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String toString() {
        return String.format("%s Name: %s Dept: %s City: %s", this.id, this.name, this.department, this.birthPlace);
    }

    public boolean isNew() {
        return this.id == null;
    }
}
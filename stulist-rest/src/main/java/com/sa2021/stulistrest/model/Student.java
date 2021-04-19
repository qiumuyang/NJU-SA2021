package com.sa2021.stulistrest.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Student {

    @Id
    @GeneratedValue
    private Long id;

    @NotBlank
    private String name;

    private String gender;

    @Size(max = 20, message = "BirthPlace No Longer Than 20 chars")
    private String birthPlace;

    @Pattern(regexp = "(?:19|20)[0-9]{2}-(?:0[1-9]|1[0-2])", message = "BirthDate in this format: YYYY-MM")
    private String birthDate;

    @Size(max = 20, message = "Department No Longer Than 20 chars")
    private String department;

    public Student(String name, String gender, String birthPlace, String birthDate, String department) {
        this.name = name;
        this.gender = gender;
        this.birthPlace = birthPlace;
        this.birthDate = birthDate;
        this.department = department;
    }

}
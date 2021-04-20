package com.sa2021.stulistrest.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Student {

    @ApiModelProperty(hidden = true)
    @Id
    @GeneratedValue
    private Long id;

    @ApiModelProperty(required = true, value = "姓名", example = "Zhang Wei")
    @NotBlank
    private String name;

    @ApiModelProperty(value = "性别", example = "Male")
    private String gender;

    @ApiModelProperty(value = "籍贯", example = "Nanjing")
    @Size(max = 20, message = "BirthPlace No Longer Than 20 chars")
    private String birthPlace;

    @ApiModelProperty(value = "出生年月", example = "2021-04")
    @Pattern(regexp = "(?:19|20)[0-9]{2}-(?:0[1-9]|1[0-2])", message = "BirthDate in this format: YYYY-MM")
    private String birthDate;

    @ApiModelProperty(value = "系别", example = "CS")
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
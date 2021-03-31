package com.sa2021.stulist.service;

import java.util.Collection;

import com.sa2021.stulist.model.Student;

public interface StudentService {

    Student findById(String id);

    Collection<Student> findByInfo(String name, String department, String birthplace);

    void save(Student student);

    void deleteById(String id);
}

package com.sa2021.stulistrest.repository;

import com.sa2021.stulistrest.model.Student;

import org.springframework.data.repository.CrudRepository;

public interface StudentRepository extends CrudRepository<Student, Long> {

}
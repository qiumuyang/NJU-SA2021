package com.sa2021.stulist.service;

import java.util.Collection;

import com.sa2021.stulist.model.Student;
import com.sa2021.stulist.repository.StudentRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentRepository repository;

    @Override
    public Student findById(String id) {
        return repository.findById(id);
    }

    @Override
    public Collection<Student> findByInfo(String name, String department, String birthplace) {
        return repository.findByInfo(name, department, birthplace);
    }

    @Override
    public Collection<Student> findByName(String string) {
        return repository.findByName(string);
    }

    @Override
    public void save(Student student) {
        repository.save(student);
    }

    @Override
    public void deleteById(String id) {
        repository.deleteById(id);
    }

}

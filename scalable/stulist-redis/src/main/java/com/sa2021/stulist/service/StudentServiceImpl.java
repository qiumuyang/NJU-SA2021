package com.sa2021.stulist.service;

import java.util.Collection;

import com.sa2021.stulist.model.Student;
import com.sa2021.stulist.repository.StudentRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentRepository repository;

    @Override
    @Cacheable(value = "students", key = "#id")
    public Student findById(String id) {
        return repository.findById(id);
    }

    @Override
    @Cacheable(value = "students", key = "#name+'-'+#department+'-'+#birthplace")
    public Collection<Student> findByInfo(String name, String department, String birthplace) {
        return repository.findByInfo(name, department, birthplace);
    }

    @Override
    @CacheEvict(value = "students", allEntries = true)
    public void save(Student student) {
        repository.save(student);
    }

    @Override
    @CacheEvict(value = "students", allEntries = true)
    public void deleteById(String id) {
        repository.deleteById(id);
    }

}

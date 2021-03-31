package com.sa2021.stulist.repository;

import java.util.Collection;

import com.sa2021.stulist.model.Student;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;


public interface StudentRepository extends Repository<Student, Integer> {

    @Query("SELECT s FROM Student s WHERE s.id = :id")
    @Transactional(readOnly = true)
    Student findById(@Param("id") String id);

    @Query("SELECT s FROM Student s WHERE s.name LIKE %:name% and s.department LIKE %:department% and s.birthPlace LIKE %:birthplace%")
    @Transactional(readOnly = true)
    Collection<Student> findByInfo(@Param("name") String name, @Param("department") String department,
            @Param("birthplace") String birthplace);

    @Modifying
    @Query("DELETE FROM Student s WHERE s.id = :id")
    @Transactional
    void deleteById(@Param("id") String id);

    void save(Student student);

}

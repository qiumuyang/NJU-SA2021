package com.sa2021.stulist.excel;

import org.springframework.batch.extensions.excel.RowMapper;
import org.springframework.batch.extensions.excel.support.rowset.RowSet;

// https://github.com/spring-projects/spring-batch-extensions

import com.sa2021.stulist.model.Student;

public class RowMapperImpl implements RowMapper<Student> {

    @Override
    public Student mapRow(RowSet rs) throws Exception {
        if (rs == null || rs.getCurrentRow() == null) {
            return null;
        }
        String[] columns = rs.getCurrentRow();

        Student student = new Student();
        student.setId(columns[0]);
        student.setName(columns[1]);
        student.setDepartment(columns[2]);
        student.setPhone(columns[3]);

        return student;
    }

}

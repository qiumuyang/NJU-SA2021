package com.sa2021.stulistrest.controller;

import com.sa2021.stulistrest.model.Student;
import com.sa2021.stulistrest.repository.StudentRepository;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import javax.validation.Valid;

@RestController
@Api(description = "学生管理")
public class StudentRestController {

    private StudentRepository repository;

    public StudentRestController(StudentRepository repository) {
        this.repository = repository;
    }

    @ApiOperation(value = "获取学生列表", notes = "获取学生列表")
    @GetMapping("/students")
    public ResponseEntity<List<Student>> getStudents() {
        List<Student> list = StreamSupport.stream(repository.findAll().spliterator(), false)
                .collect(Collectors.toList());
        return new ResponseEntity<List<Student>>(list, HttpStatus.OK);
    }

    @ApiOperation(value = "获取学生", notes = "获取指定学生")
    @GetMapping("/students/{id}")
    public ResponseEntity<Student> getStudent(@PathVariable("id") Long id) {
        Optional<Student> student = repository.findById(id);
        if (!student.isPresent()) {
            return new ResponseEntity<Student>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Student>(student.get(), HttpStatus.OK);
    }

    @ApiOperation(value = "创建学生", notes = "创建学生")
    @PostMapping("/students")
    public ResponseEntity<Student> newStudent(@RequestBody @Valid Student student, BindingResult bindingResult,
            UriComponentsBuilder ucBuilder) {
        HttpHeaders headers = new HttpHeaders();
        if (bindingResult.hasErrors() || student.getId() != null) {
            BindingErrorsResponse errors = new BindingErrorsResponse(student.getId());
            errors.addAllErrors(bindingResult);
            headers.add("errors", errors.toJSON());
            return new ResponseEntity<Student>(headers, HttpStatus.BAD_REQUEST);
        }
        repository.save(student);
        headers.setLocation(ucBuilder.path("/students/{id}").buildAndExpand(student.getId()).toUri());
        return new ResponseEntity<Student>(student, headers, HttpStatus.CREATED);
    }

    @ApiOperation(value = "修改学生", notes = "修改学生信息")
    @PutMapping("/students/{id}")
    public ResponseEntity<Student> updateStudent(@RequestBody @Valid Student student, @PathVariable long id,
            BindingResult bindingResult, UriComponentsBuilder ucBuilder) {
        HttpHeaders headers = new HttpHeaders();
        if (bindingResult.hasErrors() || student.getId() != null) {
            BindingErrorsResponse errors = new BindingErrorsResponse(student.getId());
            errors.addAllErrors(bindingResult);
            headers.add("errors", errors.toJSON());
            return new ResponseEntity<Student>(headers, HttpStatus.BAD_REQUEST);
        }
        Optional<Student> current = repository.findById(id);
        if (!current.isPresent()) {
            return new ResponseEntity<Student>(HttpStatus.NOT_FOUND);
        }
        Student stu = current.get();
        stu.setName(student.getName());
        stu.setGender(student.getGender());
        stu.setDepartment(student.getDepartment());
        stu.setBirthDate(student.getBirthDate());
        stu.setBirthPlace(student.getBirthPlace());
        repository.save(stu);
        return new ResponseEntity<Student>(stu, HttpStatus.NO_CONTENT);
    }

    @ApiOperation(value = "删除学生", notes = "删除学生")
    @DeleteMapping("/students/{id}")
    public ResponseEntity<Student> deleteStudent(@PathVariable long id) {
        Optional<Student> current = repository.findById(id);
        if (!current.isPresent()) {
            return new ResponseEntity<Student>(HttpStatus.NOT_FOUND);
        }
        repository.deleteById(id);
        return new ResponseEntity<Student>(HttpStatus.NO_CONTENT);
    }

}

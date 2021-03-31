package com.sa2021.stulist.controller;

import java.util.Collection;
import java.util.Map;
import javax.validation.Valid;

import com.sa2021.stulist.model.Student;
import com.sa2021.stulist.service.StudentService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class StudentController {

    private StudentService students;

    public StudentController(StudentService service) {
        this.students = service;
    }

    @GetMapping("/stu/new")
    public String initCreationForm(Map<String, Object> model) {
        model.put("student", new Student());
        return "stu/createUpdate";
    }

    @PostMapping("/stu/new")
    public String processCreationForm(@Valid Student student, BindingResult result) {
        if (this.students.findById(student.getId()) != null) {
            result.rejectValue("id", "duplicate", "already exists");
            student.setId(null);
        }
        if (result.hasErrors()) {
            return "stu/createUpdate";
        } else {
            this.students.save(student);
            return "redirect:/stu/" + student.getId();
        }
    }

    @GetMapping("/stu/find")
    public String initFindForm(Map<String, Object> model) {
        model.put("student", new Student());
        return "stu/find";
    }

    @GetMapping("/stu")
    public String processFindForm(Student student, BindingResult result, Map<String, Object> model) {
        if (student.getName() == null) {
            student.setName("");
        }
        if (student.getBirthPlace() == null) {
            student.setBirthPlace("");
        }
        if (student.getDepartment() == null) {
            student.setDepartment("");
        }
        Collection<Student> results = this.students.findByInfo(student.getName().strip(),
                student.getDepartment().strip(), student.getBirthPlace().strip());

        if (results.isEmpty()) {
            result.rejectValue("name", "notFound", "not found");
            return "stu/find";
        } else if (results.size() == 1) {
            return "redirect:/stu/" + results.iterator().next().getId();
        } else {
            model.put("selections", results);
            return "stu/list";
        }
    }

    @GetMapping("/stu/{stuId}/edit")
    public String initUpdateForm(@PathVariable("stuId") String stuId, Model model) {
        Student student = this.students.findById(stuId);
        if (student != null) {
            model.addAttribute(student);
            return "stu/createUpdate";
        }
        model.addAttribute("message", "Student ID not found");
        return "error";
    }

    @PostMapping("/stu/{stuId}/edit")
    public String processUpdateForm(@Valid Student student, BindingResult result, @PathVariable("stuId") String stuId) {
        if (result.hasErrors()) {
            return "stu/createUpdate";
        } else {
            student.setId(stuId);
            this.students.save(student);
            return "redirect:/stu/{stuId}";
        }
    }

    @GetMapping("/stu/{stuId}/remove")
    public String removeStudent(@PathVariable("stuId") String stuId, Model model) {
        Student student = this.students.findById(stuId);
        if (student != null) {
            this.students.deleteById(stuId);
            return "redirect:/stu";
        }
        model.addAttribute("message", "Student ID not found");
        return "stu/";
    }

    @GetMapping("/stu/{stuId}")
    public ModelAndView showDetail(@PathVariable("stuId") String stuId) {
        Student student = this.students.findById(stuId);
        ModelAndView mav;
        if (student != null) {
            mav = new ModelAndView("stu/details");
            mav.addObject(student);
        } else {
            mav = new ModelAndView("error");
            mav.addObject("message", "Student ID not found");
        }
        return mav;
    }
}
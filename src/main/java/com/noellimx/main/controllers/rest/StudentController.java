package com.noellimx.main.controllers.rest;


import com.noellimx.main.entity.Student;
import com.noellimx.main.exception.controller.NotImplementedException;
import com.noellimx.main.service.student.StudentService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/students")
public class StudentController {


  StudentService studentService;


  @Autowired
  public StudentController(StudentService studentService) {
    this.studentService = studentService;
  }

  @GetMapping("/")
  public List<Student> getStudents() {
    return studentService.getAll();
  }

  @GetMapping("/{id}")
  public Student getStudentById(@PathVariable int id) {
    Student student = new Student("abel", "lee", "@");
    student.setId(id);
    return student;
  }


  @GetMapping("/invalid")
  public void invalid() {

    throw new NotImplementedException("oh no");
  }


}

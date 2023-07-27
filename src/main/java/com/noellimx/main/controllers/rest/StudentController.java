package com.noellimx.main.controllers.rest;


import com.noellimx.main.entity.Student;
import com.noellimx.main.exception.controller.NotFoundException;
import com.noellimx.main.exception.controller.NotImplementedException;
import com.noellimx.main.service.student.StudentService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

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
    Student student = studentService.getById(id);

    if (student == null) {
      throw new NotFoundException("Resource: Student not found");
    }

    return student;
  }

  @DeleteMapping("/{id}")
  public void deleteStudentById(@PathVariable int id) {

    Student student = studentService.getById(id);
    if (student == null) {
      throw new NotFoundException("Unable to delete " + student + ". Resource not found");
    }

    studentService.deleteStudentById(id);
    student = studentService.getById(id);
    if (student != null) {

      throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,
          "Unable to delete " + student);
    }
  }

  @PutMapping("/")
  public Student updateStudentById(@RequestBody Student student) {
    studentService.update(student);
    return studentService.getById(student.getId());
  }


  @GetMapping("/invalid")
  public void invalid() {
    throw new NotImplementedException("oh no");
  }


}

package com.noellimx.main.controllers.rest;


import com.noellimx.main.entity.Student;
import java.util.ArrayList;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/students")
public class StudentController {

  @GetMapping("/")
  public List<Student> sayHelloWorld() {
    Student student = new Student("abel", "lee", "@");
    Student student2 = new Student("alex", "poh", "@");
    Student student3 = new Student("xavier", "tan", "@");
    List<Student> students = new ArrayList<>();

    students.add(student);
    students.add(student2);
    students.add(student3);

    return students;
  }

}

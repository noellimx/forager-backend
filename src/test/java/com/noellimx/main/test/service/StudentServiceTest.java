package com.noellimx.main.test.service;

import com.noellimx.main.entity.Student;
import com.noellimx.main.service.student.StudentService;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@TestMethodOrder(OrderAnnotation.class)
public class StudentServiceTest {


  StudentService studentService;

  @Autowired
  public StudentServiceTest(StudentService studentService) {
    this.studentService = studentService;
  }


  @Test
  @Order(1)
  public void ShouldCreate() {

    studentService.createStudent("My", "First", "Student");
    List<Student> students = studentService.findByFirstAndLastName("My", "First");

    Assertions.assertEquals(1, students.size());

  }

  @Test
  @Order(2)
  public void ShouldUpdate() {
    List<Student> students = studentService.findByFirstAndLastName("My", "First");
    Assertions.assertTrue(students.size() > 0);

    Student student = students.get(0);

    String updatedLastName = "First-Modified";
    student.setLastName(updatedLastName);

    studentService.update(student);

    Assertions.assertEquals(updatedLastName,
        studentService.getById(student.getId()).getLastName());
  }

}

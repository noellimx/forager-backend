package com.noellimx.main.dao;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.noellimx.main.entity.Student;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@TestMethodOrder(OrderAnnotation.class)
public class StudentDAOTest {

  @Autowired
  private StudentDAO studentDAO;

  @Test
  @Order(1)

  public void ShouldExist() {

    String firstName = "A";
    String lastName = "B";
    Student student = new Student(firstName, lastName, "C");

    System.out.println("StudentDAOTest: Creating Student" + student);

    studentDAO.save(student);

    Integer gotCount = studentDAO.findByAll().size();
    assertTrue(gotCount > 1);

    assertTrue(studentDAO.findByLastName(lastName).size() == 1);
  }

  @Test
  @Order(2)

  public void ShouldUpdate() {

    Student student = studentDAO.findById(1);
    student.setLastName("NEWB");
    studentDAO.update(student);

    student = studentDAO.findById(1);

    assertEquals("NEWB", student.getLastName());

  }

  @Test
  @Order(3)
  public void ShouldDelete() {
    Student student = studentDAO.findById(1);
    studentDAO.removeById(student.getId());
    student = studentDAO.findById(1);
    assertNull(student);
  }

  @Test
  @Order(4)
  public void ShouldDeleteAll() {
    studentDAO.removeAll();
    Integer gotCount = studentDAO.findByAll().size();
    assertTrue(gotCount == 0);
  }
}
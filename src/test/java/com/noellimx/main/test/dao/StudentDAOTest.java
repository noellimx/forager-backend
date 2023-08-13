package com.noellimx.main.test.dao;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.noellimx.main.dao.StudentDAO;
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

    studentDAO.save(student);

    int gotTotalCount = studentDAO.getAll().size();
    int wantTotalCount = 1;

    assertTrue(gotTotalCount >= wantTotalCount,
        "total count should be at least  " + wantTotalCount + " but got " + gotTotalCount);

    int gotCount = studentDAO.findByLastName(lastName).size();

    assertEquals(1, (int) gotCount, "student count got " + gotCount);
  }

  @Test
  @Order(2)
  public void ShouldUpdate() {

    Student student = studentDAO.findByLastName("B").get(0);

    int id = student.getId();

    System.out.println("ShouldUpdateDAO id " + id);
    student.setLastName("NEWB");
    studentDAO.update(student);

    student = studentDAO.findById(id);

    assertEquals("NEWB", student.getLastName());

  }

  @Test
  @Order(3)
  public void ShouldDelete() {

    Student student = studentDAO.findByLastName("NEWB").get(0);
    int id = student.getId();
    studentDAO.removeById(id);
    student = studentDAO.findById(id);
    assertNull(student);
  }

  @Test
  @Order(4)
  public void ShouldDeleteAll() {
    studentDAO.removeAll();
    int gotCount = studentDAO.getAll().size();
    assertEquals(0, (int) gotCount);
  }
}
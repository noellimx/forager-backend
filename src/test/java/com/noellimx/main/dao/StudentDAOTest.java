package com.noellimx.main.dao;


import com.noellimx.main.entity.Student;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class StudentDAOTest {

  @Autowired
  private StudentDAO studentDAO;

  @Test
  public void saveTest() {

    Student student = new Student("A", "B", "C");

    System.out.println("StudentDAOTest: Creating Student" + student);

    studentDAO.save(student);

    student = studentDAO.findById(1);
    System.out.println("StudentDAOTest: find #1" + student);

    student = studentDAO.findById(2);
    System.out.println("StudentDAOTest: find #2" + student);

    student = studentDAO.findById(3);
    System.out.println("StudentDAOTest: find #3" + student);
    student = studentDAO.findById(4);
    System.out.println("StudentDAOTest: find #4" + student);
  }
}
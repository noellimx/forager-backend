package com.noellimx.main.service.student;

import com.noellimx.main.dao.StudentDAO;
import com.noellimx.main.entity.Student;
import jakarta.transaction.Transactional;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class StudentService {

  StudentDAO studentDAO;

  @Autowired
  public StudentService(StudentDAO studentDAO) {
    this.studentDAO = studentDAO;
  }

  @Transactional
  public List<Student> getAll() {
    return studentDAO.getAll();
  }

  @Transactional
  public void createStudent(String fn, String ln, String email) {
    studentDAO.save(new Student(fn, ln, email));
  }

  @Transactional
  public List<Student> findByFirstAndLastName(String fn, String ln) {
    return studentDAO.findByFirstAndLastName(fn, ln);
  }

  @Transactional
  public Student getById(Integer id) {
    return studentDAO.findById(id);
  }


  @Transactional
  public void update(Student student) {
    studentDAO.update(student);
  }
}

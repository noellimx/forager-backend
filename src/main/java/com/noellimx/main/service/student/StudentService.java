package com.noellimx.main.service.student;

import com.noellimx.main.entity.Student;
import com.noellimx.main.respository.StudentRepository;
import jakarta.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class StudentService {

  final StudentRepository studentDAO;

  @Autowired
  public StudentService(StudentRepository studentDAO) {
    this.studentDAO = studentDAO;
  }

  @Transactional
  public List<Student> getAll() {
    return studentDAO.findAll();
  }

  @Transactional
  public void createStudent(String fn, String ln, String email) {
    studentDAO.save(new Student(fn, ln, email));
  }


  @Transactional
  public List<Student> findByFirstAndLastName(String fn, String ln) {
    return studentDAO.findByFirstNameAndLastName(fn, ln);
  }

  @Transactional
  public Student getById(Integer id) {
    Optional<Student> s = studentDAO.findById(id);

    return s.orElse(null);

  }


  @Transactional
  public void update(Student student) {
    studentDAO.save(student);
  }


  @Transactional
  public void deleteStudent(Student student) {
    studentDAO.delete(student);
  }

  @Transactional
  public void deleteStudentById(Integer id) {
    studentDAO.deleteById(id);
  }

}

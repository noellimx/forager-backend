package com.noellimx.main.service.student;

import com.noellimx.main.dao.StudentDAO;
import com.noellimx.main.entity.Student;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class Service {


  StudentDAO studentDAO;

  @Autowired
  public Service(StudentDAO studentDAO) {
    this.studentDAO = studentDAO;
  }


  public List<Student> getAll() {
    return studentDAO.getAll();
  }
}

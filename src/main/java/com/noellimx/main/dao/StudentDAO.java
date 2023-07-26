package com.noellimx.main.dao;

import com.noellimx.main.entity.Student;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


@Repository
public class StudentDAO {

  private EntityManager entityManager;


  @Autowired
  public StudentDAO(EntityManager entityManager) {
    this.entityManager = entityManager;
  }


  @Transactional
  public void save(Student student) {
    entityManager.persist(student);
  }

  @Transactional
  public void saveMany(Student... students) {
    for (Student s : students) {
      entityManager.persist(s);
    }
  }

  @Transactional
  public Student findById(Integer id) {
    return entityManager.find(Student.class, id);
  }
}

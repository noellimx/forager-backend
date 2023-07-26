package com.noellimx.main.dao;

import com.noellimx.main.entity.Student;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import java.util.List;
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
  public List<Student> findByLastName(String lastName) {

    TypedQuery<Student> query = entityManager.createQuery("FROM Student WHERE lastName=:ln",
        Student.class);
    query.setParameter("ln", lastName);
    return query.getResultList();
  }

  @Transactional
  public void update(Student student) {
    entityManager.merge(student);
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

  @Transactional
  public List<Student> findByAll() {
    TypedQuery<Student> query = entityManager.createQuery("FROM Student",
        Student.class);
    return query.getResultList();
  }

  @Transactional
  public void removeById(Integer id) {
    Student student = findById(id);

    System.out.println("removeById" + student);
    entityManager.remove(student);
  }

  @Transactional
  public void removeAll() {
    entityManager.createQuery("DELETE from Student").executeUpdate();
  }
}

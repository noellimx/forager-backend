package com.noellimx.main.respository;

import com.noellimx.main.entity.Student;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {


  public List<Student> findByFirstNameAndLastName(String ln, String fn);

}

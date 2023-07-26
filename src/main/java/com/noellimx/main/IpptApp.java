package com.noellimx.main;

import com.noellimx.main.dao.StudentDAO;
import com.noellimx.main.entity.Student;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication(scanBasePackages = {"com.noellimx.main",
    "com.noellimx.external"})
public class IpptApp {

  public static void main(String[] args) {
    SpringApplication.run(IpptApp.class, args);
  }


  @Bean
  public CommandLineRunner clr1() {
    return runner -> {
      System.out.println("clr1: Beans should be loaded");
    };
  }

  @Bean
  public CommandLineRunner seedStudent(StudentDAO studentDAO) {
    return runner -> {
      System.out.println("clr2: Seeding students");

      Student student = new Student("FirstSeedStudent", "Lim", "seed@email.com");
      Student student2 = new Student("SecondSeedStudent", "Lim", "seed@email.com");

      studentDAO.saveMany(student, student2);
    };
  }
}

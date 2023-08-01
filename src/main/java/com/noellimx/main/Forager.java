package com.noellimx.main;

import com.noellimx.main.configuration.BasicSecurityProfile;
import com.noellimx.main.service.MyUserDetailsService;
import com.noellimx.main.service.student.StudentService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication(scanBasePackages = {"com.noellimx.main",
    "com.noellimx.external"})
public class Forager {

  public static void main(String[] args) {
    SpringApplication.run(Forager.class, args);
  }


  @Bean
  public CommandLineRunner seedStudents(StudentService studentService) {
    return runner -> {

    };
  }

  @Bean
  public CommandLineRunner seedUsers(MyUserDetailsService userDetailsService,
      BasicSecurityProfile basicSecurityProfile) {
    return runner -> {

    };
  }
}

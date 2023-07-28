package com.noellimx.main;

import com.noellimx.main.configuration.BasicSecurityProfile;
import com.noellimx.main.service.MyUserDetailsService;
import com.noellimx.main.service.student.StudentService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

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
  public CommandLineRunner seedStudents(StudentService studentService) {
    return runner -> {
      System.out.println("clr2: Seeding students");
      studentService.createStudent("FirstSeedStudent", "Lim", "seed@email.com");
      studentService.createStudent("SecondSeedStudent", "Lim", "seed@email.com");
    };
  }

  @Bean
  public CommandLineRunner seedUsers(MyUserDetailsService userDetailsService,
      BasicSecurityProfile basicSecurityProfile) {
    return runner -> {
      UserDetails user = User.builder()
          .username("user")
          .password("password1")
          .roles("USER")
          .build();
      UserDetails admin = User.builder()
          .username("admin")
          .password("admin")
          .roles("ADMIN")
          .build();

      UserDetails env_user = User.builder()
          .username(basicSecurityProfile.getUsername())
          .password(basicSecurityProfile.getPassword())
          .roles("USER", "ADMIN")
          .build();

      userDetailsService.createUser(user);
      userDetailsService.createUser(admin);
      userDetailsService.createUser(env_user);
    };
  }

}

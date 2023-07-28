package com.noellimx.main.service;

import com.noellimx.main.respository.UserRepository;
import jakarta.transaction.Transactional;
import java.util.Arrays;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Service;


@Service
public class MyUserDetailsService implements UserDetailsManager {


  private UserRepository userRepository;


  @Autowired
  public MyUserDetailsService(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  @Override
  public UserDetails loadUserByUsername(String username) {

    System.out.println("UserService::loadUserByUsername executing findByUsername..." + username);

    com.noellimx.main.entity.User user = userRepository.findByUsername(username);
    System.out.println("UserService::loadUserByUsername executed findByUsername...");

    if (user == null) {

      System.out.println("UserService::loadUserByUsername failed to load user... user not found");
      throw new UsernameNotFoundException(username);
    }

    System.out.println("UserService::loadUserByUsername loaded user...");
    System.out.println(user);

    class A implements GrantedAuthority {


      String role;

      public A(String auth) {
        this.role = auth;
      }

      @Override
      public String getAuthority() {
        return role;
      }
    }

    A role3 = new A("ROLE_ADMIN");
    A role4 = new A("ROLE_USER");
    System.out.println("UserService::loadUserByUsername converting to user details ...");

    UserDetails userDetails = new User(user.getUsername(), user.getPassword(), true, true, true,
        true,
        Arrays.asList(role3, role4));

    System.out.println("UserService::loadUserByUsername convert to user details success...");
    System.out.println(userDetails);
    return userDetails;
  }

  @Override
  @Transactional
  public void createUser(UserDetails userDetails) {
    System.out.println("UserService::createUser");
    com.noellimx.main.entity.User user = new com.noellimx.main.entity.User(
        userDetails.getUsername(),
        userDetails.getPassword());
    userRepository.save(user);
  }

  @Override
  public void updateUser(UserDetails user) {

  }

  @Override
  public void deleteUser(String username) {

  }

  @Override
  public void changePassword(String oldPassword, String newPassword) {

  }

  @Override
  public boolean userExists(String username) {
    return true;
  }
}


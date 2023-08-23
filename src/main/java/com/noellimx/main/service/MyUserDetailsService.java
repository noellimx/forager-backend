package com.noellimx.main.service;

import com.noellimx.main.entity.AppUser;
import com.noellimx.main.exception.controller.NotFoundException;
import com.noellimx.main.respository.UserRepository;
import jakarta.transaction.Transactional;
import java.util.Arrays;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Service;


@Service
public class MyUserDetailsService implements UserDetailsManager {

  private final UserRepository userRepository;


  @Autowired
  public MyUserDetailsService(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  @Override
  public UserDetails loadUserByUsername(String username) {

    AppUser user = userRepository.findByUsername(username);

    if (user == null) {
      throw new UsernameNotFoundException(username);
    }

    class A implements GrantedAuthority {

      final String role;

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

    return new User(user.getUsername(), user.getPassword(), true, true, true,
        true,
        Arrays.asList(role3, role4));
  }

  @Override
  @Transactional
  public void createUser(UserDetails userDetails) {
    AppUser user = new AppUser(
        userDetails.getUsername(),
        userDetails.getPassword());
    userRepository.save(user);
  }

  @Transactional
  public UserDetails authenticateUser(UserDetails userDetails) {

    AppUser user = userRepository.findByUsernameAndPassword(userDetails.getUsername(),
        userDetails.getPassword());

    if (user == null) {
      throw new NotFoundException("User and password pair not found.");
    }

    return new User(user.getUsername(), "", true, true, true,
        true,
        List.of());
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


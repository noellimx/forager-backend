package com.noellimx.main.respository;

import com.noellimx.main.entity.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepository extends JpaRepository<AppUser, Long> {

  AppUser findByUsername(String username);

  AppUser findByUsernameAndPassword(String username, String password);
}
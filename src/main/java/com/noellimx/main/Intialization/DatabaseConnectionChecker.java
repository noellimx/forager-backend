package com.noellimx.main.Intialization;

import java.sql.Connection;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class DatabaseConnectionChecker implements ApplicationRunner {

  private final DataSource dataSource;

  @Autowired
  public DatabaseConnectionChecker(DataSource dataSource) {
    this.dataSource = dataSource;
  }

  @Override
  public void run(ApplicationArguments args) throws Exception {
    try (Connection connection = dataSource.getConnection()) {
      // If the connection is successful, do nothing
      System.out.println("Info: Database connection success.");

    } catch (Exception e) {
      // Log a warning and exit the application
      System.err.println("Warning: Database connection failed. Application is exiting.");
      System.exit(1); // Terminate the application with a non-zero exit code
    }
  }
}
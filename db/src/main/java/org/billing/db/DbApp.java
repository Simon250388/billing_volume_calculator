package org.billing.db;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DbApp implements CommandLineRunner {

  public static void main(String[] args) {
    SpringApplication.run(DbApp.class, args);
  }

  @Override
  public void run(String... args) throws Exception {}
}

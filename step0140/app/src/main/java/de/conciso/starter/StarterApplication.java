package de.conciso.starter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jms.annotation.EnableJms;

@SpringBootApplication
public class StarterApplication {
  public static void main(String[] args) {
    SpringApplication.run(StarterApplication.class, args);
  }
}

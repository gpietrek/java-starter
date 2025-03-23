package de.conciso.starter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HelloWorldApplication {

  private static final Logger logger = LoggerFactory.getLogger(HelloWorldApplication.class);

  public void run() {
    var greeter = new GreeterService();
    logger.info(greeter.greet("World"));
  }
}

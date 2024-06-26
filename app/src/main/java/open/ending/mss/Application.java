package open.ending.mss;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Launch main class.
 */
@SpringBootApplication
public class Application {

  public static void main(String[] args) {
    SpringApplication application = new SpringApplication(Application.class);
    application.run(args);
  }
}

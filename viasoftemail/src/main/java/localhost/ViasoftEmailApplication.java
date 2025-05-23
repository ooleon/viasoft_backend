package localhost;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
//import org.springframework.jms.annotation.EnableJms;

@SpringBootApplication
//@EnableJms
@ComponentScan(basePackages = "localhost")
public class ViasoftEmailApplication {

    public static void main(String[] args) {
        SpringApplication.run(ViasoftEmailApplication.class, args);
    }
}

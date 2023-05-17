package toy.animoly;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class SpringAnimolyApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringAnimolyApplication.class, args);
    }

}

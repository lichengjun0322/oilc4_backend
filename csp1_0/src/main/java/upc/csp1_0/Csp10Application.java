package upc.csp1_0;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;



@SpringBootApplication
@MapperScan("upc.csp1_0.dao")
public class Csp10Application {
    public static void main(String[] args) {
        SpringApplication.run(Csp10Application.class, args);

    }

}

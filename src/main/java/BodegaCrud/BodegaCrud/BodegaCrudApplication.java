package BodegaCrud.BodegaCrud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
public class BodegaCrudApplication {

	public static void main(String[] args) {
		SpringApplication.run(BodegaCrudApplication.class, args);
	}

}

package sia.boot.example.corsionline;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.data.rest.configuration.SpringDataRestConfiguration;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2WebMvc;

@SpringBootApplication
@EnableSwagger2WebMvc
@Import(SpringDataRestConfiguration.class)
public class CorsiApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(CorsiApiApplication.class, args);
	}
	
	public Docket personeApi() {
		return new Docket(DocumentationType.SPRING_WEB)
				.select()
		          .apis(RequestHandlerSelectors.any())
		          .paths(PathSelectors.any())
		          .build();
	}
}

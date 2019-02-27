package sia.bootdatajpa;

import static springfox.documentation.schema.AlternateTypeRules.newRule;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import org.springframework.http.ResponseEntity;
import org.springframework.web.context.request.async.DeferredResult;

import com.fasterxml.classmate.TypeResolver;

import sia.bootdatajpa.model.Persona;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.WildcardType;
import springfox.documentation.service.Tag;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.data.rest.configuration.SpringDataRestConfiguration;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2WebMvc;

@SpringBootApplication
@EnableSwagger2WebMvc
@Import(SpringDataRestConfiguration.class)
public class BootDataJpaApplication {
	
	@Autowired
	  private TypeResolver typeResolver;

	public static void main(String[] args) {
		SpringApplication.run(BootDataJpaApplication.class, args);
	}
	
	public Docket personeApi() {
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
		          .apis(RequestHandlerSelectors.any())
		          .paths(PathSelectors.any())
		          .build()
		        .pathMapping("/")
		        .directModelSubstitute(LocalDate.class, String.class)
		        .genericModelSubstitutes(ResponseEntity.class)
		        .alternateTypeRules(
		            newRule(typeResolver.resolve(DeferredResult.class,
		                typeResolver.resolve(ResponseEntity.class, WildcardType.class)),
		                typeResolver.resolve(WildcardType.class)))
		        .useDefaultResponseMessages(false)
//		        .globalResponseMessage(RequestMethod.GET,
//		            newArrayList(new ResponseMessageBuilder()
//		                .code(500)
//		                .message("500 message")
//		                .responseModel(new ModelRef("Error"))
//		                .build()))
		        //.securitySchemes(newArrayList(apiKey()))
		        //.securityContexts(newArrayList(securityContext()))
		        .enableUrlTemplating(true)
//		        .globalOperationParameters(
//		            newArrayList(new ParameterBuilder()
//		                .name("someGlobalParameter")
//		                .description("Description of someGlobalParameter")
//		                .modelRef(new ModelRef("string"))
//		                .parameterType("query")
//		                .required(true)
//		                .build()))
		        .tags(new Tag("Persone Service", "All apis relating to persons")) 
		        .additionalModels(typeResolver.resolve(Persona.class)) 
		        ;
	}

}

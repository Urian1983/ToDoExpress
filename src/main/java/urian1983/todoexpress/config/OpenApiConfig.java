package urian1983.todoexpress.config;

import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.models.OpenAPI;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {


    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(
                .title("TodoExpress")
                .description("API documentation for a simple ToDo application with JPA")
                .version("1.0")
                .contact(new Contact()
                        .name("Josep Julià Roca Blanco")
                        .email("urian1983@proton.me")))


                )
    }
}

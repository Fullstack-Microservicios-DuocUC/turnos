package cl.duoc.mineria.turnos.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI(){

        return new OpenAPI()
                .info(new Info()
                        .title("API de Gestión de Turnos")
                        .version("1.0.0")
                        .description("Microservicio encargado de administrar los turnos de trabajo de los operarios.")
                    );

    }

}
package Mauro.HomeChef.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.servers.Server;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
    servers = @Server(
        url = "http://localhost:8084"
    )
)
public class OpenApiConfig {

    public static final String HC_SECURITY_SCHEME = "JWT Bearer Token";

    @Bean
    public OpenAPI openApiConfiguration() {
        return new OpenAPI()
            .components(new Components()
                .addSecuritySchemes(HC_SECURITY_SCHEME,
                    new SecurityScheme()
                        .name(HC_SECURITY_SCHEME)
                        .type(SecurityScheme.Type.HTTP)
                        .scheme("bearer")
                        .bearerFormat("JWT")
                )
            )
            .info(new Info()
                .title("HomeChef Server API")
                .version("0.1")
            );
    }

}

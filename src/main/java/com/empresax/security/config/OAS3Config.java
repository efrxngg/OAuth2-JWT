package com.empresax.security.config;


import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@SecurityScheme(
        name = "Bearer Authentication",
        type = SecuritySchemeType.HTTP,
        bearerFormat = "JWT",
        scheme = "bearer"
)
public class OAS3Config {

    @Bean
    public OpenAPI infoOpenApi() {
        return new OpenAPI()
                .info(new Info().title("Login Service")
                        .description("""
                                Autenticacion y Autorizacion mediante JWT firmado con **RSA256** public y private key generados con el **JDK 17**.
                                                                
                                Para mas informacion puedes visitar mi repositorio en Github **OAuth2-JWT**.
                                      """)
                        .version("v1")
                        .license(
                                new License().name("Apache 2.0").url("https://www.apache.org/licenses/LICENSE-2.0.html"))
                        .contact(new Contact()
                                .name("@efrxngg")
                                .url("https://efrxngg.github.io/")))
                .externalDocs(new ExternalDocumentation()
                        .description("GitHub")
                        .url("https://github.com/efrxngg"));
    }

}
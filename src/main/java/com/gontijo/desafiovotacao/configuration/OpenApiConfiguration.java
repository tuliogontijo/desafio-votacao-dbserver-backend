package com.gontijo.desafiovotacao.configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.context.annotation.Configuration;

@OpenAPIDefinition(
        info = @Info(
                title = "API para sistema de votação",
                version = "1.0.0",
                description = "Desafio proposto pela empresa DbServer",
                contact = @Contact(
                        name = "Tulio Gontijo",
                        email = "gontijo.tulio@gmail.com"
                )
        ),
        servers = @Server(
                url = "/"
        )
)
@Configuration
class OpenApiConfiguration {
}
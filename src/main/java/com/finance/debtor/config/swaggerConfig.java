package com.finance.debtor.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class swaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
            .info(new Info()
                .title("Debtor API")
                .version("1.0")
                .description("API para gerenciamento de usuários e lançamentos financeiros")
                .contact(new Contact()
                    .name("F3lkis")
                    .email("seu@email.com"))
            );
    }
}
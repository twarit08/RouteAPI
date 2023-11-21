package com.route.config;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;

@Configuration
public class SwaggerConfig {

	private String schemeName = "bearerScheme";
	
	@Value("${server.name}")
	private String server;

	@Bean
	public OpenAPI springOpenAPI() {
		return new OpenAPI()
				.addSecurityItem(new SecurityRequirement().addList(schemeName))
				.components(new Components().addSecuritySchemes(schemeName,
						new SecurityScheme().name(schemeName).type(SecurityScheme.Type.HTTP).bearerFormat("JWT")
								.scheme("bearer")))
				.info(new Info().title("Router API").description("").version("v0.0.1")
						.contact(new Contact().name("Twarit Soni").email("twarit.s88@gmail.com").url("www.hti.com"))
						.license(new License().name("Apache 2.0").url("http://springdoc.org"))).servers(List.of(new Server().url(server).description("Server")));
	}

}

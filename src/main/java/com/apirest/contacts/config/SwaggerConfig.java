package com.apirest.contacts.config;

import springfox.documentation.swagger2.annotations.EnableSwagger2;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.VendorExtension;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger.web.DocExpansion;
import springfox.documentation.swagger.web.OperationsSorter;
import springfox.documentation.swagger.web.UiConfiguration;
import springfox.documentation.swagger.web.UiConfigurationBuilder;

import static springfox.documentation.builders.PathSelectors.regex;

import java.util.ArrayList;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
	
	@Bean
    public Docket contactApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.apirest.contacts"))
                .paths(regex("/api.*"))
                .build()
                .apiInfo(metaInfo());
    }

    private ApiInfo metaInfo() {

        ApiInfo apiInfo = new ApiInfo(
                "Contatinhos API REST",
                "API REST de gerenciamento de contatinhos.",
                "1.0",
                "Terms of Service",
                new Contact("Antonia Luciana Pires", "https://github.com/antonialucianapires",
                        "antonialucianapires@gmail.com"),
                "Apache License Version 2.0",
                "https://www.apache.org/licesen.html", new ArrayList<VendorExtension>()
        );

        return apiInfo;
    }
    
    @Bean
    UiConfiguration uiConfig() {
    return UiConfigurationBuilder.builder()
    .operationsSorter(OperationsSorter.ALPHA)
    .docExpansion(DocExpansion.LIST) // DocExpansion.LIST or DocExpansion.NONE or DocExpansion.FULL -> Exibe ou recolhe endpoints
    .defaultModelsExpandDepth(-1) // Oculta Models
    .build();

    }

}
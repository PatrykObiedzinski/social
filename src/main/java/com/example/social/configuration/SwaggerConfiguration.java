package com.example.social.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfiguration {

    @Bean
    public Docket SwaggerApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("social-api")
                .apiInfo(buildApiInfo())
                .select()
                .apis(RequestHandlerSelectors.any())
                .build();
    }

    private ApiInfo buildApiInfo() {
        return new ApiInfoBuilder()
                .title("Swagger for a social app")
                .description("An easy documentation")
                .contact(new Contact("Patryk Obiedziński",
                        "https://github.com/PatrykObiedzinski/social",
                        "paobiedzinski@gmail.com"))
                .license("Custom license")
                .licenseUrl("https://example.com")
                .version("1.0.0")
                .build();
    }
}

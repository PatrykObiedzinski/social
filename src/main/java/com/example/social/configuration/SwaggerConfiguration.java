package com.example.social.configuration;

import com.google.common.base.Predicate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

import static com.google.common.base.Predicates.or;
import static java.util.stream.Collectors.toList;

@Configuration
@EnableSwagger2
public class SwaggerConfiguration {

    @Bean
    public Docket SwaggerApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("social-api")
                .apiInfo(buildApiInfo())
                .select()
                .paths(isPathAvailableBySwagger())
                .apis(RequestHandlerSelectors.any())
                .build();
    }

    private ApiInfo buildApiInfo() {
        return new ApiInfoBuilder()
                .title("Swagger for a social app - HStoneAge code challenge")
                .description("An easy documentation")
                .contact(new Contact("Patryk Obiedziński",
                        "https://github.com/PatrykObiedzinski/social",
                        "paobiedzinski@gmail.com"))
                .license("Custom license")
                .licenseUrl("https://example.com")
                .version("1.0.0")
                .build();
    }

    private Predicate<String> isPathAvailableBySwagger() {
        ArrayList<String> availablePaths = getSwaggerAvailablePaths();
        List<Predicate<String>> arePathsAvailable = availablePaths.stream()
                .map(PathSelectors::regex)
                .collect(toList());
        return or(arePathsAvailable);
    }

    private ArrayList<String> getSwaggerAvailablePaths() {
        ArrayList<String> availablePaths = new ArrayList<>();
        availablePaths.add("/follows.*");
        availablePaths.add("/posts.*");
        availablePaths.add("/timeline.*");
        availablePaths.add("/user.*");
        availablePaths.add("/wall.*");
        return availablePaths;
    }
}

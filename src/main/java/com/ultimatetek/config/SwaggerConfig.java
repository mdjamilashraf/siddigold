package com.ultimatetek.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springdoc.core.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 *
 * @author Md Junaid Khan
 */
@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI().info(new Info()
                .title("SalesOrder Application")
                .version("v1")
                .description("Development Of API's"));

    }

    @Bean
    public GroupedOpenApi apis() {
        String basePackage = "com.ultimatetek.rest";
        return GroupedOpenApi.builder().group("MyApis").packagesToScan(basePackage).build();
    }
}

package com.ultimatetek;

import javax.servlet.ServletContext;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.boot.web.servlet.ServletContextInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@ServletComponentScan
@SpringBootApplication
//@OpenAPIDefinition(info = @Info(title = "SalesOrder API", version = "2.0", description = "SalesOrder Information", contact = @Contact(
//        name = "ultimateTekSolution",
//        url = "https://ultimatetek.in/",
//        email = "idris@ultimate-in.com"
//)))
public class SalesOrderApplication {

    private static final Logger LOGGER = LoggerFactory.getLogger(SalesOrderApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(SalesOrderApplication.class, args);
        LOGGER.info("SalesOrderApplication-started");

    }

    @Bean
    public ServletContextInitializer initializer() {
        return (ServletContext servletContext) -> {
        };
    }

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurerAdapter() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**").allowedOrigins("*").allowedMethods("GET", "PUT", "POST", "DELETE").allowedHeaders("*");
            }
        };
    }

}

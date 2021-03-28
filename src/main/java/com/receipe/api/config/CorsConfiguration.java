package com.receipe.api.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Handles the cors configuration.
 */
@Configuration
public class CorsConfiguration {
    /**
     * Cors configuration, currently allow localhost for development. Later change it to hosted frontend url.
     * @return cors config. bean
     */
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry
                    .addMapping("/**")
                    .allowedMethods("GET", "POST", "PUT", "DELETE")
                    .allowedOrigins("http://localhost:4200");
            }
        };
    }
}

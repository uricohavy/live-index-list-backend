package com.list.index.live.live_index_list.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOriginPatterns("http://localhost:3000")  // Allow frontend origins
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")  // Allow common HTTP methods
                .allowedHeaders("*")  // Allow all headers
                .allowCredentials(true);  // Allow cookies and credentials (if needed)
    }
}


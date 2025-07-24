package br.com.opinai.api.review.infrastructure.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import br.com.opinai.api.review.infrastructure.json.Json;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ObjectMapperConfig {

    @Bean
    public ObjectMapper objectMapper() {
        return Json.mapper();
    }
}
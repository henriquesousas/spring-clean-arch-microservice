package com.jobee.admin.service.infrastructure.configuration;

import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JacksonConfig {
    @Bean
    public Jackson2ObjectMapperBuilderCustomizer jacksonCustomizer() {
        return builder -> {
            builder.featuresToDisable(MapperFeature.SORT_PROPERTIES_ALPHABETICALLY);
            builder.propertyNamingStrategy(PropertyNamingStrategies.LOWER_CAMEL_CASE);
        };
    }
}

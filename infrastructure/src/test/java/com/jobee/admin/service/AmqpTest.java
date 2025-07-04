package com.jobee.admin.service;

import com.jobee.admin.service.infrastructure.configuration.WebServiceConfig;
import org.junit.jupiter.api.Tag;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@ActiveProfiles("test-integration")
@SpringBootTest(classes = WebServiceConfig.class)
@Tag("integrationTest")
public @interface AmqpTest {
}
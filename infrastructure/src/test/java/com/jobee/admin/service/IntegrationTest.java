package com.jobee.admin.service;

import com.jobee.admin.service.infrastructure.configuration.WebServiceConfig;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@ActiveProfiles("test")
@SpringBootTest(classes = WebServiceConfig.class)
@ExtendWith(CleanupExtension.class)
public @interface IntegrationTest {
}

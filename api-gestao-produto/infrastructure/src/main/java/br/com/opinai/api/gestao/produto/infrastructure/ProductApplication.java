package br.com.opinai.api.gestao.produto.infrastructure;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.AbstractEnvironment;

/**
 * @SpringBootApplication
 * public class ProductServiceApplication {
 *     public static void main(String[] args) {
 *         new SpringApplicationBuilder(ProductServiceApplication.class)
 *             .profiles("development")
 *             .run(args);
 *     }
 * }
 */

@SpringBootApplication
public class ProductApplication {
    public static void main(String[] args) {
        System.setProperty(AbstractEnvironment.ACTIVE_PROFILES_PROPERTY_NAME, "development");
        SpringApplication.run(WebServiceConfig.class, args);
    }
}
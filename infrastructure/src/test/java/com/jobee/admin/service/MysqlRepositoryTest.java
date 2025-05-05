package com.jobee.admin.service;

import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.test.context.ActiveProfiles;

import java.lang.annotation.*;

// Por esta ser uma anotacao
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Inherited
// Configuração de teste posso colocar direto na classe que vai testar
// Usar essa anotação  @SpringBootTest quando for fazer um test E2E, como esse é um teste que não precisa levantar todo o spring vamos usar o @DataJpaTest
// @SpringBootTest => que sobe toda a aplicação spring
// @DataJpaTest => sobe o necessário menos as classes injetaveis, neste caso precisa ler manualmente via REGEX
@ActiveProfiles("test-integration")
@ComponentScan(
        basePackages = "com.jobee.admin.service",
        useDefaultFilters = false,
        includeFilters = {
                @ComponentScan.Filter(type = FilterType.REGEX, pattern = ".*RepositoryMysql")
        }
)
@DataJpaTest
@ExtendWith(CleanupExtension.class)
public @interface MysqlRepositoryTest {
}

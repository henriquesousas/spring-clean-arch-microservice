package com.jobee.admin.service;

import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.springframework.data.repository.CrudRepository;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Collection;

public class MysqlCleanupExtension implements BeforeEachCallback {
    @Override
    public void beforeEach(final ExtensionContext context) {
        // TODO: Use this implementation to clean up the database
//        final var appContext = SpringExtension.getApplicationContext(context);
//        cleanUp(List.of(
//               // appContext.getBean(GenreJpaRepository.class),
//                appContext.getBean(CategoryJpaRepository.class)
//        ));
//        final var em = appContext.getBean(TestEntityManager.class);
//        em.flush();
//        em.clear();

        final var repositories = SpringExtension
                .getApplicationContext(context)
                .getBeansOfType(CrudRepository.class)
                .values();

        cleanUp(repositories);
    }

    private void cleanUp(final Collection<CrudRepository> repositories) {
        repositories.forEach(CrudRepository::deleteAll);
    }
}
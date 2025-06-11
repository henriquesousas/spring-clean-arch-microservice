package com.jobee.admin.service.infrastructure;

import com.jobee.admin.service.infrastructure.configuration.WebServiceConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.AbstractEnvironment;


@SpringBootApplication
public class Main {
    public static void main(String[] args) {
        System.setProperty(AbstractEnvironment.ACTIVE_PROFILES_PROPERTY_NAME, "development");
        SpringApplication.run(WebServiceConfig.class, args);
    }

//    @Bean
//    public ApplicationRunner runner(CategoryRepository repository) {
//        return  args -> {
//            List<CategoryModel> all = repository.findAll();l
//            Category category = Category.newCategory("Filmes", "desc", true);
//            repository.save(CategoryModel.from(category));
//            repository.deleteAll();
//        };
//    }

//    @RabbitListener(queues = "review.created.queue")
//    void dummyListener() {
//    }
}
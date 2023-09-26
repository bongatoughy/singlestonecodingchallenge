package com.kyle.singlestonecodingchallenge;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class LoadDatabase {

    private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

    @Bean
    CommandLineRunner initDatabase(ContactRepository repository) {

        return args -> {
            log.info("Preloading " + repository.save(new Contact("Bilbo", "B", "baggins", "nightingale", "chicago", "IL", "11111", "11", "home", "b@b.com")));
            log.info("Preloading " + repository.save(new Contact("Frodo", "F", "Baggins", "oak", "detroit", "MI", "22222", "99", "home", "f@f.com")));
        };
    }
}

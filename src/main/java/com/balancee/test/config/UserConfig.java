package com.balancee.test.config;

import com.balancee.test.models.User;
import com.balancee.test.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class UserConfig {

    @Bean
    CommandLineRunner commandLineRunner(UserRepository repository){
        return args -> {
            User jojo = new User("josiah","ademeso");
            User fuad = new User("fuad","adekunle");
            repository.saveAll(List.of(jojo,fuad));
        };
    }
}

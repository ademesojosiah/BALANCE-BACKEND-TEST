package com.balancee.test.config;

import com.balancee.test.models.Task;
import com.balancee.test.models.User;
import com.balancee.test.repository.TaskRepository;
import com.balancee.test.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;
import java.util.List;

@Configuration
public class UserConfig {


    //Seeding data to the database
    @Bean
    CommandLineRunner commandLineRunner(UserRepository repository, TaskRepository taskRepository, PasswordEncoder encoder){
        return args -> {
            User jojo = new User("josiah",encoder.encode("ademeso"));
            User fuad = new User("fuad",encoder.encode("adekunle"));
            Task task1 = new Task("mariam","yee3", LocalDate.of(2000,6,6),jojo);
            repository.saveAll(List.of(jojo,fuad));
            taskRepository.save(task1);
        };
    }
}

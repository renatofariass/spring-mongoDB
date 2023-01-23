package com.renatoalberto.workshopmongo.config;

import com.renatoalberto.workshopmongo.domain.User;
import com.renatoalberto.workshopmongo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

@Configuration
public class Instantiation implements CommandLineRunner {
    @Autowired
    UserRepository userRepository;

    @Override
    public void run(String... args) throws Exception {
        //cleaning mongoDB collection
        userRepository.deleteAll();

        //inserting users
        User maria = new User(null, "Maria Brown", "maria@gmail.com");
        User alex = new User(null, "Alex Green", "alex@gmail.com");
        User bob = new User(null, "Bob Grey", "bob@gmail.com");

        //saving
        userRepository.saveAll(Arrays.asList(maria, alex, bob));
    }
}

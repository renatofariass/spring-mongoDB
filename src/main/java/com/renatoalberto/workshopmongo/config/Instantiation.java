package com.renatoalberto.workshopmongo.config;

import com.renatoalberto.workshopmongo.domain.Post;
import com.renatoalberto.workshopmongo.domain.User;
import com.renatoalberto.workshopmongo.repository.PostRepository;
import com.renatoalberto.workshopmongo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.TimeZone;

@Configuration
public class Instantiation implements CommandLineRunner {
    @Autowired
    UserRepository userRepository;

    @Autowired
    PostRepository postRepository;

    @Override
    public void run(String... args) throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        sdf.setTimeZone(TimeZone.getTimeZone("GMT"));

        //cleaning mongoDB collection
        userRepository.deleteAll();

        //inserting users
        User maria = new User(null, "Maria Brown", "maria@gmail.com");
        User alex = new User(null, "Alex Green", "alex@gmail.com");
        User bob = new User(null, "Bob Grey", "bob@gmail.com");

        //inserting posts
        Post post1 = new Post(null,sdf.parse("30/01/2023"),"Partiu viagem!",
                "Vou viajar para São Paulo, abraços!!", maria
                );
        Post post2 = new Post(null,sdf.parse("01/02/2023"),"Bom dia!",
                "Acordei feliz hoje!", maria
        );

        //saving
        userRepository.saveAll(Arrays.asList(maria, alex, bob));
        postRepository.saveAll(Arrays.asList(post1, post2));
    }
}

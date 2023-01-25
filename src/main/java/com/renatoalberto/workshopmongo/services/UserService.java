package com.renatoalberto.workshopmongo.services;

import com.renatoalberto.workshopmongo.domain.User;
import com.renatoalberto.workshopmongo.repository.UserRepository;
import com.renatoalberto.workshopmongo.services.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public List<User> findAll() {
       return userRepository.findAll();
    }

    public User findById(String id) {
        User user = userRepository.findById(id).orElse(null);
        if(user == null) {
            throw new ObjectNotFoundException("Objeto não encontrado");
        }
        return user;
    }
}

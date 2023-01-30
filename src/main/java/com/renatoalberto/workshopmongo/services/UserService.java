package com.renatoalberto.workshopmongo.services;

import com.renatoalberto.workshopmongo.domain.User;
import com.renatoalberto.workshopmongo.dto.UserDTO;
import com.renatoalberto.workshopmongo.repository.UserRepository;
import com.renatoalberto.workshopmongo.services.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public User insert(User user) {
        return userRepository.insert(user);
    }

    public User fromDTO(UserDTO userDTO) {
        return new User(userDTO.getId(), userDTO.getName(), userDTO.getEmail());
    }

    public void delete(String id) {
        findById(id);
        userRepository.deleteById(id);
    }
}
package com.renatoalberto.workshopmongo.resources;

import com.renatoalberto.workshopmongo.domain.User;
import com.renatoalberto.workshopmongo.dto.UserDTO;
import com.renatoalberto.workshopmongo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/users")
public class UserResource {
    @Autowired
    UserService userService;

    @GetMapping
    public ResponseEntity<List<UserDTO>> findAll() {
        List<User> list = userService.findAll();
        List<UserDTO> listDTO = list.stream().map(x -> new UserDTO(x)).collect(Collectors.toList());
        return ResponseEntity.ok().body(listDTO);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<UserDTO> findById(@PathVariable String id) {
        User user = userService.findById(id);
        return ResponseEntity.ok().body(new UserDTO(user));
    }

    @PostMapping
    public ResponseEntity<Void> insert(@RequestBody UserDTO obj) {
        User user = userService.fromDTO(obj);
        user = userService.insert(user);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").
                buildAndExpand(user.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

}

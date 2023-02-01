package com.renatoalberto.workshopmongo.resources;

import com.renatoalberto.workshopmongo.domain.Post;
import com.renatoalberto.workshopmongo.domain.User;
import com.renatoalberto.workshopmongo.dto.UserDTO;
import com.renatoalberto.workshopmongo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/users")
public class UserResource {
    @Autowired
    UserService userService;

    // return all users
    @GetMapping
    public ResponseEntity<List<UserDTO>> findAll() {
        List<User> list = userService.findAll();
        List<UserDTO> listDTO = list.stream().map(x -> new UserDTO(x)).collect(Collectors.toList());
        return ResponseEntity.ok().body(listDTO);
    }

    // find user by id
    @GetMapping(value = "/{id}")
    public ResponseEntity<UserDTO> findById(@PathVariable String id) {
        User user = userService.findById(id);
        return ResponseEntity.ok().body(new UserDTO(user));
    }

    // insert user
    @PostMapping
    public ResponseEntity<Void> insert(@RequestBody UserDTO obj) {
        User user = userService.fromDTO(obj);
        user = userService.insert(user);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").
                buildAndExpand(user.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    // delete user
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        userService.delete(id);
        return ResponseEntity.noContent().build();
    }

    // update user
    @PutMapping(value = "/{id}")
    public ResponseEntity<Void> update(@RequestBody UserDTO obj, @PathVariable String id) {
        User user = userService.fromDTO(obj);
        user.setId(id);
        user = userService.update(user);
        return ResponseEntity.noContent().build();
    }

    //return user by id and your posts
    @GetMapping(value = "/{id}/posts")
    public ResponseEntity<List<Post>> findPosts(@PathVariable String id) {
        User user = userService.findById(id);
        return ResponseEntity.ok().body(user.getPosts());
    }
}

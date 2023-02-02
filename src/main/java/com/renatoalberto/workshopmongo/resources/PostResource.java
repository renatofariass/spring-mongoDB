package com.renatoalberto.workshopmongo.resources;

import com.renatoalberto.workshopmongo.domain.Post;
import com.renatoalberto.workshopmongo.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(value = "/posts")
public class PostResource {
    @Autowired
    PostService postService;

    // find post by id
    @GetMapping(value = "/{id}")
    public ResponseEntity<Post> findById(@PathVariable String id) {
        Post post = postService.findById(id);
        return ResponseEntity.ok().body(post);
    }
}

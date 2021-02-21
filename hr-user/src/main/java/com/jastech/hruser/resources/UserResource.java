package com.jastech.hruser.resources;

import com.jastech.hruser.entities.User;
import com.jastech.hruser.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RefreshScope
@RestController
@RequestMapping(value = "/users")
public class UserResource {

    private final UserRepository repository;

    @Autowired
    public UserResource(UserRepository repository) {
        this.repository = repository;
    }

    @GetMapping()
    public ResponseEntity<User> findByEmail(@RequestParam("email") String email) {
        User obj = repository.findByEmail(email).get();
        return ResponseEntity.ok().body(obj);
    }

    @GetMapping(value = "{id}")
    public ResponseEntity<User> find(@PathVariable("id") Long id) {
        User obj = repository.findById(id).get();
        return ResponseEntity.ok().body(obj);
    }
}

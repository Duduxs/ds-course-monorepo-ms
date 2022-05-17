package com.edudev.msuser.resources;

import com.edudev.msuser.entities.User;
import com.edudev.msuser.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/users")
public class UserResource {

    @Autowired
    private UserRepository repository;

    @GetMapping("{id}")
    public ResponseEntity<User> findById(@PathVariable Long id){

        final var result = repository.findById(id).get();

        return ResponseEntity.ok(result);

    }

    @GetMapping(value = "search")
    public ResponseEntity<User> findByEmail(@RequestParam String email){

        final var result = repository.findByEmail(email).orElse(null);

        return ResponseEntity.ok(result);

    }

}

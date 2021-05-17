package com.br.hruser.resources;

import com.br.hruser.entities.UserEntity;
import com.br.hruser.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/users")
public class UserResource {

    @Autowired
    private UserRepository repository;

    @GetMapping(value = "/{id}")
    public ResponseEntity<UserEntity> findById(@PathVariable Long id) {
        UserEntity obj = repository.findById(id).get();
        return ResponseEntity.ok(obj);
    }

    @GetMapping(value = "/search")
    public ResponseEntity<UserEntity> findByEmail(@RequestParam String email) {
        UserEntity obj = repository.findByEmail(email);
        return ResponseEntity.ok(obj);
    }
}

package com.br.hroauth.resources;

import com.br.hroauth.entities.UserEntity;
import com.br.hroauth.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/users")
public class UserResource {

    @Autowired
    private UserService userService;

    @GetMapping(value = "/search")
    public ResponseEntity<UserEntity> findByEmail(@RequestParam String email) {
        try {
            UserEntity userEntity = userService.findByEmail(email);
            return ResponseEntity.ok(userEntity);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
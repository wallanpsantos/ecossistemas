package com.br.hruser.resources;

import com.br.hruser.entities.UserEntity;
import com.br.hruser.repositories.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/users")
public class UserResource {

    private static final Logger logger = LoggerFactory.getLogger(UserResource.class);

    @Value("${hr-user.config: Erro ao acessar config remota, usando Mock Local}")
    private String hrUserConfig;

    @Autowired
    private UserRepository repository;

    @GetMapping(value = "/configs")
    public ResponseEntity<Void> getConfigs() {
        logger.info("HR-USER CONFIG = " + hrUserConfig);
        return ResponseEntity.noContent().build();
    }

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

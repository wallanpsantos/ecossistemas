package com.br.hroauth.services;

import com.br.hroauth.entities.UserEntity;
import com.br.hroauth.feigns.UserFeignClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {

    private static Logger logger = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private UserFeignClient userFeignClient;

    /**
     * Metodo para fins didatico nao necessario
     *
     * @param email
     * @return userEntity
     */
    public UserEntity findByEmail(String email) {
        UserEntity userEntity = userFeignClient.findByEmail(email).getBody();
        if (userEntity == null) {
            logger.error("Email not found: " + email);
            throw new IllegalArgumentException("Email not found");
        }
        logger.info("Email found: " + email);
        return userEntity;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity userEntity = userFeignClient.findByEmail(username).getBody();
        if (userEntity == null) {
            logger.error("Email not found: " + username);
            throw new UsernameNotFoundException("Email not found");
        }
        logger.info("Email found: " + username);
        return userEntity;
    }
}
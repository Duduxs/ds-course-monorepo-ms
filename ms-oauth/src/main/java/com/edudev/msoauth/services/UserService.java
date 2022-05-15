package com.edudev.msoauth.services;

import com.edudev.msoauth.entities.User;
import com.edudev.msoauth.feignclients.UserFeignClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserFeignClient client;

    private static Logger logger = LoggerFactory.getLogger(UserService.class);

    public User findByEmail(String email) {
        User user = client.findByEmail(email).getBody();

        if(user == null) {
            logger.error("Email not found: " + email);
            throw new IllegalArgumentException("Email not found");
        }

        logger.info("Email found: " + email);

        return user;
    }

}

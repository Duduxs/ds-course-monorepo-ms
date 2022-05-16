package com.edudev.msoauth.services;

import com.edudev.msoauth.entities.Role;
import com.edudev.msoauth.entities.User;
import com.edudev.msoauth.feignclients.UserFeignClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserFeignClient client;

    private static Logger logger = LoggerFactory.getLogger(UserDetailsServiceImpl.class);

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = client.findByEmail(username).getBody();

        if(user == null) {
            logger.error("Email not found: " + username);
        }

        logger.info("Email found: " + username);
        logger.info("Roles: ");
        for (Role r : user.getRoles()) {
            logger.info(r.getRoleName());
        }

        return user;

    }
}

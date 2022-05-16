package com.edudev.msoauth.resources;

import com.edudev.msoauth.config.security.JwtProvider;
import com.edudev.msoauth.dtos.LoginDTO;
import com.edudev.msoauth.services.UserDetailsServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("auth")
@CrossOrigin(origins = "*", maxAge = 3600)
public class AuthenticationResource {

    @Autowired
    private UserDetailsServiceImpl service;

    @Autowired
    private JwtProvider jwtProvider;

    @Autowired
    private AuthenticationManager authenticationManager;

    private Logger logger = LogManager.getLogger(this.getClass());

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginDTO dto) {

        final var authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(dto.email(), dto.password()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        final String jwt = jwtProvider.generateJwt(authentication);

        return ResponseEntity.ok(jwt);

    }

}

//the logic for registering a user and verifying it
package com.mitarth.devtask.service;

import com.mitarth.devtask.dto.LoginRequest;
import com.mitarth.devtask.dto.RegisterRequest;
import com.mitarth.devtask.model.Users;
import com.mitarth.devtask.repo.repo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class userService {
    @Autowired
    private repo repo;

    @Autowired
    private BCryptPasswordEncoder encoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JWTService jwtService;

    public Users register(RegisterRequest request) {
        Users users = new Users();
        users.setUsername(request.getUsername());
        users.setPassword(encoder.encode(request.getPassword()));
        return repo.save(users);
    }
//thi
    public String verify(LoginRequest request) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
            if (authentication.isAuthenticated()) {
                return jwtService.generateToken(request.getUsername());
            }
            return "failed";
        } catch (Exception e) {
            e.printStackTrace();
            return e.getClass().getSimpleName() + " : " + e.getMessage();
        }
    }
}

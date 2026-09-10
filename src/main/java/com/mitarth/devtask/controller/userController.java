//the controller for home,register,login all of em
package com.mitarth.devtask.controller;

import com.mitarth.devtask.dto.LoginRequest;
import com.mitarth.devtask.dto.RegisterRequest;
import com.mitarth.devtask.model.Users;
import com.mitarth.devtask.service.userService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedHashMap;
import java.util.Map;

@RestController
public class userController {
    @Autowired
    private userService userservice;

    @GetMapping("/")
    public Map<String, Object> welcome() {
        Map<String, Object> response = new LinkedHashMap<>();
        response.put("message", "Hello!!!!!!!!, Welcome to DevStack 🚀");
        response.put("description", "A simple user auth backend built with Spring Boot (register + login with JWT).");

        Map<String, String> endpoints = new LinkedHashMap<>();
        endpoints.put("POST /register", "Create a new user. Body: { \"username\": \"\", \"email\": \"\", \"password\": \"\" }");
        endpoints.put("POST /login", "Login and get your JWT token. Body: { \"email\": \"\", \"password\": \"\" }");
        response.put("endpoints", endpoints);

        response.put("note", "Use the token from /login in the Authorization header (Bearer <token>) for protected routes.");
        response.put("github", "https://github.com/mitarthpathak/DevTask");

        return response;
    }

    @PostMapping("/register")
    public Users register(@RequestBody RegisterRequest request) {
        return userservice.register(request);
    }

    //if user ever reach this endpoint he/she will get their own JWT token by which they logged in
    //sounds cool right??
    @PostMapping("/login")
    public String login(@RequestBody LoginRequest request) {
        return userservice.verify(request);
    }
}

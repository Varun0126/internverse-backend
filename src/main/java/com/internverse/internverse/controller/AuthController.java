package com.internverse.internverse.controller;

import com.internverse.internverse.model.User;
import com.internverse.internverse.service.AuthService;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")

public class AuthController {

    @Autowired
    private AuthService service;

    @PostMapping("/register")
    public User register(@RequestBody User user){
        return service.register(user);
    }

    @PostMapping("/login")
    public Map<String,Object> login(@RequestBody User user){
        return service.login(user.getEmail(),user.getPassword());
    }
}
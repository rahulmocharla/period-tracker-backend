package com.login.demo.controller;

import com.login.demo.dto.LoginRequest;
import com.login.demo.dto.RegisterRequest;
import com.login.demo.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
@CrossOrigin("*")
public class AuthController {
    private final AuthService authService;

    @PostMapping("/register")
    public String register(@RequestBody RegisterRequest request){
        return authService.register(request);


    }

    @PostMapping("/login")
    public String login(@RequestBody LoginRequest request){
       return authService.login(request);


    }


}

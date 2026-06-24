package com.login.demo.controller;

import com.login.demo.dto.UserProfileResponse;
import com.login.demo.entity.User;
import com.login.demo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.Authenticator;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {

    private final UserRepository userRepository;

    @GetMapping("/profile")
    public UserProfileResponse getProfile(Authentication authentication){
        String email = authentication.getName();


        User user = userRepository
                .findByEmail(email)
                .orElseThrow();

        return new UserProfileResponse(
                user.getId(),
                user.getUsername(),
                user.getEmail()
        );
//        return userRepository
//                .findByEmail(email)
//                .orElseThrow(() -> new RuntimeException("User Not Found"));
    }

}

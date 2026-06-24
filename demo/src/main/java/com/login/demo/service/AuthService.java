package com.login.demo.service;

import com.login.demo.dto.LoginRequest;
import com.login.demo.dto.RegisterRequest;
import com.login.demo.entity.User;
import com.login.demo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public String register(RegisterRequest request){
        User user = new User();
        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        userRepository.save(user);

        return "Registration Successful";

    }
    public String login(LoginRequest request){
        User user =userRepository.findByEmail(request.getEmail()).orElseThrow();
        if(passwordEncoder.matches(request.getPassword(),user.getPassword())){
            return jwtService.generateToken(user.getEmail());
        }
        return "Invalid Credentials";

    }
}

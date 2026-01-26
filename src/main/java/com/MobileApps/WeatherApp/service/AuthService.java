package com.MobileApps.WeatherApp.service;

import com.MobileApps.WeatherApp.dto.LoginRequest;
import com.MobileApps.WeatherApp.entity.User;
import com.MobileApps.WeatherApp.repository.UserRepository;
import com.MobileApps.WeatherApp.config.JwtUtil;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public String login(LoginRequest request) {
        User user = userRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new RuntimeException("Invalid username or password"));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid username or password");
        }

        return jwtUtil.generateToken(user.getUsername());
    }
}

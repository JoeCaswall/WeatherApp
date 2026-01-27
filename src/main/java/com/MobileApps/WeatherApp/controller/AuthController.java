package com.MobileApps.WeatherApp.controller;

import com.MobileApps.WeatherApp.dto.LoginRequest;
import com.MobileApps.WeatherApp.dto.SignupRequest;
import com.MobileApps.WeatherApp.dto.UserDTO;
import com.MobileApps.WeatherApp.entity.User;
import com.MobileApps.WeatherApp.service.AuthService;
import com.MobileApps.WeatherApp.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;
    private final AuthService authService;

    @PostMapping("/signup")
    public UserDTO signup(@Valid @RequestBody SignupRequest request) throws IllegalAccessException {
        return userService.registerUser(request);
    }

    @PostMapping("/login")
    public String login(@Valid @RequestBody LoginRequest request) {
        return authService.login(request);
    }
}

package com.MobileApps.WeatherApp.service;

import com.MobileApps.WeatherApp.dto.SignupRequest;
import com.MobileApps.WeatherApp.dto.UserDTO;
import com.MobileApps.WeatherApp.entity.FavouriteLocation;
import com.MobileApps.WeatherApp.entity.User;
import com.MobileApps.WeatherApp.middleware.UserMapper;
import com.MobileApps.WeatherApp.repository.FavouriteLocationRepository;
import com.MobileApps.WeatherApp.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final FavouriteLocationRepository favouriteLocationRepository;

    @Transactional
    public UserDTO registerUser(SignupRequest request) throws IllegalAccessException {

        if (userRepository.existsByUsername(request.getUsername())) {
            throw new IllegalAccessException("Username already exists");
        }

        if (userRepository.existsByEmail(request.getEmail())) {
            throw new IllegalAccessException("Email already exists");
        }

        User user = new User();
        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));

        userRepository.save(user);

        FavouriteLocation london = new FavouriteLocation();
        london.setCityName("London");
        london.setLatitude(51.5074);
        london.setLongitude(-0.1278);
        london.setUser(user);

        favouriteLocationRepository.save(london);

        user.setDefaultLocation(london);
        User saved =  userRepository.save(user);
        return UserMapper.toDTO(saved);
    }
}

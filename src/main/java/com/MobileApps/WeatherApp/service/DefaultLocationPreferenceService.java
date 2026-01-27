package com.MobileApps.WeatherApp.service;

import com.MobileApps.WeatherApp.entity.FavouriteLocation;
import com.MobileApps.WeatherApp.entity.User;
import com.MobileApps.WeatherApp.repository.FavouriteLocationRepository;
import com.MobileApps.WeatherApp.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class DefaultLocationPreferenceService {

    private final UserRepository userRepo;
    private final FavouriteLocationRepository favRepo;

    public FavouriteLocation setDefaultLocation(String username, Long favouriteId) {
        User user = userRepo.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        FavouriteLocation fav = favRepo.findById(favouriteId)
                .orElseThrow(() -> new RuntimeException("Favourite not found"));

        if (!fav.getUser().getId().equals(user.getId())) {
            throw new RuntimeException("Not allowed");
        }

        user.setDefaultLocation(fav);
        userRepo.save(user);

        return fav;
    }

    public FavouriteLocation getDefaultLocation(String username) {
        User user = userRepo.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        return user.getDefaultLocation();
    }
}

package com.MobileApps.WeatherApp.service;

import com.MobileApps.WeatherApp.entity.FavouriteLocation;
import com.MobileApps.WeatherApp.entity.User;
import com.MobileApps.WeatherApp.middleware.FavouriteLocationMapper;
import com.MobileApps.WeatherApp.repository.FavouriteLocationRepository;
import com.MobileApps.WeatherApp.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FavouriteLocationService {

    private final FavouriteLocationRepository repo;
    private final UserRepository userRepo;

    public FavouriteLocationService(FavouriteLocationRepository repo, UserRepository userRepo) {
        this.repo = repo;
        this.userRepo = userRepo;
    }

    public FavouriteLocation addFavourite(String username, String city, Double lat, Double lon) {
        User user = userRepo.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));
        // Early return if city is already in favourites - removes need for error handling
        // Duplicates are not allowed in the db entity - this is just for graceful handling
        if (repo.existsByUserAndCityNameIgnoreCase(user, city)) {
            return repo.findByUserAndCityName(user, city);
        }

        FavouriteLocation fav = new FavouriteLocation();
        fav.setCityName(city);
        fav.setLatitude(lat);
        fav.setLongitude(lon);
        fav.setUser(user);

        return repo.save(fav);
    }

    public List<FavouriteLocation> getFavourites(String username) {
        User user = userRepo.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        return repo.findByUser(user);
    }

    public void deleteFavourite(Long id, String username) {
        FavouriteLocation fav = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Favourite not found"));

        if (!fav.getUser().getUsername().equals(username)) {
            throw new RuntimeException("Not allowed");
        }

        repo.delete(fav);
    }
}

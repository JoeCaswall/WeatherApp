package com.MobileApps.WeatherApp.repository;

import com.MobileApps.WeatherApp.entity.FavouriteLocation;
import com.MobileApps.WeatherApp.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FavouriteLocationRepository extends JpaRepository<FavouriteLocation, Long> {
    List<FavouriteLocation> findByUser(User user);
}

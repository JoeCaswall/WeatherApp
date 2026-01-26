package com.MobileApps.WeatherApp.controller;

import com.MobileApps.WeatherApp.dto.FavouriteLocationRequestDTO;
import com.MobileApps.WeatherApp.entity.FavouriteLocation;
import com.MobileApps.WeatherApp.service.FavouriteLocationService;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/favourites")
public class FavouriteLocationController {

    private final FavouriteLocationService service;

    public FavouriteLocationController(FavouriteLocationService service) {
        this.service = service;
    }

    @PostMapping
    public FavouriteLocation addFavourite(@RequestBody FavouriteLocationRequestDTO req,
                                          Authentication auth) {
        return service.addFavourite(
                auth.getName(),
                req.getCityName(),
                req.getLatitude(),
                req.getLongitude()
        );
    }

    @GetMapping
    public List<FavouriteLocation> getFavourites(Authentication auth) {
        return service.getFavourites(auth.getName());
    }

    @DeleteMapping("/{id}")
    public void deleteFavourite(@PathVariable Long id, Authentication auth) {
        service.deleteFavourite(id, auth.getName());
    }
}

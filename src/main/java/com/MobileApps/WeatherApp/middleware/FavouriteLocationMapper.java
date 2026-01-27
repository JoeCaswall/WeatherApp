package com.MobileApps.WeatherApp.middleware;

import com.MobileApps.WeatherApp.dto.FavouriteLocationDTO;
import com.MobileApps.WeatherApp.entity.FavouriteLocation;

public class FavouriteLocationMapper {

    public static FavouriteLocationDTO toDTO(FavouriteLocation fav) {
        FavouriteLocationDTO dto = new FavouriteLocationDTO();
        dto.setId(fav.getId());
        dto.setCityName(fav.getCityName());
        dto.setLatitude(fav.getLatitude());
        dto.setLongitude(fav.getLongitude());
        return dto;
    }
}

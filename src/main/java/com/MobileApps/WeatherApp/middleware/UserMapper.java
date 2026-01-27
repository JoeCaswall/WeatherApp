package com.MobileApps.WeatherApp.middleware;

import com.MobileApps.WeatherApp.dto.UserDTO;
import com.MobileApps.WeatherApp.entity.User;

public class UserMapper {

    public static UserDTO toDTO(User user) {
        UserDTO dto = new UserDTO();
        dto.setId(user.getId());
        dto.setUsername(user.getUsername());
        dto.setEmail(user.getEmail());

        if (user.getDefaultLocation() != null) {
            dto.setDefaultLocation(FavouriteLocationMapper.toDTO(user.getDefaultLocation()));
        }

        return dto;
    }
}

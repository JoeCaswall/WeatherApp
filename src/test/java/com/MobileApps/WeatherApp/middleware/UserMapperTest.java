package com.MobileApps.WeatherApp.middleware;

import com.MobileApps.WeatherApp.dto.UserDTO;
import com.MobileApps.WeatherApp.entity.User;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserMapperTest {

    @Test
    void toDTO_mapsBasicFields() {
        User user = new User();
        user.setId(1L);
        user.setUsername("joe");
        user.setEmail("joe@example.com");

        UserDTO dto = UserMapper.toDTO(user);

        assertEquals(1L, dto.getId());
        assertEquals("joe", dto.getUsername());
        assertEquals("joe@example.com", dto.getEmail());
        assertNull(dto.getDefaultLocation());
    }
}

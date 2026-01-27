package com.MobileApps.WeatherApp.controller;

import com.MobileApps.WeatherApp.config.JwtUtil;
import com.MobileApps.WeatherApp.entity.FavouriteLocation;
import com.MobileApps.WeatherApp.entity.User;
import com.MobileApps.WeatherApp.repository.UserRepository;
import com.MobileApps.WeatherApp.service.FavouriteLocationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class FavouriteLocationControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private JwtUtil jwtUtil;

    @MockBean
    private FavouriteLocationService favouriteLocationService;

    @MockBean
    private UserRepository userRepository;

    private String jwtToken;

    @BeforeEach
    void setUp() {
        // Set up a user and mock UserRepository
        User user = new User();
        user.setId(1L);
        user.setUsername("joe");
        user.setPassword("password");
        user.setEmail("joe@email.com");
        when(userRepository.findByUsername("joe")).thenReturn(Optional.of(user));
        // Generate JWT for user 'joe'
        jwtToken = jwtUtil.generateToken("joe");
    }

    @Test
    void testGetFavourites_withJwtFilter() throws Exception {
        // Set up a favourite location
        User user = new User();
        user.setId(1L);
        user.setUsername("joe");
        user.setPassword("password");
        user.setEmail("joe@email.com");

        FavouriteLocation fav = new FavouriteLocation();
        fav.setId(10L);
        fav.setCityName("London");
        fav.setLatitude(51.5074);
        fav.setLongitude(-0.1278);
        fav.setUser(user);

        when(favouriteLocationService.getFavourites("joe")).thenReturn(List.of(fav));

        mockMvc.perform(get("/api/favourites")
                        .header("Authorization", "Bearer " + jwtToken)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].cityName").value("London"))
                .andExpect(jsonPath("$[0].user").doesNotExist())
                .andExpect(jsonPath("$[0].defaultLocation").doesNotExist());
    }
}
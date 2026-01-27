package com.MobileApps.WeatherApp.controller;

import com.MobileApps.WeatherApp.entity.FavouriteLocation;
import com.MobileApps.WeatherApp.entity.User;
import com.MobileApps.WeatherApp.service.FavouriteLocationService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(FavouriteLocationController.class)
class FavouriteLocationControllerRecursionTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private FavouriteLocationService service;

    @Test
    @WithMockUser(username = "joe")
    void testNoInfiniteRecursion() throws Exception {

        User user = new User();
        user.setId(1L);
        user.setUsername("joe");

        FavouriteLocation fav = new FavouriteLocation();
        fav.setId(10L);
        fav.setCityName("London");
        fav.setLatitude(51.5074);
        fav.setLongitude(-0.1278);
        fav.setUser(user);

        when(service.getFavourites("joe")).thenReturn(List.of(fav));

        mockMvc.perform(get("/api/favourites")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].cityName").value("London"))
                .andExpect(jsonPath("$[0].user").doesNotExist())
                .andExpect(jsonPath("$[0].defaultLocation").doesNotExist());
    }
}

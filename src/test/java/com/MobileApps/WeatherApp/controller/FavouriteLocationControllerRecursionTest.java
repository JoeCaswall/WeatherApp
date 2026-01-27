package com.MobileApps.WeatherApp.controller;

import com.MobileApps.WeatherApp.entity.FavouriteLocation;
import com.MobileApps.WeatherApp.entity.User;
import com.MobileApps.WeatherApp.service.FavouriteLocationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.util.List;

import static org.mockito.Mockito.when;

class FavouriteLocationControllerRecursionTest {

    @Mock
    private FavouriteLocationService service;

    @InjectMocks
    private FavouriteLocationController controller;

    private WebTestClient client;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
        client = WebTestClient.bindToController(controller).build();
    }

    @Test
    void testNoInfiniteRecursion() {

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

        client.get()
                .uri("/api/favourites")
                .header("Authorization", "Bearer dummy")
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$[0].cityName").isEqualTo("London")
                // Check the controller is not returning entities
                .jsonPath("$[0].user").doesNotExist()
                .jsonPath("$[0].defaultLocation").doesNotExist();
    }
}

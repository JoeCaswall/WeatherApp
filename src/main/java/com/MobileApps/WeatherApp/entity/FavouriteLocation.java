package com.MobileApps.WeatherApp.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "favourite_locations")
@Data
public class FavouriteLocation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String cityName;
    private Double latitude;
    private Double longitude;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}

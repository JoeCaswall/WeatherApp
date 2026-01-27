package com.MobileApps.WeatherApp.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
// Same user cannot have the same city name as a favourite
@Table(
        name = "favourite_locations",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"user_id", "city_name"})
        })
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

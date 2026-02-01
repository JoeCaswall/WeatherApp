package com.MobileApps.WeatherApp.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "users")
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password; // hashed

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String role = "USER";
//
//    @Column(nullable = false)
//    private int isActive = 1;

    @OneToOne
    @JoinColumn(name="default_location_id")
    private FavouriteLocation defaultLocation;
}

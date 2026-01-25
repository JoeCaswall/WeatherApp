package com.MobileApps.WeatherApp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@AllArgsConstructor
@Data
public class WeatherbitData {

    private String wind_cdir;
    private int rh;
    private String pod;
    private double lon;
    private double pres;
    private String timezone;
    private String ob_time;
    private String country_code;
    private int clouds;
    private double vis;
    private double wind_spd;
    private double gust;
    private String wind_cdir_full;
    private double app_temp;
    private String state_code;
    private long ts;
    private double h_angle;
    private double dewpt;

    private WeatherbitCurrentConditions weather;

    private double uv;
    private int aqi;
    private String station;
    private List<String> sources;

    private int wind_dir;
    private double elev_angle;
    private String datetime;
    private double precip;
    private double ghi;
    private double dni;
    private double dhi;
    private double solar_rad;

    private String city_name;
    private String sunrise;
    private String sunset;

    private double temp;
    private double lat;
    private double slp;
}


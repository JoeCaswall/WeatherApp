package com.MobileApps.WeatherApp.service;

import com.MobileApps.WeatherApp.dto.CityRecord;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Objects;

@Service
@Data
@AllArgsConstructor
public class CityDataLoader {

    private final List<CityRecord> cities;

    private List<CityRecord> loadCities() {
        try (var reader = new BufferedReader(new InputStreamReader(
                Objects.requireNonNull(getClass().getResourceAsStream("/cities_list/cities_20000.csv"))
        ))) {
            return reader.lines()
                    .skip(1) // skip header
                    .map(this::parseLine)
                    .toList();
        } catch (Exception e) {
            throw new IllegalStateException("Failed to load city metadata", e);
        }
    }

    private CityRecord parseLine(String line) {
        String[] parts = line.split(",");
        return new CityRecord(
                Integer.parseInt(parts[0]),
                parts[1],
                parts[2],
                parts[3],
                parts[4],
                Double.parseDouble(parts[5]),
                Double.parseDouble(parts[6])
        );
    }
}

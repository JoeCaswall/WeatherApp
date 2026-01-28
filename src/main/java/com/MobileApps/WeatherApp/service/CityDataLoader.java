package com.MobileApps.WeatherApp.service;

import com.MobileApps.WeatherApp.dto.CityRecord;
import lombok.Data;
import org.jspecify.annotations.NonNull;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@Data
public class CityDataLoader {

    private final List<CityRecord> cities;

    public CityDataLoader() {
        System.out.println(">>> CityDataLoader constructor called");
        this.cities = loadCities();
        System.out.println(">>> Loaded " + cities.size() + " cities");
    }

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

    CityRecord parseLine(String line) {
        List<String> parts = getStrings(line);

        if (parts.size() != 7) {
            throw new IllegalStateException("Invalid CSV row: " + line);
        }

        return new CityRecord(
                Integer.parseInt(parts.get(0)),
                parts.get(1),
                parts.get(2),
                parts.get(3),
                parts.get(4),
                Double.parseDouble(parts.get(5)),
                Double.parseDouble(parts.get(6))
        );
    }

    /*
    This handles an edge case where the country name contained commas such as "Bahamas, The" which broke the
    parsing Logic and caused an exception as the Search was expecting a double not a string
     */
    private static @NonNull List<String> getStrings(String line) {
        List<String> parts = new ArrayList<>();
        StringBuilder current = new StringBuilder();
        boolean inQuotes = false;

        for (int i = 0; i < line.length(); i++) {
            char c = line.charAt(i);

            if (c == '"') {
                inQuotes = !inQuotes;
            } else if (c == ',' && !inQuotes) {
                parts.add(current.toString().trim());
                current.setLength(0);
            } else {
                current.append(c);
            }
        }

        parts.add(current.toString().trim());
        return parts;
    }

}

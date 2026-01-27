package com.MobileApps.WeatherApp.service;

import com.MobileApps.WeatherApp.dto.CityRecord;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CityDataLoaderTest {

    @Test
    void parseLine_handlesQuotedCommas() {
        String line = "1234,Freeport,,BS,\"Bahamas, The\",26.5333,-78.7000";

        CityDataLoader loader = new CityDataLoader();
        CityRecord record = loader.parseLine(line);

        assertEquals(1234, record.cityId());
        assertEquals("Freeport", record.cityName());
        assertEquals("", record.stateCode());
        assertEquals("BS", record.countryCode());
        assertEquals("Bahamas, The", record.countryFull());
        assertEquals(26.5333, record.lat());
        assertEquals(-78.7000, record.lon());
    }
}

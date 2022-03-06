package Models;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.math.BigDecimal;
import java.math.RoundingMode;

import static org.junit.jupiter.api.Assertions.*;

class LocationTest {

    @ExtendWith(DatabaseRule.class)
    DatabaseRule databaseRule = new DatabaseRule();

    Location location;
    public Location setUpLocation() throws Exception {
        return new Location("Heron's Perch",45.03,79.54);
    }

    @Test
    @DisplayName("Location Instantiates correctly")
    public void Location_InstantiatesCorrectly_True() throws Exception {
        location = setUpLocation();
        assertTrue(location instanceof Location);
    }

    @Test
    @DisplayName("Location Instantiates with properties")
    public void Location_InstantiatesWithProperties () throws Exception {
        location = setUpLocation();
        assertEquals("Heron's Perch",location.getName());
        BigDecimal lat = new BigDecimal(45.03).setScale(2, RoundingMode.DOWN);
        BigDecimal loong = new BigDecimal(79.54).setScale(2, RoundingMode.DOWN);
        assertEquals(lat, location.getLatitude());
        assertEquals(loong,location.getLongitude());
    }

    @Test
    @DisplayName("Save saves Location")
    public void save_SavesAllLocations() throws Exception {
        location = setUpLocation();
        Location location1 = new Location("Amanaki",23.43,145.34);
        location.saveLocation();
        location1.saveLocation();

        assertEquals(2,Location.all().size());

    }

    @Test
    @DisplayName("List contains locations")
    public void all_ContainsAllLocations() throws Exception {
        location = setUpLocation();
        Location location1 = new Location("Amanaki",23.43,145.34);
        location.saveLocation();
        location1.saveLocation();
        assertTrue(Location.all().contains(location));
        assertTrue(Location.all().contains(location1));
    }

    @Test
    @DisplayName("find finds location")
    public void find_findsLocations() throws Exception {
        location = setUpLocation();
        Location location1 = new Location("Amanaki",23.43,145.34);
        location.saveLocation();
        location1.saveLocation();
       assertEquals(location1,Location.find(location1.getId()));
    }



}
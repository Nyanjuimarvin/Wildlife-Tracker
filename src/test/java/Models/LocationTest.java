package Models;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LocationTest {

    Location location;
    public Location setUpLocation(){
        return new Location("Heron's Perch",45.03,79.54);
    }

    @Test
    @DisplayName("Location Instantiates correctly")
    public void Location_InstantiatesCorrectly_True(){
        location = setUpLocation();
        assertTrue(location instanceof Location);
    }

    @Test
    @DisplayName("Location Instantiates with properties")
    public void Location_InstantiatesWithProperties (){
        location = setUpLocation();
        assertEquals("Heron's Perch",location.getName());
        assertEquals(45.03, location.getLatitude());
        assertEquals(79.54,location.getLongitude());
    }

}
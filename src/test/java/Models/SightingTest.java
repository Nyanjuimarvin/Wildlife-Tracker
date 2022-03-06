package Models;


import org.joda.time.DateTime;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.Date;
import java.sql.Timestamp;

import static org.junit.jupiter.api.Assertions.*;

class SightingTest {
    @ExtendWith(DatabaseRule.class)
    public DatabaseRule databaseRule = new DatabaseRule();

    @Test
    @DisplayName("Sighting Instantiates Correctly")
    public void Sighting_CorrectlyInstantiates(){
        Sighting sighting = new Sighting(3,4533,4);
        assertTrue(sighting instanceof Sighting);
    }

    @Test
    @DisplayName("Instantiates With Time")
    public void Sighting_InstantiatesWithTime(){
        Sighting sighting = new Sighting(3,4533,4);
        sighting.saveSighting();
        System.out.println(sighting.getId());
        Timestamp savedSighting = Sighting.find(sighting.getId()).getTimesighted();
        Timestamp rightNow = new Timestamp(new Date().getTime());
        //Joda Time
        DateTime saved = new DateTime(savedSighting);
        DateTime now = new DateTime(rightNow);
        assertEquals(now.dayOfWeek(),saved.dayOfWeek());
    }

    @Test
    @DisplayName("Instantiates with id")
    public void Sighting_InstantiatesWithId(){
        Sighting sighting = new Sighting(3,4533,4);
        int initialId = sighting.getId();
        sighting.saveSighting();
        assertNotEquals(initialId,Sighting.find(sighting.getId()).getId());
    }

    @Test
    @DisplayName("All Sightings In a List")
    public void all_SightingsAreAddedToList(){
        Sighting sighting = new Sighting(3,4533,4);
        Sighting sighting1 = new Sighting(5,564,65);
        sighting.saveSighting();
        sighting1.saveSighting();
        assertEquals(2,Sighting.allSightings().size());
    }

    @Test
    @DisplayName("List Contains All Sightings")
    public void all_ContainsAllSightings(){
        Sighting sighting = new Sighting(3,4533,4);
        Sighting sighting1 = new Sighting(5,564,65);
        sighting.saveSighting();
        sighting1.saveSighting();
        assertTrue(Sighting.allSightings().contains(sighting));
        assertTrue(Sighting.allSightings().contains(sighting1));
    }

    @Test
    @DisplayName("Find Finds Sighting")
    public void find_ReturnsSighting(){
        Sighting sighting = new Sighting(3,4533,4);
        Sighting sighting1 = new Sighting(5,564,65);
        sighting.saveSighting();
        sighting1.saveSighting();
        assertEquals(sighting,Sighting.find(sighting.getId()));
    }

    @Test
    @DisplayName("Delete Sightings")
    public void delete_DeletesAllSightings(){
        Sighting sighting = new Sighting(3,4533,4);
        Sighting sighting1 = new Sighting(5,564,65);
        sighting.saveSighting();
        sighting1.saveSighting();
        Sighting.deleteAll();
        assertEquals(0,Sighting.allSightings().size());
    }
}
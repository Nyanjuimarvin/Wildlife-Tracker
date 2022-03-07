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
        Timestamp savedSighting = Sighting.find(sighting.getId()).getTimesighted();
        System.out.println(savedSighting);
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

    @Test
    @DisplayName("Delete by Id")
    public void deleteById_DeletesSighting(){
        Sighting sighting = new Sighting(3,4533,4);
        Sighting sighting1 = new Sighting(5,564,65);
        sighting.saveSighting();
        sighting1.saveSighting();
        Sighting.deletebyId(sighting1.getId());
        assertFalse(Sighting.allSightings().contains(sighting1));
    }

    @Test
    @DisplayName("Returns Sighted Animal")
    public void endangeredAnimalSighted() throws Exception{
        Ranger ranger = new Ranger("Colt","212-345-234123",4567);
        ranger.save();
        Location location = new Location("Falkreath",54.56,123.34);
        location.saveLocation();
        EndangeredAnimal animal = new EndangeredAnimal("Yellow Bird","Young","Okay",ranger.getId());
        animal.saveAnimal();
        Sighting sighting = new Sighting(animal.getId(),ranger.getId(),location.getId());
        assertEquals(animal,sighting.eAnimalSighted());
    }

    @Test
    @DisplayName("Returns Sighted Animal")
    public void unthreatenedAnimalSighted() throws Exception{
        Ranger ranger = new Ranger("Colt","212-345-234123",4567);
        ranger.save();
        Location location = new Location("Falkreath",54.56,123.34);
        location.saveLocation();
        UnthreatenedAnimal animal = new UnthreatenedAnimal("Yellow Bird","Young","Okay",ranger.getId());
        animal.saveAnimal();
        Sighting sighting = new Sighting(animal.getId(),ranger.getId(),location.getId());
        assertEquals(animal,sighting.uAnimalSighted());
    }

    @Test
    @DisplayName("Returns Location")
    public void placeSighted_ReturnsLocation() throws Exception{
        Ranger ranger = new Ranger("Colt","212-345-234123",4567);
        ranger.save();
        Location location = new Location("Falkreath",54.56,123.34);
        location.saveLocation();
        UnthreatenedAnimal animal = new UnthreatenedAnimal("Yellow Bird","Young","Okay",ranger.getId());
        animal.saveAnimal();
        Sighting sighting = new Sighting(animal.getId(),ranger.getId(),location.getId());
        sighting.saveSighting();
//        System.out.println(Sighting.find(sighting.getId()).getTimesighted());
        assertEquals(location,sighting.placeSighted());
    }

    @Test
    @DisplayName("Returns Ranger")
    public void rangerRecord_ReturnsRanger() throws Exception{
        Ranger ranger = new Ranger("Colt","212-345-234123",4567);
        ranger.save();
        Location location = new Location("Falkreath",54.56,123.34);
        location.saveLocation();
        UnthreatenedAnimal animal = new UnthreatenedAnimal("Yellow Bird","Young","Okay",ranger.getId());
        animal.saveAnimal();
        Sighting sighting = new Sighting(animal.getId(),ranger.getId(),location.getId());
        sighting.saveSighting();
//        System.out.println(Sighting.find(sighting.getId()).getTimesighted());
        assertEquals(ranger,sighting.recordRanger());
    }
}
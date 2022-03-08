package Models;

import Exceptions.InvalidEntryException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class EndangeredAnimalTest {

    @ExtendWith(DatabaseRule.class)
    public DatabaseRule databaseRule = new DatabaseRule();

    EndangeredAnimal animal;

    public EndangeredAnimal setUp() throws Exception {
        return new EndangeredAnimal("Dodo","Adult","Healthy",4);
    }



    @Test
    @DisplayName("Instantiates Correctly")
    public void EndangeredAnimal_InstantiatesCorrectly_True() throws Exception {
        EndangeredAnimal animal = new EndangeredAnimal("Dodo","Adult","Healthy",4);
        assertTrue(animal instanceof EndangeredAnimal);
    }

    @Test
    @DisplayName("Instantiates with properties")
    public void EndangeredAnimal_InstantiatesWithProperties_True() throws Exception {
        EndangeredAnimal animal = new EndangeredAnimal("Dodo","Adult","Healthy",4);
        assertEquals("Dodo",animal.getName());
        assertEquals("Adult",animal.getAgeRange());
        assertEquals("Healthy",animal.getHealth());
        assertEquals(4,animal.getRangerId());
    }

    @Test
    @DisplayName("Instantiates with id")
    public void EndangeredAnimal_InstantiatesWithId_True() throws Exception {
        EndangeredAnimal animal = new EndangeredAnimal("Dodo","Adult","Healthy",4);
        int initialId = animal.getId();
        animal.saveAnimal();
        assertNotEquals(initialId,animal.getId());
    }

    @Test
    @DisplayName("Save saves all Animals")
    public void save_SavesAllAnimals () throws Exception {
        animal = setUp();
        animal.saveAnimal();
        EndangeredAnimal animal1 = new EndangeredAnimal("Red Panda","Young","Okay",3433);
        animal1.saveAnimal();
        assertEquals(2,EndangeredAnimal.all().size());
    }

    @Test
    @DisplayName("All Animals Are In a List")
    public void save_AllAnimalsAreSaved() throws Exception {
        animal = setUp();
        EndangeredAnimal animal1 = new EndangeredAnimal("Monkey","Adult","Healthy",343);
        animal.saveAnimal();
        animal1.saveAnimal();
        assertTrue(EndangeredAnimal.all().contains(animal));
        assertTrue( EndangeredAnimal.all().contains(animal1));

    }

    @Test
    @DisplayName("Objects are equal")
    public void EndangeredAnimals_CheckObjectEquality_True() throws Exception {
        animal = setUp();
        animal.saveAnimal();
        assertTrue(EndangeredAnimal.all().get(0).equals(animal));
    }

    @Test
    @DisplayName("Animal Is Found By Id")
    public void find_FindsAnimalsAtGivenId_EndangeredAnimal() throws Exception {
        animal = setUp();
        animal.saveAnimal();
        EndangeredAnimal animal1 = new EndangeredAnimal("Red Panda","Young","Okay",3433);
        animal1.saveAnimal();
        assertEquals(animal1,EndangeredAnimal.find(animal1.getId()));
    }
    @Test
    @DisplayName("Delete all")
    public void deleteAll_DeletesAllAnimals_True() throws Exception{
        animal = setUp();
        animal.saveAnimal();
        EndangeredAnimal animal1 = new EndangeredAnimal("Red Panda","Young","Okay",3433);
        animal1.saveAnimal();
        EndangeredAnimal.deleteAll();
        assertEquals(0,EndangeredAnimal.all().size());
    }

    @Test
    @DisplayName("Delete By Id")
    public void deleteById_DeletesAnimal_True() throws Exception{
        animal = setUp();
        animal.saveAnimal();
        EndangeredAnimal animal1 = new EndangeredAnimal("Red Panda","Young","Okay",3433);
        animal1.saveAnimal();
        EndangeredAnimal.deletebyId(animal1.getId());
        assertFalse(EndangeredAnimal.all().contains(animal1));
    }

    @Test
    @DisplayName("Returns Time")
    public void timeSighted_ReturnsTime() throws Exception{
        animal = setUp();
        animal.saveAnimal();
        Location location = new Location("Whiterun",45.43,123.54);
        location.saveLocation();
        Ranger ranger = new Ranger("Deadwood","123-312-212134",58533);
        ranger.save();
        Sighting sighting = new Sighting(animal.getId(),ranger.getId(),location.getId());
        sighting.saveSighting();
        Timestamp rightNow = new Timestamp(new Date().getTime());
        String humanNow = DateFormat.getDateTimeInstance().format(rightNow);
        assertEquals(humanNow,animal.timeSighted());
    }

    @Test
    @DisplayName("Returns Location")
    public void placeSighted_ReturnsLocation() throws Exception{
        animal = setUp();
        animal.saveAnimal();
        Location location = new Location("Whiterun",45.43,123.54);
        location.saveLocation();
        Ranger ranger = new Ranger("Deadwood","123-312-212134",58533);
        ranger.save();
        Sighting sighting = new Sighting(animal.getId(),ranger.getId(),location.getId());
        sighting.saveSighting();
        assertEquals(location,animal.locationSighted());
    }
}
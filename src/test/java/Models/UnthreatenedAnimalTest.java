package Models;

import Exceptions.InvalidEntryException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;


import java.sql.Timestamp;
import java.text.DateFormat;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class UnthreatenedAnimalTest {

    UnthreatenedAnimal animal;

    public UnthreatenedAnimal setUp() throws InvalidEntryException {
        return new UnthreatenedAnimal("Dodo","Adult","Healthy",4);
    }

    @ExtendWith(DatabaseRule.class)
    public DatabaseRule databaseRule = new DatabaseRule();


    @Test
    @DisplayName("Instantiates Correctly")
    public void UnthreatenedAnimal_InstantiatesCorrectly_True() throws InvalidEntryException {
        animal = setUp();
        assertTrue(animal instanceof UnthreatenedAnimal);
    }

    @Test
    @DisplayName("Instantiates with properties")
    public void UnthreatenedAnimal_InstantiatesWithProperties_True() throws InvalidEntryException {
        animal = setUp();
        assertEquals("Dodo",animal.getName());
        assertEquals("Adult",animal.getAgeRange());
        assertEquals("Healthy",animal.getHealth());
        assertEquals(4,animal.getRangerId());
    }

    @Test
    @DisplayName("Instantiates with id")
    public void UnthreatenedAnimal_InstantiatesWithId_True() throws InvalidEntryException {
        animal = setUp();
        int initialId = animal.getId();
        animal.saveAnimal();
        assertNotEquals(initialId,animal.getId());
    }

    @Test
    @DisplayName("Save saves all Animals")
    public void save_SavesAllAnimals () throws InvalidEntryException {
        animal = setUp();
        animal.saveAnimal();
        UnthreatenedAnimal animal1 = new UnthreatenedAnimal("Horse","Young","Okay",3433);
        animal1.saveAnimal();
        assertEquals(2,UnthreatenedAnimal.all().size());
    }
    @Test
    @DisplayName("All Animals Are In a List")
    public void save_AllAnimalsAreSaved() throws Exception {
        animal = setUp();
        UnthreatenedAnimal animal1 = new UnthreatenedAnimal("Monkey","Adult","Healthy",343);
        animal.saveAnimal();
        animal1.saveAnimal();
        assertTrue(UnthreatenedAnimal.all().contains(animal));
        assertTrue( UnthreatenedAnimal.all().contains(animal1));

    }

    @Test
    @DisplayName("Objects are equal")
    public void UnthreatenedAnimals_CheckObjectEquality_True() throws InvalidEntryException {
        animal = setUp();
        animal.saveAnimal();
        assertTrue(UnthreatenedAnimal.all().get(0).equals(animal));
    }

    @Test
    @DisplayName("Animal Is Found By Id")
    public void find_FindsAnimalsAtGivenId_UnthreatenedAnimal() throws InvalidEntryException {
        animal = setUp();
        animal.saveAnimal();
        UnthreatenedAnimal animal1 = new UnthreatenedAnimal("Panda","Young","Okay",3433);
        animal1.saveAnimal();
        assertEquals(animal1,UnthreatenedAnimal.find(animal1.getId()));
    }


    @Test
    @DisplayName("Delete All Animals")
    public void delete_DeletesAllAnimals() throws Exception{
        animal = setUp();
        animal.saveAnimal();
        UnthreatenedAnimal animal1 = new UnthreatenedAnimal("Panda","Young","Okay",3433);
        animal1.saveAnimal();
        UnthreatenedAnimal.deleteAll();
        assertEquals(0,UnthreatenedAnimal.all().size());

    }

    @Test
    @DisplayName("Delete Animal At Given Id")
    public void deleteById_DeletesAnimal() throws Exception{
        animal = setUp();
        animal.saveAnimal();
        UnthreatenedAnimal animal1 = new UnthreatenedAnimal("Panda","Young","Okay",3433);
        animal1.saveAnimal();
        UnthreatenedAnimal.deletebyId(animal1.getId());
        assertFalse(UnthreatenedAnimal.all().contains(animal1));

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
package Models;

import Exceptions.InvalidEntryException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;


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


}
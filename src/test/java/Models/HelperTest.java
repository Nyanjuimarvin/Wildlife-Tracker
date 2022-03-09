package Models;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.junit.jupiter.api.Assertions.*;

class HelperTest {

    @ExtendWith(DatabaseRule.class)
    DatabaseRule databaseRule = new DatabaseRule();

    @Test
    @DisplayName("Saves Any")
    public void saveDifferent_SavesAll() throws Exception {
        Location location = new Location("Solitude",45.65,-56.76);
        Ranger ranger = new Ranger("Willie","234-432-123456",5333);
        EndangeredAnimal animal = new EndangeredAnimal("panda","Adult","Okay",ranger.getId());
        UnthreatenedAnimal animal1 = new UnthreatenedAnimal("Lion","Newborn","Healthy",ranger.getId());
        assertTrue(EndangeredAnimal.all().contains(animal));
        assertTrue(UnthreatenedAnimal.all().contains(animal1));
    }
}
package Models;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RangerTest {

    Ranger ranger;
    public Ranger setUpRanger(){
        return new Ranger("Shelton Johnson","(2332)-45234",54475);
    }

    @Test
    @DisplayName("Ranger objects are correctly Instantiated")
    public void Ranger_InstantiatesCorrectly_True(){
        ranger = setUpRanger();
        assertTrue(ranger instanceof Ranger);
    }

    @Test
    @DisplayName("Ranger instantiates with properties")
    public void Ranger_InstantiatesWithAllProperties(){
        ranger = setUpRanger();
        assertEquals("Shelton Johnson",ranger.getName());
        assertEquals(54475,ranger.getId());
        assertEquals("(2332)-45234",ranger.getContact());
    }

}
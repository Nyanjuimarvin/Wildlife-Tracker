package Models;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;


import static org.junit.jupiter.api.Assertions.*;

class RangerTest {

    @ExtendWith(DatabaseRule.class)
    DatabaseRule databaseRule = new DatabaseRule();

    Ranger ranger;
    public Ranger setUpRanger() throws Exception {
        return new Ranger("Shelton Johnson","254-722-345678",54475);
    }

    @Test
    @DisplayName("Ranger objects are correctly Instantiated")
    public void Ranger_InstantiatesCorrectly_True() throws Exception {
        ranger = setUpRanger();
        assertTrue(ranger instanceof Ranger);
    }

    @Test
    @DisplayName("Ranger instantiates with properties")
    public void Ranger_InstantiatesWithAllProperties() throws Exception {
        ranger = setUpRanger();
        assertEquals("Shelton Johnson",ranger.getName());
        assertEquals(54475,ranger.getBadgeId());
        assertEquals("254-722-345678",ranger.getContact());
    }

    @Test
    @DisplayName("Ranger instantiates with Id")
    public void Ranger_InstantiatedWithId() throws Exception {
        ranger = setUpRanger();
        int initialId = ranger.getId();
        ranger.save();
        assertNotEquals(initialId,ranger.getId());
    }

    @Test
    @DisplayName("Rangers are added to list")
    public void save_AddsRangerToList() throws Exception {
        ranger = setUpRanger();
        ranger.save();
        assertEquals(1,Ranger.all().size());
    }

    @Test
    @DisplayName("Rangers are found")
    public void find_FindsRangers() throws Exception {
        ranger = setUpRanger();
        Ranger ranger1 = new Ranger("Anon","254-765-091234",6543);
        ranger.save();
        ranger1.save();

        assertEquals(ranger1,Ranger.find(ranger1.getId()));

    }

    @Test
    @DisplayName("All Rangers Are In List")
    public void all_AllRangersAreInList() throws Exception {
        ranger = setUpRanger();
        Ranger ranger1 = new Ranger("Anon","254-765-091234",6543);
        ranger.save();
        ranger1.save();
        assertTrue(Ranger.all().contains(ranger));
        assertTrue(Ranger.all().contains(ranger1));

    }

}
package Models;

import java.sql.Timestamp;

public class Sighting {

    private int sightingId;
    private int rangerId;
    private int locationId;
    private int animalId;
    private Timestamp timeRecorded;

    public Sighting (int animalId,int rangerId,int locationId){
        this.rangerId = rangerId;
        this.locationId = locationId;
    }

    public int getRangerId() {
        return rangerId;
    }

    public int getLocationId() {
        return locationId;
    }

    public int getAnimalId() {
        return animalId;
    }
}

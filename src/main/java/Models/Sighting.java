package Models;

import Exceptions.InvalidEntryException;
import org.joda.time.DateTime;
import org.sql2o.Connection;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.util.Date;
import java.util.List;
import java.util.Objects;

public class Sighting {

    private int id;
    private int rangerId;
    private int locationId;
    private int animalId;
    private Timestamp timesighted;
    private String readableDate;

    public Sighting (int animalId,int rangerId,int locationId){
        this.animalId = animalId;
        this.rangerId = rangerId;
        this.locationId = locationId;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Sighting)) return false;
        Sighting sighting = (Sighting) o;
        return rangerId == sighting.rangerId
                && locationId == sighting.locationId
                && animalId == sighting.animalId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(rangerId, locationId, animalId);
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

    public String getReadableDate() {
        return readableDate;
    }

    public int getId() {
        return id;
    }


    public void setTimesighted(Timestamp timesighted) {
        this.timesighted = timesighted;
    }

    public Timestamp getTimesighted() {
        return timesighted;
    }

    public void saveSighting(){
        try(Connection conn = Db.sql2o.open()){
            String sql = "INSERT INTO sightings (rangerid,animalid,locationid,timesighted) VALUES (:rangerId,:animalId,:locationId,now())";
            this.id = (int) conn.createQuery(sql,true)
                    .addParameter("rangerId",this.rangerId)
                    .addParameter("animalId",this.animalId)
                    .addParameter("locationId",this.locationId)
                    .executeUpdate()
                    .getKey();
        }
    }

    public static List <Sighting> allSightings(){
        try(Connection conn = Db.sql2o.open()){
            return conn.createQuery("SELECT * FROM sightings;")
                    .executeAndFetch(Sighting.class);
        }
    }

    public static Sighting find(int id) {
        try(Connection conn = Db.sql2o.open()){
            return conn.createQuery("SELECT * FROM sightings WHERE id = :id;")
                    .addParameter("id",id)
                    .throwOnMappingFailure(false)
                    .executeAndFetchFirst(Sighting.class);
        }
    }

    public static void deleteAll(){
        try(Connection conn = Db.sql2o.open()){
            conn.createQuery("DELETE FROM sightings")
                    .executeUpdate();
        }
    }

    public static void deletebyId(int id){
        try(Connection conn = Db.sql2o.open()){
            conn.createQuery("DELETE FROM sightings WHERE id = :id")
                    .addParameter("id",id)
                    .executeUpdate();
        }
    }


    //Return Location Sighted
    public  Location placeSighted(){

        try(Connection conn = Db.sql2o.open()){
            return conn.createQuery("SELECT * FROM locations WHERE id = :id")
                    .addParameter("id",this.locationId)
                    .executeAndFetchFirst(Location.class);
        }
    }

    //Endangered Animal Sighted
    public EndangeredAnimal eAnimalSighted(){
        try(Connection conn = Db.sql2o.open()){
            return conn.createQuery("SELECT * FROM animals WHERE id = :id")
                    .addParameter("id",this.animalId)
                    .executeAndFetchFirst(EndangeredAnimal.class);
        }
    }

    //Unthreatened Animal Sighted
    public UnthreatenedAnimal uAnimalSighted(){
        try(Connection conn = Db.sql2o.open()){
            return conn.createQuery("SELECT * FROM animals WHERE id = :id")
                    .addParameter("id",this.animalId)
                    .executeAndFetchFirst(UnthreatenedAnimal.class);
        }
    }

    public Ranger recordRanger(){
        try(Connection conn = Db.sql2o.open()){
            return conn.createQuery("SELECT * FROM rangers WHERE id = :id")
                    .addParameter("id",this.rangerId)
                    .executeAndFetchFirst(Ranger.class);
        }
    }
}

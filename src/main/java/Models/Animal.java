package Models;


import Exceptions.InvalidEntryException;
import org.sql2o.Connection;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Animal {
    public String name;
    public String health;
    public String ageRange;
    public int rangerId;
    public Timestamp timeOfRecord;
    public String timeRecorded;
    public String placeOfRecord;
    public int locationId;
    public String location;

    private int id;
    public String type;

    public static final String MAX_HEALTH = "Healthy";
    public static final String MID_HEALTH = "Okay";
    public static final String MIN_HEALTH = "Ill";

    public static final String MAX_AGE ="Adult";
    public static final String MID_AGE = "Young";
    public static final String MIN_AGE = "Newborn";


    public Animal(String name, String ageRange, String health,int rangerId) throws InvalidEntryException {
        try{
        this.setName(name);
        this.setHealth(health);
        this.setAgeRange(ageRange);
        this.rangerId = rangerId;
        }catch(InvalidEntryException ex){
            System.out.println(ex.getMessage());
        }
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Animal)) return false;
        Animal animal = (Animal) o;
        return rangerId == animal.getRangerId()
                && Objects.equals(name, animal.getName())
                && Objects.equals(health, animal.getHealth())
                && Objects.equals(ageRange, animal.getAgeRange())
                && Objects.equals(type, animal.getType());
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, health, ageRange, rangerId, type);
    }


    public void setHealth(String health) throws InvalidEntryException {
        switch (health) {
            case MAX_HEALTH:
                this.health = MAX_HEALTH;
                break;
            case MID_HEALTH:
                this.health = MID_HEALTH;
                break;
            case MIN_HEALTH:
                this.health = MIN_HEALTH;
                break;
            default:
                throw new InvalidEntryException("Please Select The Given Options for the Animal's Health");
        }
    }

    public void setAgeRange(String ageRange) throws InvalidEntryException {
        switch (ageRange) {
            case MAX_AGE:
                this.ageRange = MAX_AGE;
                break;
            case MID_AGE:
                this.ageRange = MID_AGE;
                break;
            case MIN_AGE:
                this.ageRange = MIN_AGE;
                break;
            default:
                throw new InvalidEntryException("Please Select The Given Options For the Animal's Age");
        }
    }

    public void setName(String name) throws InvalidEntryException {
        if( !(name.isEmpty()) && name.matches("([a-zA-z]+|[a-zA-Z]+\\s[a-zA-Z]+)*" ) ){
            this.name = name;
        }else{
            throw new InvalidEntryException("Please Enter Animal Name Again");
        }
    }


    public void setTimeRecorded() {
        this.timeRecorded = timeSighted();
    }

    public void setPlaceOfRecord(String placeOfRecord) {
        this.placeOfRecord = placeOfRecord;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getName() {
        return name;
    }

    public String getAgeRange() {
        return ageRange;
    }

    public String getHealth() {
        return health;
    }

    public int getRangerId() {
        return rangerId;
    }

    public String getType() {
        return type;
    }


    public int getId() {
        return id;
    }

    public void saveAnimal(){
        try(Connection conn = Db.sql2o.open() ){
            String sql = "INSERT INTO animals (name, agerange, health,rangerId,type,location) VALUES (:name, :age , :health, :rangerId, :type, :location)";
            this.id = (int) conn.createQuery(sql,true)
                     .addParameter("name",this.name)
                     .addParameter("age",this.ageRange)
                     .addParameter("health",this.health)
                     .addParameter("rangerId",this.rangerId)
                     .addParameter("type",this.type)
                    .addParameter("location",this.location)
                     .executeUpdate()
                    .getKey();
        }
    }

    //Time of sighting
    public String timeSighted(){
        try(Connection conn = Db.sql2o.open() ) {
            String sql = "SELECT timesighted FROM sightings WHERE animalid = :id";
            this.timeOfRecord = conn.createQuery(sql)
                    .addParameter("id",this.id)
                    .executeAndFetchFirst(Timestamp.class);
            return DateFormat.getDateTimeInstance().format(timeOfRecord);
        }
    }

    //Ranger
    public Ranger recordRanger(){
        try(Connection conn = Db.sql2o.open()){
            return conn.createQuery("SELECT * FROM rangers WHERE id = :id")
                    .addParameter("id",this.rangerId)
                    .executeAndFetchFirst(Ranger.class);
        }
    }

    //Location
    public Location locationSighted(){
        try(Connection conn = Db.sql2o.open()){
            String sql = "SELECT locationid FROM sightings WHERE animalid = :id";
            this.locationId = conn.createQuery(sql)
                    .addParameter("id",this.id)
                    .executeAndFetchFirst(Integer.class);

            return conn.createQuery("SELECT * FROM locations WHERE id = :id")
                    .addParameter("id",this.locationId)
                    .executeAndFetchFirst(Location.class);
        }
    }


    public void extraDetails() {
        this.location = this.locationSighted().readableLocation();
        this.timeRecorded = this.timeSighted();

    }

}

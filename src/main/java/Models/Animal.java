package Models;


import Exceptions.InvalidEntryException;
import org.sql2o.Connection;

import java.util.Objects;

public class Animal {
    public String name;
    public String health;
    public String ageRange;
    public int rangerId;

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
        this.setRangerId(rangerId);
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
        if(health.equals(MAX_HEALTH)){
            this.health = MAX_HEALTH;
        }else if(health.equals(MID_HEALTH)){
            this.health = MID_HEALTH;
        }else if(health.equals(MIN_HEALTH)){
            this.health = MIN_HEALTH;
        }
        else{
            throw new InvalidEntryException("Please Select The Given Options for the Animal's Health");
        }
    }

    public void setAgeRange(String ageRange) throws InvalidEntryException {
        if(ageRange.equals(MAX_AGE)){
            this.ageRange = MAX_AGE;
        }else if(ageRange.equals(MID_AGE)){
            this.ageRange = MID_AGE;
        }else if(ageRange.equals(MIN_AGE)){
            this.ageRange = MIN_AGE;
        }
        else{
            throw new InvalidEntryException("Please Select The Given Options For the Animal's Age");
        }
    }

    public void setName(String name) throws InvalidEntryException {
        if(name.matches("([a-zA-z]+|[a-zA-Z]+\\s[a-zA-Z]+)*" )){
            this.name = name;
        }else{
            throw new InvalidEntryException("Please Enter Your Name Again");
        }
    }

    public void setRangerId(int rangerId) throws InvalidEntryException {
        if( rangerId > 0 && rangerId<= 100_000 ){
            this.rangerId = rangerId;
        }else{
            throw new InvalidEntryException("Please Enter Your ID Again..Kindly make sure it is correct");
        }
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
            String sql = "INSERT INTO animals (name, agerange, health,rangerId,type) VALUES (:name, :age , :health, :rangerId, :type)";
            this.id = (int) conn.createQuery(sql,true)
                     .addParameter("name",this.name)
                     .addParameter("age",this.ageRange)
                     .addParameter("health",this.health)
                     .addParameter("rangerId",this.rangerId)
                     .addParameter("type",this.type)
                     .executeUpdate()
                    .getKey();
        }
    }

}

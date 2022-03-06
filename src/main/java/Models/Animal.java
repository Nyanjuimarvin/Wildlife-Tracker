package Models;

import org.sql2o.Connection;

import java.util.Objects;

public class Animal {
    public String name;
    public String health;
    public String ageRange;
    public int rangerId;

    private int id;
    public String type;

    private static final String MAX_HEALTH = "Healthy";
    private static final String MID_HEALTH = "Okay";
    private static final String MIN_HEALTH = "Ill";

    private static final String MAX_AGE ="Adult";
    private static final String MID_AGE = "Young";
    private static final String MIN_AGE = "Newborn";

    public Animal(String name, String ageRange, String health,int rangerId) {
        this.ageRange = ageRange ;
        this.name = name;
        this.health = health;
        this.rangerId = rangerId;
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

    public void setName(String name) {
        this.name = name;
    }

    public void setHealth(String health) {
        this.health = health;
    }

    public void setAgeRange(String ageRange) {
        this.ageRange = ageRange;
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

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void saveAnimal(){
        try(Connection conn = Db.sql2o.open() ){
            String sql = "INSERT INTO animals (name, agerange, health,rangerId,type) VALUES (:name, :age , :health, :rangerId, :type)";
            int id = (int) conn.createQuery(sql,true)
                     .addParameter("name",this.name)
                     .addParameter("age",this.ageRange)
                     .addParameter("health",this.health)
                     .addParameter("rangerId",this.rangerId)
                     .addParameter("type",this.type)
                     .executeUpdate()
                    .getKey();
            setId(id);
        }
    }

}

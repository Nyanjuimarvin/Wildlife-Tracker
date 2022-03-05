package Models;

import org.sql2o.Connection;

public class Animal {
    public String name;
    public String health;
    public String ageRange;

    private int id;
    public String type;

    private static final String MAX_HEALTH = "Healthy";
    public static final String MID_HEALTH = "Okay";
    public static final String MIN_HEALTH = "Ill";

    public static final String MAX_AGE ="Adult";
    public static final String MID_AGE = "Young";
    public static final String MIN_AGE = "Newborn";

    public Animal(String name, String ageRange, String health) {
        this.ageRange = ageRange ;
        this.name = name;
        this.health = health;
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

    public void saveAnimal(){
        try(Connection conn = Db.sql2o.open() ){
            this.id = (int) conn.createQuery("INSERT INTO animals (name, age, health,type) VALUES (:name, :age, :health, :type)")
                     .addParameter("name",this.name)
                     .addParameter("age",this.ageRange)
                     .addParameter("health",this.health)
                    .addParameter("type",this.type)
                     .executeUpdate()
                     .getKey();

        }
    }

}

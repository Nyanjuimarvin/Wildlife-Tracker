package Models;

public abstract class Animal {
    public String ageRange;
    public String name;
    public String health;

    private int id;
    public String type;

    public Animal(String ageRange, String name, String health) {
        this.ageRange = ageRange;
        this.name = name;
        this.health = health;
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

    public abstract void saveAnimal();
}

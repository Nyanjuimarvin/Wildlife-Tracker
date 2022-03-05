package Models;

public class Ranger {
    private String name;
    private int id;
    private int sightings;

    public Ranger(String name, int id) {
        this.name = name;
        this.id = id;
    }

    public void setSightings(int sightings) {
        this.sightings = sightings;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public int getSightings() {
        return sightings;
    }
}

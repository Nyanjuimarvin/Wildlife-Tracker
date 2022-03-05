package Models;

import java.util.Objects;

public class Ranger {
    private String name;
    private String contact;
    private int sightings;
    private int badgeId;
    private int id;

    public Ranger(String name,String contact, int badgeId) {
        this.name = name;
        this.badgeId = badgeId;
        this.contact = contact;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Ranger)) return false;
        Ranger ranger = (Ranger) o;
        return id == ranger.id
                && Objects.equals(name, ranger.name)
                && Objects.equals(contact, ranger.contact);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, contact, id);
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

    public String getContact() {
        return contact;
    }

    public int getSightings() {
        return sightings;
    }
}

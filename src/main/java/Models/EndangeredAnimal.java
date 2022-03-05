package Models;

import java.util.List;

public class EndangeredAnimal extends Animal implements Query{

    public static final String RISK_CATEGORY = "Endangered";

    public EndangeredAnimal(String name, String ageRange, String health) {
        super(name, ageRange, health);
        type = RISK_CATEGORY;
    }

    @Override
    public void save() {

    }

    @Override
    public List<Object> all() {
        return null;
    }

    @Override
    public Object find(int id) {
        return null;
    }

    @Override
    public void delete() {

    }

    @Override
    public void deleteOne(int id) {

    }
}

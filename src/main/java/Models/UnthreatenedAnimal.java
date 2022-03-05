package Models;

import org.sql2o.Connection;

import java.util.List;

public class UnthreatenedAnimal extends Animal implements Query{

    public static final String RISK_CATEGORY = "Unthreatened";

    public UnthreatenedAnimal(String ageRange,String name,String health){
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

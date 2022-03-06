package Models;

import Exceptions.InvalidEntryException;
import dao.EndangeredDao;
import org.sql2o.Connection;

import java.util.List;



public class EndangeredAnimal extends Animal implements EndangeredDao{

    public static final String RISK_CATEGORY = "Endangered";

    public EndangeredAnimal(String name, String ageRange, String health,int rangerID) throws InvalidEntryException {
        super(name, ageRange, health,rangerID);
        type = RISK_CATEGORY;
    }


    public boolean validHealth(){
        return  health.equals(MAX_HEALTH)||
                health.equals(MID_HEALTH)||
                health.equals(MIN_HEALTH);
    }



    public static List <EndangeredAnimal> all(){
        try( Connection conn = Db.sql2o.open() ){
            return conn.createQuery("SELECT * FROM animals WHERE type = 'Endangered';")
                    .executeAndFetch(EndangeredAnimal.class);
        }
    }

    public static EndangeredAnimal find(int id) {
        try( Connection conn = Db.sql2o.open() ){
            String sql = "SELECT * FROM animals WHERE id = :id";
            return conn.createQuery(sql)
                    .addParameter("id",id)
                    .throwOnMappingFailure(false)
                    .executeAndFetchFirst(EndangeredAnimal.class);
        }
    }

    @Override
    public void delete() {

    }

    @Override
    public void deleteOne(int id) {

    }
}

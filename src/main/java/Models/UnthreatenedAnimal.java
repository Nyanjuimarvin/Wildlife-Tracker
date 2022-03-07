package Models;

import Exceptions.InvalidEntryException;
import org.sql2o.Connection;

import java.sql.Timestamp;
import java.util.List;
import java.util.Objects;

public class UnthreatenedAnimal extends Animal{

    public static final String RISK_CATEGORY = "Unthreatened";

    public UnthreatenedAnimal(String name, String ageRange, String health,int rangerID) throws InvalidEntryException {
        super(name, ageRange, health,rangerID);
        type = RISK_CATEGORY;
    }

    public boolean equals(Object o){
        if (this == o) return true;
        if (!(o instanceof UnthreatenedAnimal)) return false;
        UnthreatenedAnimal animal = (UnthreatenedAnimal) o;
        return rangerId == animal.rangerId
                && Objects.equals(name, animal.name)
                && Objects.equals(ageRange, animal.ageRange)
                && Objects.equals(health,animal.health);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    public static List <UnthreatenedAnimal> all(){
        try( Connection conn = Db.sql2o.open() ){
            return conn.createQuery("SELECT * FROM animals WHERE type = 'Unthreatened';")
                    .executeAndFetch(UnthreatenedAnimal.class);
        }
    }

    public static UnthreatenedAnimal find(int id) {
        try( Connection conn = Db.sql2o.open() ){
            String sql = "SELECT * FROM animals WHERE id = :id";
            return conn.createQuery(sql)
                    .addParameter("id",id)
                    .throwOnMappingFailure(false)
                    .executeAndFetchFirst(UnthreatenedAnimal.class);
        }
    }

    //Nt
    public static void deleteAll(){
        try( Connection conn = Db.sql2o.open() ){
            String sql = "DELETE FROM animals where type = 'Unthreatened'";
            conn.createQuery(sql).executeUpdate();
        }
    }

    //Nt
    public static void deletebyId(int id){
        try( Connection conn = Db.sql2o.open() ){
            String sql = "DELETE FROM animals where id = :id";
            conn.createQuery(sql).addParameter("id",id).executeUpdate();
        }
    }

}

package Models;

import org.junit.jupiter.api.extension.AfterEachCallback;
import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.sql2o.Connection;
import org.sql2o.Sql2o;


public class DatabaseRule implements AfterEachCallback, BeforeEachCallback {

    @Override
    public void beforeEach(ExtensionContext context) throws Exception {
       Db.sql2o = new Sql2o("jdbc:postgresql://localhost:5432/wildlife_tracker_test","marvin","nrvnqsr13");
    }

    @Override
    public void afterEach(ExtensionContext context) throws Exception {
        try(Connection conn = Db.sql2o.open()){
            String deleteAnimals = "DELETE FROM animals *;";
            conn.createQuery(deleteAnimals).executeUpdate();
        }
    }

}




package Models;

import org.sql2o.Connection;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Objects;

public class Location {

    private String name;
    private BigDecimal latitude;
    private BigDecimal longitude;
    private int id;

    public Location(String name, double latitude, double longitude) {
        this.name = name;
        this.latitude = new BigDecimal(latitude).setScale(2,RoundingMode.DOWN);
        this.longitude = new BigDecimal(longitude).setScale(2,RoundingMode.DOWN);
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Location)) return false;
        Location location = (Location) o;
        return Objects.equals(name, location.name)
                && Objects.equals(latitude, location.latitude)
                && Objects.equals(longitude, location.longitude);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, latitude, longitude);
    }

    public String getName() {
        return name;
    }

    public BigDecimal getLatitude() {
        return latitude;
    }

    public BigDecimal getLongitude() {
        return longitude;
    }

    public int getId() {
        return id;
    }

    public void saveLocation(){
        try(Connection conn = Db.sql2o.open() ){
            String sql = "INSERT INTO locations (name,latitude,longitude) VALUES (:name,:latitude,:longitude)";
            this.id = (int) conn.createQuery(sql,true)
                    .addParameter("name",this.name)
                    .addParameter("latitude",this.latitude)
                    .addParameter("longitude",this.longitude)
                    .executeUpdate()
                    .getKey();
        }
    }

    public static List <Location> all(){
        try(Connection conn = Db.sql2o.open() ){
            return  conn.createQuery("SELECT * FROM locations")
                    .executeAndFetch(Location.class);
        }
    }

    public static Location find(int id){
        try(Connection conn = Db.sql2o.open() ){
            return  conn.createQuery("SELECT * FROM locations WHERE id = :id")
                    .addParameter("id",id)
                    .executeAndFetchFirst(Location.class);
        }
    }
}

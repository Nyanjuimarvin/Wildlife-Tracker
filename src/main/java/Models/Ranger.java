package Models;

import Exceptions.InvalidRangerDetails;
import org.sql2o.Connection;

import java.util.List;
import java.util.Objects;

public class Ranger {
    private String name;
    private String contact;
    private int sightings;
    private int badgeId;
    private int id;

    public Ranger(String name,String contact, int badgeId) throws Exception {
        try {
            this.setName(name);
            this.setContact(contact);
            this.setBadgeId(badgeId);
        }catch(InvalidRangerDetails ex){
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Ranger)) return false;
        Ranger ranger = (Ranger) o;
        return sightings == ranger.sightings
                && badgeId == ranger.badgeId
                && Objects.equals(name, ranger.name)
                && Objects.equals(contact, ranger.contact);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, contact, sightings, badgeId);
    }

    public void setName(String name) throws InvalidRangerDetails {
        if(name.matches("([a-zA-z]+|[a-zA-Z]+\\s[a-zA-Z]+)*" ) ){
            this.name = name;
        }else{
            throw new InvalidRangerDetails("Please Enter Your Name Again");
        }
    }

    public void setContact(String contact) throws InvalidRangerDetails {
        if(contact.matches("[1-9]\\d{2}-[1-9]\\d{2}-\\d{6}")){
            this.contact = contact;
        }else{
            throw new InvalidRangerDetails("Please Enter Your Correct Contact Details");
        }
    }

    public void setBadgeId(int badgeId) throws InvalidRangerDetails {
        if(badgeId > 0 && badgeId <= 100_000){
            this.badgeId = badgeId;
        }
        else{
            throw new InvalidRangerDetails("Please Enter Your Correct Badge Id Number");
        }
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

    public int getBadgeId() {
        return badgeId;
    }

    public int getSightings() {
        return sightings;
    }

    public void save(){
        try(Connection conn = Db.sql2o.open()){
            String sql = "INSERT INTO rangers (name,contact,badgeid) VALUES (:name,:contact,:badgeId)";
            this.id = (int) conn.createQuery(sql,true)
                    .addParameter("name",this.name)
                    .addParameter("contact",this.contact)
                    .addParameter("badgeId",this.badgeId)
                    .executeUpdate()
                    .getKey();
        }
    }

    public static List <Ranger> all(){
        try(Connection conn = Db.sql2o.open()){
            return conn.createQuery("SELECT * FROM rangers")
                    .executeAndFetch(Ranger.class);
        }
    }

    public static Ranger find(int id){
        try(Connection conn = Db.sql2o.open()){
            return conn.createQuery("SELECT * FROM rangers WHERE id = :id")
                    .addParameter("id",id)
                    .executeAndFetchFirst(Ranger.class);
        }
    }
}

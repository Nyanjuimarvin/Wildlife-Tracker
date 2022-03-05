package Models;

import java.util.List;

public interface Query {

    void save();
    List <Object> all();
    Object find(int id);
    void delete();
    void deleteOne(int id);

}

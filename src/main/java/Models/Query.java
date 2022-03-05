package Models;

import jdk.jpackage.internal.Arguments;

import java.util.List;

public interface Query {

    void save();
    List <Object> all();
    Object find(int id);
    void delete();
    void deleteOne(int id);

}

package Models;

import Exceptions.InvalidEntryException;

public class Helper {

    //Helper Class
    public Helper(){

    }

    public void saveDifferent(String type,String name,int rangerId,int locationId,String age,String health) throws InvalidEntryException {
        if(type.equals("Endangered")){
            EndangeredAnimal animal = new EndangeredAnimal(name,age,health,rangerId);
            animal.saveAnimal();
            Sighting sighting = new Sighting(animal.getId(),rangerId,locationId);
            sighting.saveSighting();
        }else{
            UnthreatenedAnimal animal = new UnthreatenedAnimal(name,age,health,rangerId);
            animal.saveAnimal();
            Sighting sighting = new Sighting(animal.getId(),rangerId,locationId);
            sighting.saveSighting();
        }

    }
}

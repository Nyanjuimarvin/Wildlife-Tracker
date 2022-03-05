import Exceptions.InvalidStringEntryException;
import Models.Animal;

public class App {
    public static void main(String[] args){
        Animal animal = new Animal("Porkie","young","ill");
        System.out.println(animal.health);
    }
}

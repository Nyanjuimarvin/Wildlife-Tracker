import static spark.Spark.*;

import Models.*;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;


public class App {
    public static void main(String[] args) throws Exception {
        staticFileLocation("/public");

        get("/",(request, response) -> {
            Map <String, Object> model = new HashMap<>();
            return new ModelAndView(model,"index.hbs");
        },new HandlebarsTemplateEngine());

        //Get Ranger Form
        get("/rangers/new",(request, response) -> {
            Map <String,Object> model = new HashMap<>();
            return new ModelAndView(model,"ranger-form.hbs");
        },new HandlebarsTemplateEngine());

        //Get Sighting Form
        get("/sighting/new",(request, response) -> {
            Map <String,Object> model = new HashMap<>();
            return new ModelAndView(model,"sighting-form.hbs");
        },new HandlebarsTemplateEngine());

        //Post Ranger Form
        post("/rangers",(request, response) -> {
            Map <String,Object> model = new HashMap<>();
            String name = request.queryParams("name");
            int badgeId = Integer.parseInt(request.queryParams("badgeId"));
            String contact = request.queryParams("contact");
            Ranger.all().add(new Ranger(name,contact,badgeId));
            response.redirect("/");
            return null;
        },new HandlebarsTemplateEngine());

        //Post Sightings Form
        post("/sightings",(request, response) -> {
            Map <String,Object> model = new HashMap<>();


            String locationName = request.queryParams("locationname");
            double latitude = Double.parseDouble(request.queryParams("latitude"));
            double longitude = Double.parseDouble(request.queryParams("longitude"));

            Location newLocation = new Location(locationName,latitude,longitude);
            Location.all().add(newLocation);

            String name = request.params("name");
            String age = request.queryParams("age");
            String health = request.queryParams("health");
            //Ny
            int rangerId = Integer.parseInt(request.queryParams("rangerId"));
            String risk = request.queryParams("risk");
           if(risk.equals("Endangered")){
               EndangeredAnimal animalE = new EndangeredAnimal(name,age,health,rangerId);
               EndangeredAnimal.all().add(animalE);
               Sighting.allSightings().add(new Sighting(animalE.getId(),rangerId, newLocation.getId()));
           }else{
               UnthreatenedAnimal animalU = new UnthreatenedAnimal(name,age,health,rangerId);
               UnthreatenedAnimal.all().add(animalU);
               Sighting.allSightings().add(new Sighting(animalU.getId(),rangerId,newLocation.getId()));
           }

            return null;
        },new HandlebarsTemplateEngine());

        //Get All Sightings
        get("/sightings/all",(request, response) -> {

        },new HandlebarsTemplateEngine());

        //Get Recent Sightings
        get("/sightings/recent",(request, response) -> {

        },new HandlebarsTemplateEngine());

        //Show Specific Sighting

        //Delete All Sightings

        //Delete Specific Sighting


        //Show Specific Animal

        //Show Ranger Details
    }
}

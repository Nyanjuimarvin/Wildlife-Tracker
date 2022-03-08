import static spark.Spark.*;

import Models.*;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;


public class App {
    public static void main(String[] args) throws Exception{
        staticFileLocation("/public");

        get("/",(request, response) -> {
            Map <String, Object> model = new HashMap<>();
            model.put("ranger",Ranger.all());
            return new ModelAndView(model,"index.hbs");
        },new HandlebarsTemplateEngine());

        //Get Ranger Form
        get("/rangers/new",(request, response) -> {
            Map <String,Object> model = new HashMap<>();
            model.put("rangers",Ranger.all());
            return new ModelAndView(model,"ranger-form.hbs");
        },new HandlebarsTemplateEngine());

        //Get Normal Sighting Form
        get("/sightings/normal",(request, response) -> {
            Map <String,Object> model = new HashMap<>();
            model.put("rangers",Ranger.all());
            return new ModelAndView(model,"unthreatened-form.hbs");
        },new HandlebarsTemplateEngine());

        //Get Endangered Sighting Form
        get("/sightings/endangered",(request, response) -> {
            Map <String,Object> model = new HashMap<>();
            model.put("rangers",Ranger.all());
            return new ModelAndView(model,"endangered-form.hbs");
        },new HandlebarsTemplateEngine());

        //Post Ranger Form
        post("/rangers",(request, response) -> {
            Map <String,Object> model = new HashMap<>();
            String name = request.queryParams("name");
            int badgeId = Integer.parseInt(request.queryParams("badgeId"));
            String contact = request.queryParams("contact");
            Ranger ranger = new Ranger(name, contact, badgeId);
            ranger.save();
            response.redirect("/");
            return null;
        },new HandlebarsTemplateEngine());

        //Post Normal Sighting Form
        post("/unthreatened",(request, response) ->{
            Map <String,Object> model = new HashMap<>();

            String locationName = request.queryParams("locationname");
            double latitude = Double.parseDouble(request.queryParams("latitude"));
            double longitude = Double.parseDouble(request.queryParams("longitude"));

            Location newLocation = new Location(locationName,latitude,longitude);
            newLocation.saveLocation();

            String name = request.queryParams("Name");
            String age = request.queryParams("age");
            String health = request.queryParams("health");
            //Ny
            int rangerId = Integer.parseInt(request.queryParams("rangerId"));
            UnthreatenedAnimal animal = new UnthreatenedAnimal(name,age,health,rangerId);
            animal.saveAnimal();
            Sighting sighting = new Sighting(animal.getId(),rangerId,newLocation.getId());
            sighting.saveSighting();

            response.redirect("/");
            return null;
        },new HandlebarsTemplateEngine());

        //Post Endangered Sightings Form
        post("/endangered",(request, response) ->{
            Map <String,Object> model = new HashMap<>();

            String locationName = request.queryParams("locationname");
            double latitude = Double.parseDouble(request.queryParams("latitude"));
            double longitude = Double.parseDouble(request.queryParams("longitude"));

            Location newLocation = new Location(locationName,latitude,longitude);
            newLocation.saveLocation();

            String name = request.queryParams("Name");
            String age = request.queryParams("age");
            String health = request.queryParams("health");
            //Ny
            int rangerId = Integer.parseInt(request.queryParams("rangerId"));
           EndangeredAnimal animal = new EndangeredAnimal(name,age,health,rangerId);
           animal.saveAnimal();
           Sighting sighting = new Sighting(animal.getId(),rangerId,newLocation.getId());
           sighting.saveSighting();

           response.redirect("/");
           return null;
        },new HandlebarsTemplateEngine());

        //Get All Sightings
        get("/sightings/all",(request, response) -> {
            Map <String,Object> model = new HashMap<>();
            model.put("sight",Sighting.allSightings());
            model.put("rangers",Ranger.all());

            return new ModelAndView(model,"sightings.hbs");

        },new HandlebarsTemplateEngine());

//        //Get Recent Sightings
//        get("/sightings/recent",(request, response) -> {
//
//        },new HandlebarsTemplateEngine());

        //Show Specific Sighting

        //Delete All Sightings

        //Delete Specific Sighting


        //Show Specific Animal

        //Show Ranger Details
    }
}

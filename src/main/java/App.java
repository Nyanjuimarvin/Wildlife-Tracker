import static spark.Spark.*;

import Models.*;
import org.w3c.dom.ranges.Range;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class App {
    public static void main(String[] args) throws Exception{
        staticFileLocation("/public");


        get("/",(request, response) -> {
            Map <String, Object> model = new HashMap<>();
            model.put("rangers",Ranger.all());
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
           animal.setLocation(newLocation.readableLocation());
           animal.saveAnimal();

           Sighting sighting = new Sighting(animal.getId(),rangerId,newLocation.getId());
           sighting.saveSighting();
           animal.extraDetails();
           response.redirect("/");
           return null;
        },new HandlebarsTemplateEngine());

        //Get All Normal Sightings
        get("/unthreatenedsightings/all",(request, response) -> {
            Map <String,Object> model = new HashMap<>();
            model.put("nAnimals",UnthreatenedAnimal.all());
            return new ModelAndView(model,"normalsightings.hbs");
        },new HandlebarsTemplateEngine());

        //Get All Endangered Sightings
        get("/endangeredsightings/all",(request, response) -> {
            Map <String,Object> model = new HashMap<>();
            model.put("eAnimals",EndangeredAnimal.all());
            return new ModelAndView(model,"endangeredSighting.hbs");
        },new HandlebarsTemplateEngine());


        //Rangers
        get("/rangers/:id",(request, response) -> {
            Map <String,Object> model = new HashMap<>();
            int rangerId = Integer.parseInt(request.params("id"));
            model.put("ranger",Ranger.find(rangerId));
            model.put("rangers",Ranger.all());
            return new ModelAndView(model,"ranger.hbs");
        },new HandlebarsTemplateEngine());


        //Ranger Sightings
        get("/rangers/:id/sightings",(request, response) -> {
            Map <String,Object> model = new HashMap<>();
            int id = Integer.parseInt(request.params("id")) ;
            model.put("ranger",Ranger.find(id));
            model.put("sight",Ranger.rangerSightings(id));
            System.out.println(Ranger.rangerSightings(id).size());
            return new ModelAndView(model,"exec.hbs");
        },new HandlebarsTemplateEngine());

        get("/sightings/all",(request, response) -> {
            Map <String,Object> model = new HashMap<>();
            model.put("sight",Sighting.allSightings());
            model.put("rangers",Ranger.all());
            System.out.println(Sighting.allSightings().size());
            return new ModelAndView(model,"sight.hbs");
            },new HandlebarsTemplateEngine());


        //Location at id
        get("/locations/:id",(request, response) -> {
            Map <String,Object> model = new HashMap<>();
            int locationId = Integer.parseInt(request.params("id"));
            model.put("location",Location.find(locationId));
            model.put("rangers", Ranger.all());
            return new ModelAndView(model,"location.hbs");
        },new HandlebarsTemplateEngine());


        //Delete All Sightings
        get("sightings/delete",(request, response) -> {
            Map <String,Object> model = new HashMap<>();
            Sighting.deleteAll();
            response.redirect("/");
            return null;
        },new HandlebarsTemplateEngine());

        //Delete Specific Sighting
        get("sightings/delete/:id",(request, response) -> {
            Map <String,Object> model = new HashMap<>();
            int id = Integer.parseInt(request.params("id"));
            Sighting.deletebyId(id);
            response.redirect("/");
            return null;
        },new HandlebarsTemplateEngine());

        //Show Specific Animal

        //Show Ranger Details

    }
}

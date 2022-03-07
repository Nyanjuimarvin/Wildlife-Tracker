import static spark.Spark.*;

import Exceptions.InvalidRangerDetails;
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
            model.put("rangers",Ranger.all());
            return new ModelAndView(model,"ranger-form.hbs");
        },new HandlebarsTemplateEngine());

        //Get Sighting Form
        get("/sightings/new",(request, response) -> {
            Map <String,Object> model = new HashMap<>();
            model.put("rangers",Ranger.all());
            return new ModelAndView(model,"sighting-form.hbs");
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

        //Post Sightings Form
        post("/sightings",(request, response) -> {
            Map <String,Object> model = new HashMap<>();


            String locationName = request.queryParams("locationname");
            double latitude = Double.parseDouble(request.queryParams("latitude"));
            double longitude = Double.parseDouble(request.queryParams("longitude"));

            Location newLocation = new Location(locationName,latitude,longitude);
            newLocation.saveLocation();
            System.out.println(newLocation.getLatitude());
            System.out.println(newLocation.getLongitude());

            String name = request.params("name");
            String age = request.queryParams("age");
            String health = request.queryParams("health");
            System.out.println(health);
            //Ny
            int rangerId = Integer.parseInt(request.queryParams("rangerId"));
            System.out.println(rangerId);
            String risk = request.queryParams("risk");
            System.out.println(risk);
            System.out.println(risk.equals("Endangered"));
            System.out.println(risk.equals("Unthreatened"));
           Helper helper = new Helper();
           helper.saveDifferent(risk,name,rangerId, newLocation.getId(), age,health);

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

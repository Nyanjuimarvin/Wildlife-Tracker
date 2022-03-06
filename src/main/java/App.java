import static spark.Spark.*;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;


public class App {
    public static void main(String[] args) {
        staticFileLocation("/public");

        get("/",(request, response) -> {
            Map <String, Object> model = new HashMap<>();
            return new ModelAndView(model,"index.hbs");
        },new HandlebarsTemplateEngine());

        //Get Ranger Form
        get("/rangers",(request, response) -> {
            Map <String,Object> model = new HashMap<>();
            return new ModelAndView(model,"ranger-form.hbs");
        },new HandlebarsTemplateEngine());

        //Get Sighting Form
        get("/sighting",(request, response) -> {
            Map <String,Object> model = new HashMap<>();
            return new ModelAndView(model,"sighting-form.hbs");
        },new HandlebarsTemplateEngine());
    }
}

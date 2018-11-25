package easyeat.easyteam.controllers;

//import easyeat.easyteam.models.Good;

import easyeat.easyteam.utility.DBWorker;
//import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.sql.SQLException;

@Controller
public class RecipeController {

    @RequestMapping(method = RequestMethod.GET, produces="text/plain")
    @ResponseBody
    public String getResponseBodyOfRecipe(@RequestParam("good") String[] good, ModelMap model) {
        String recipe = null;

        try {
            if (good != null) {
                DBWorker db = new DBWorker();
                recipe = db.getRecipe(good);
                db.closeConnection();
            }
        } catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

        model.addAttribute("responseGood", (recipe != null ? recipe : "NO MATCH"));
        return recipe != null ? recipe : "GO TO SEARCHING...";
    }


//    @Controller
//    public class RecipeController {
//
//        @RequestMapping(method = RequestMethod.GET) //, produces = MediaType.APPLICATION_JSON_VALUE)
//        public String getListOfGoods(@RequestParam("good") String[] good, ModelMap model) {
//            String recipe = null;
//
//            try {
//                if (good != null) {
//                    DBWorker db = new DBWorker();
//                    recipe = db.getRecipe(good);
//                    db.closeConnection();
//                }
//            } catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
//                e.printStackTrace();
//            }
//
//            model.addAttribute("responseGood", (recipe != null ? recipe : "NO MATCH"));
//            return "viewrecipes";
//            //return recipe != null ? ("redirect:" + recipe) : "viewrecipes";
//
//        }

    }

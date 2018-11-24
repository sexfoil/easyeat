package easyeat.easyteam.controllers;

import easyeat.easyteam.models.Good;
import easyeat.easyteam.utility.DBWorker;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.SQLException;

@Controller
public class RecipeController {

    @RequestMapping(method = RequestMethod.GET)
    public String getListOfGoods(@RequestParam ("good") String[] good, ModelMap model) {
        String list = "";
        String recipe = "";
        for (String item : good) {
            list += "(" + item + ")";
        }
        boolean isLink = false;
        try {
            DBWorker db = new DBWorker();
            recipe = db.getRecipe(good);
            isLink = true;
//            Good g = (good[0] == null) ? (new Good("EMPTY", 0)) : (db.getOneGood(good[0]));
//            if (g == null) {
//                System.out.println("\nWHY NULL???\n");
//                list += " NULL";
//            } else {
//                list += " : " + g.getName() + " " + g.getCalories();
//            }
            db.closeConnection();

        } catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

        model.addAttribute("responseGood", list);
        //return "viewrecipes";
        return isLink ? ("redirect:" + recipe) : "viewrecipes";

    }

//    @RequestMapping(value = "/redirectExample", method = RequestMethod.GET)
//    public String redirectExample(HttpServletRequest request) {
//        //request.getScheme() - if you don't know where was the request sent: http, https, ftp..
//        return "redirect:" + request.getScheme() +"://javastudy.ru";
//    }
}

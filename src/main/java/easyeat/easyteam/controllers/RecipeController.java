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
        for (String item : good) {
            list += "(" + item + ")";
        }
        try {
            DBWorker db = new DBWorker();
            Good g = good[0] == null? new Good("EMPTY", 0) : db.getOneGood(good[0]);
            list += " : " + g.getName() + " " + g.getCalories();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        model.addAttribute("responseGood", list);
        return "viewrecipes";
    }
}

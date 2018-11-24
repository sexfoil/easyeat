package easyeat.easyteam.utility;

import easyeat.easyteam.models.Good;

import java.sql.*;
import java.util.ArrayList;

public class DBWorker {
    Connection connection = null;
    Statement statement = null;

    public DBWorker() throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
        Class.forName("com.mysql.jdbc.Driver").newInstance();
        System.out.println("-----------------");

        connection = DriverManager.getConnection(
                "jdbc:mysql://mysql:3306/eedatabase?serverTimezone=UTC",
                "root", "easyeat");

        statement = connection.createStatement();

        System.out.println("Successful  connection with DB ...");
        System.out.println("-----------------");
    }



    public void updateDatabase(String query) {
        try {
            statement.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /*public void addUserToDatabase(String query) {
        try {
            statement.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }*/

    public boolean isUserExist(String login) {

        boolean isExist = false;
        String query = "SELECT * FROM  users WHERE name='" + login + "'";

        try {
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                isExist = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return isExist;
    }


//    public ArrayList<Good> getGoods(String what, String kind) {
//        String query;
//        ArrayList<Good> products = new ArrayList<Good>();
//        query = "SELECT " + what + " FROM  planes" + kind;
//
//        try {
//            ResultSet resultSet = statement.executeQuery(query);
//
//            while (resultSet.next()) {
//                Product p = new Product(
//                        resultSet.getInt("plane_id"),
//                        resultSet.getString("name"),
//                        resultSet.getString("mark"),
//                        resultSet.getString("model"),
//                        resultSet.getString("role"),
//                        resultSet.getString("country"),
//                        resultSet.getString("description"),
//                        resultSet.getInt("crew"),
//                        resultSet.getInt("max_speed"),
//                        resultSet.getInt("service_ceiling"),
//                        resultSet.getString("picture"),
//                        resultSet.getInt("price")
//                );
//                products.add(p);
//            }
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return products;
//    }


    public String getRecipe(String[] good_set) {
        String query = "SELECT * FROM  recipes;";
        String recipe = "https://1000.menu/#catalog";
        try {
            ResultSet resultSet = statement.executeQuery(query);

            result:
            while (resultSet.next()) {
                String set = resultSet.getString("good_set");
                for (String item : good_set) {
                    if (!set.contains(item)) {
                        continue result;
                    }
                }
                recipe = resultSet.getString("link");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return recipe;

    }

    public Good getOneGood(String name) {
        String query;
        Good good = null;
        query = "SELECT * FROM  goods WHERE name='" + name + "';";

        try {
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                good = new Good(
                        resultSet.getString("name"),
                        resultSet.getInt("calories")
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return good;
    }

    public void closeConnection(){
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

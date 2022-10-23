package com.creativehazio;

import com.creativehazio.database.DB;
import com.creativehazio.database.DatabaseConnector;
import com.creativehazio.database.DatabaseDAO;
import com.creativehazio.database.DatabaseDB;
import com.creativehazio.todo.Slices;
import com.creativehazio.user.User;

import java.io.FileWriter;
import java.sql.*;
import java.time.LocalDate;
import java.time.LocalTime;

public class Main {
    public static void main(String[] args) {
        Slices td = new Slices("The working power of doing", Date.valueOf(LocalDate.now()),
                Time.valueOf(LocalTime.now()),"Stop dreaming, Start doing");
        Slices td2 = new Slices("Bhadie Kelly", Date.valueOf(LocalDate.now()), Time.valueOf(LocalTime.now()),
                "");
        Slices td3 = new Slices("Junior Developer Journey",Date.valueOf(LocalDate.now()), Time.valueOf(LocalTime.now()),
                "Day by day i'm getting closer to start creating value and start making money");


        User bhadie = new User("Bhadie","Kelly","Bhadiekelly","WOW");

//        SlicesDatabase db = new SlicesDatabase();
//        UserDatabase udb = new UserDatabase();

//        try {
//            DatabaseConnector db = DatabaseConnector.getInstance();
//            db.setUrl("jdbc:mysql://localhost:3306/Todo");
//            db.setRoot("root");
//            db.setPassword("javaprogrammer");
//
//            Connection conn = DatabaseConnector.getConn();
//            PreparedStatement pst = conn.prepareStatement("SELECT * FROM CREATIVEHAZIOSLICES");
//            ResultSet rs = pst.executeQuery();
//            System.out.println("id\t\ttitle\t\tdateCreated\t\ttimeCreated\t\tbody");
//
//            while (rs.next()){
//                int id = rs.getInt("id");
//                String title = rs.getString("title");
//                Date dateCreated = rs.getDate("dateCreated");
//                Time timeCreated = rs.getTime("timeCreated");
//                String body = rs.getString("body");
//                System.out.println(id + "\t\t" +title+"\t\t"+dateCreated +"\t\t" + timeCreated +"\t\t" +body);
//            }
//        }catch (Exception e){
//            System.out.println("Unable to get slices\n"+ e.getMessage());
//        }


//        DatabaseDAO databaseDAO = new DatabaseDB();
//        DatabaseConnector databaseConnector;
//
//        databaseConnector = DatabaseConnector.getInstance();
//        databaseConnector.setUrl("jdbc:mysql://localhost:3306/Todo");
//        databaseConnector.setRoot("root");
//        databaseConnector.setPassword("javaprogrammer");
//        Connection conn = DatabaseConnector.getConn();
//
//        databaseDAO.getByID(7297);

        try{
            FileWriter wr = new FileWriter("5506.dbi");
            wr.write("It worked");
            wr.close();
        }catch (Exception e){}
        // TODO: 1. Build frontEnd using javaFx
        //  2. turn it into a jar app for me to use
        //TODO: 1.  One database to manage both UserTable and SlicesTable
        // 2. Each user have their own SliceTable
        // 3. Find a way to save all my previous todos in a listview to be accessed later
        //1. Add a loading label when a user sign up or login
        // 2. Add tests for fields not to be null

    }

}

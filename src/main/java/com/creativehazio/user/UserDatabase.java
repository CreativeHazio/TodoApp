package com.creativehazio.user;

import com.creativehazio.database.DatabaseConnector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDatabase implements UserDAOInterface {

    @Override
    public User getUser(String username, String password) {
        User user = null;
        try {
            Connection conn = DatabaseConnector.getConn();
            PreparedStatement pst = conn.prepareStatement("SELECT * FROM USERS");
            ResultSet rs = pst.executeQuery();

            while (rs.next()){
                if (username.equals(rs.getString("userName")) && password.equals(rs.getString("password"))){
                    int id = rs.getInt("id");
                    String firstName = rs.getString("firstName");
                    String lastName = rs.getString("lastName");
                    String userName = rs.getString("userName");
                    String userPassword = rs.getString("password");
                    System.out.println(id+" "+firstName+" "+lastName+" "+userName+" "+password);
                    user = new User(id,firstName,lastName,userName,userPassword);
                    break;
                }
            }
        }catch (SQLException e){}
        return user;
    }

    @Override
    public void getAllUsers() {

    }

    @Override
    public boolean createUsersTable() {
        try {
            Connection conn = DatabaseConnector.getConn();
            PreparedStatement pst = conn.prepareStatement("CREATE TABLE users" +
                      "(ID INT, FIRSTNAME VARCHAR(50), LASTNAME VARCHAR(50), USERNAME VARCHAR(50), PASSWORD VARCHAR(50))");
            pst.execute();
            System.out.println("Users table has been added");
            return true;
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return false;
    }

    @Override
    public boolean save(User user) {
        try {
            Connection conn = DatabaseConnector.getConn();

            PreparedStatement pst = conn.prepareStatement("INSERT INTO USERS VALUES(?,?,?,?,?)");
            pst.setInt(1,(int)user.getUserId());
            pst.setString(2, user.getFirstName());
            pst.setString(3, user.getLastName());
            pst.setString(4, user.getUserName());
            pst.setString(5, user.getUserPassword());
            pst.execute();
            System.out.println("User has been added");

            PreparedStatement pstCreateUserTable = conn.prepareStatement("CREATE TABLE "+user.getUserName()+"SLICES" +
                    "(id INT, title VARCHAR(50), dateCreated DATE, timeCreated TIME, body VARCHAR(250), PRIMARY KEY(id))");
            pstCreateUserTable.execute();
            System.out.println("User "+user.getUserName()+" Table has been created");

            return true;
        }catch (SQLException e){
            System.out.println("Unable to Create table"+"\n"+e.getMessage());
        }
        return false;
    }

    @Override
    public void update(User user) {

    }

    @Override
    public boolean deleteByID(long id) {
        return false;
    }
}

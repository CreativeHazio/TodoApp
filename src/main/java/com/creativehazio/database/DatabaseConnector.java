package com.creativehazio.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnector {
    private static String url,root,password;
    private static Connection conn = null;
    private static DatabaseConnector databaseConnector2 = null;

    private DatabaseConnector(){
        getUrl();
        getRoot();
        getPassword();
    }

    private static String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    private static String getRoot() {
        return root;
    }

    public void setRoot(String root) {
        this.root = root;
    }

    private static String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public static DatabaseConnector getInstance(){
        if (databaseConnector2==null)
            databaseConnector2 = new DatabaseConnector();
        return databaseConnector2;
    }

    public static Connection getConn(){
        if (conn == null)
            setConn();
        return conn;
    }

    private static void setConn(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(getUrl(), getRoot(), getPassword());
            System.out.println("Successfully connected to database.");
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            System.out.println("Error while connecting to database\n" + e.getMessage());
        }
    }

}

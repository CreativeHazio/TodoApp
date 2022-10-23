package com.creativehazio.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConn {
    private static Connection conn;
    private static String url, root, pwd;

    public DatabaseConn() {
        super();
        init();
    }

    public static Connection getConn() {
        if (conn == null) {
            setConn();
        }
        return conn;
    }

    private static void setConn() {
        DatabaseConn.init();

        try {

            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(url, root, pwd);
            System.out.println("Successfully connected to database.");
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            System.out.println("Error while connecting to database\n" + e.getMessage());
        }
    }


    private static void init() {
        url = "jdbc:mysql://localhost:3306/Todo";
        root = "root";
        pwd = "javaprogrammer";
    }

}

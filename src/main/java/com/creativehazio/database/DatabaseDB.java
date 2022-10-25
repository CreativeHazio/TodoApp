package com.creativehazio.database;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DatabaseDB implements DatabaseDAO{
    @Override
    public boolean createTable() {
        try{
            Connection conn = DatabaseConnector.getConn();
            PreparedStatement pst = conn.prepareStatement("CREATE TABLE DATABASEINFO(id INT, url VARCHAR(50), " +
                    "root VARCHAR(50), password VARCHAR(50), PRIMARY KEY(id))");
            pst.execute();
            return true;
        }catch (SQLException e){
            System.out.println("Unable to create table" + e.getMessage());
        }
        return false;
    }

    @Override
    public boolean save(DB databaseModel) {
        try {
            Connection conn = DatabaseConnector.getConn();
            PreparedStatement pst = conn.prepareStatement("INSERT INTO DATABASEINFO VALUES(?,?,?,?)");

            pst.setInt(1, databaseModel.getId());
            pst.setString(2,databaseModel.getUrl());
            pst.setString(3,databaseModel.getRoot());
            pst.setString(4, databaseModel.getPassword());
            pst.execute();
            System.out.println("Saved and your id is "+ databaseModel.getId());
            return true;
        }catch (SQLException e){
            System.out.println("Unable to insert values "+ e.getMessage());
        }
        return false;
    }

    @Override
    public DB getByID(int id) {
        DB db = null;
        String dbInfo = null;

        try {
            FileReader reader = new FileReader(id+".dbi");
            BufferedReader bufferedReader = new BufferedReader(reader);
            String urlInfo = null;
            String rootInfo = null;
            String passwordInfo = null;

            while ((dbInfo = bufferedReader.readLine()) != null){
                String [] dbi = dbInfo.split("\s");
                urlInfo = dbi[0];
                rootInfo = dbi[1];
                passwordInfo = dbi[2];
            }
            DatabaseConnector databaseConnector = DatabaseConnector.getInstance();
            databaseConnector.setUrl(urlInfo);
            databaseConnector.setRoot(rootInfo);
            databaseConnector.setPassword(passwordInfo);

            Connection conn = DatabaseConnector.getConn();
            PreparedStatement st = conn.prepareStatement("SELECT * FROM DATABASEINFO WHERE ID = ?");
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();

            if (rs.next()) {
                int ID = rs.getInt("id");
                String url = rs.getString("url");
                String root = rs.getString("root");
                String password = rs.getString("password");
                db = new DB(url,root,password);
                return db;
            }
        }catch(IOException | SQLException e){}
            return db;
        }
    }


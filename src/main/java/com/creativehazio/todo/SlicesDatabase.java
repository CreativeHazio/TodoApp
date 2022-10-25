package com.creativehazio.todo;

import com.creativehazio.database.DatabaseConnector;
import com.creativehazio.user.User;

import java.sql.*;

public class SlicesDatabase implements SlicesDAOInterface<Slices> {

    @Override
    public boolean save(Slices slices, User user) {

        try {
            Connection connection = DatabaseConnector.getConn();
            PreparedStatement pst = connection.prepareStatement("INSERT INTO "+
                    user.getUserName()+"SLICES VALUES(?,?,?,?,?)");
            pst.setInt(1, (int) slices.getId());
            pst.setString(2, slices.getTitle());
            pst.setDate(3, slices.getDate());
            pst.setTime(4, slices.getTime());
            pst.setString(5, slices.getBody());
            pst.execute();
            System.out.println("Slice has been added");
            return true;
        }catch (Exception e){
            System.out.println("Error adding slice\n"+e.getMessage());
            return false;
        }

    }

//    @Override
//    public Slices getSlice(long id, User user) {
//        Slices slices = null;
//
//        try {
//            Connection connection = DatabaseConnector.getConn();
//            PreparedStatement st = connection.prepareStatement("SELECT * FROM "+
//                    user.getUserName()+"SLICES WHERE ID = ?");
//            st.setInt(1, (int) id);
//            ResultSet rs = st.executeQuery();
//
//            if (rs.next()){
//                int sliceID = rs.getInt("id");
//                String title = rs.getString("title");
//                Date dateCreated = rs.getDate("dateCreated");
//                Time timeCreated = rs.getTime("timeCreated");
//                String body = rs.getString("body");
//                slices = new Slices(sliceID,title,dateCreated,timeCreated,body);
//            }
//        }catch (Exception e){}
//        return slices;
//    }
//
//    @Override
//    public void getAllSlice(User user) {
//        try {
//            Connection connection = DatabaseConnector.getConn();
//            PreparedStatement pst = connection.prepareStatement("SELECT * FROM "+user.getUserName()+"SLICES");
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
//

//    }

//    @Override
//    public void update(Slices slices,User user, String title, String body) {
//        try {
//            Connection connection = DatabaseConnector.getConn();
//            PreparedStatement pst = connection.prepareStatement("UPDATE "+
//                                                                            user.getUserName()+"SLICES SET TITLE = ?,BODY = ? WHERE ID =?");
//            pst.setString(1,title);
//            pst.setString(2,body);
//            pst.setInt(3,(int) slices.getId());
//            pst.execute();
//            System.out.println("Slice successfully updated");
//        }catch (Exception e){
//            System.out.println("Unable to update\n" +e.getMessage());
//        }
//    }

//    @Override
//    public boolean deleteByID(long id,User user) {
//        try {
//            Connection connection = DatabaseConnector.getConn();
//            PreparedStatement pst = connection.prepareStatement("DELETE FROM "+
//                                                                                                    user.getUserName()+"SLICES WHERE ID = ?");
//            pst.setInt(1,(int)id);
//            pst.execute();
//            System.out.println("Slice has been deleted");
//            return true;
//        }catch (Exception e){
//            System.out.println("Unable to delete slice\n"+e.getMessage());
//            return false;
//        }
//    }
}

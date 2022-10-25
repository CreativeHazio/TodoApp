package com.creativehazio.user;

public interface UserDAOInterface <U extends User>{
    U getUser(String username, String password);
//    void getAllUsers();
    boolean createUsersTable();
    boolean save(U user);
//    void update(U user);
    boolean deleteByID(long id);
}

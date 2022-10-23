package com.creativehazio.database;

public interface DatabaseDAO {
    boolean createTable();
    boolean save(DB databaseModel);
    DB getByID(int id);
}

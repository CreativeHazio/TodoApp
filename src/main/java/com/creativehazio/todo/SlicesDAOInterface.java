package com.creativehazio.todo;

import com.creativehazio.user.User;

public interface SlicesDAOInterface<S extends Slices> {
//    S getSlice(long id, User user);
//    void getAllSlice(User user);
    boolean save(S todo, User user);
//    void update(S todo,User user, String title, String body);
//    boolean deleteByID(long id,User user);
}

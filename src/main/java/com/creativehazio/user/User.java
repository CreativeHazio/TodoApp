package com.creativehazio.user;

public class User{

    private long userId;
    private static long userIdGenerator;
    private String firstName;
    private String lastName;
    private String userName;
    private String userPassword;

    public User(String firstName, String lastName, String userName, String userPassword) {
        userIdGenerator++;
        this.userId = userIdGenerator;
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userName;
        this.userPassword = userPassword;
    }

    public User(long userId, String firstName, String lastName, String userName, String userPassword) {
        this.userId = userId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userName;
        this.userPassword = userPassword;
    }

    public long getUserId() {
        return userId;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getUserName() {
        return userName;
    }

    public String getUserPassword() {
        return userPassword;
    }
}

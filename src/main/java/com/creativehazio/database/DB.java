package com.creativehazio.database;

public class DB {
    private String url, root, password;
    private int id;

    public DB(String url, String root, String password) {
        this.id = (int)( Math.random()*12345);
        this.url = url;
        this.root = root;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getRoot() {
        return root;
    }

    public void setRoot(String root) {
        this.root = root;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "DB{" +
                "url='" + url + '\'' +
                ", root='" + root + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}

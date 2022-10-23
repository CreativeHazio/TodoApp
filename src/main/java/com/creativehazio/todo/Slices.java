package com.creativehazio.todo;

import java.sql.Date;
import java.sql.Time;

public class Slices {
    private static long idGenerator;
    private long id;
    private String title;
    private Date dateCreated;
    private Time timeCreated;
    private String body;

    public Slices(String title, Date dateCreated, Time timeCreated, String body) {
        idGenerator++;
        this.id = idGenerator;
        this.title = title;
        this.dateCreated = dateCreated;
        this.timeCreated = timeCreated;
        this.body = body;
    }

    public Slices(long id, String title, Date dateCreated, Time timeCreated, String body) {
        this.id = id;
        this.title = title;
        this.dateCreated = dateCreated;
        this.timeCreated = timeCreated;
        this.body = body;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getBody() {
        return body;
    }

    protected Date getDate() {
        return dateCreated;
    }

    protected Time getTime() {
        return timeCreated;
    }

    public String getDateCreated(){
        return getDate()+" "+getTime();
    }

    @Override
    public String toString() {
        return "Slices{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", dateCreated=" + dateCreated +
                ", timeCreated=" + timeCreated +
                ", body='" + body + '\'' +
                '}';
    }
}

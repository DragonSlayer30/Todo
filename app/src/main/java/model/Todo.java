package model;

import java.util.Date;

public class Todo {
    private String title;
    private String description;
    private boolean done;
    private Date due;
    private int id;

    public Todo(int  identity, String task_title, String desc, boolean status, Date due_date) {
        id = identity;
        title = task_title;
        description = desc;
        done = status;
        due = due_date;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

    public Date getDue() {
        return due;
    }

    public void setDue(Date due) {
        this.due = due;
    }

    public int getId() { return id; }

    public void setId(int id) { this.id = id; }

}

package com.example.todolist.object.entity;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.FieldType;
import org.springframework.data.mongodb.core.mapping.MongoId;

@Document("todo")
public class ToDo {
    @MongoId(FieldType.OBJECT_ID)
    private String id;
    private String text;
    private boolean done;

    public ToDo(String id, String text, boolean done) {
        this.id = id;
        this.text = text;
        this.done = done;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }
}

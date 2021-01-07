package com.example.stufa;

public class Query {

    String type;
    String Message;

    public Query(String type, String message) {
        this.type = type;
        Message = message;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMessage() {
        return Message;
    }

    public void setMessage(String message) {
        Message = message;
    }
}

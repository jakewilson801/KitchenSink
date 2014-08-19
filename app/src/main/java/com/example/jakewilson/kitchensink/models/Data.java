package com.example.jakewilson.kitchensink.models;

/**
 * Created by jakewilson on 8/19/14.
 */
public class Data {

    private String name;
    private String id;
    private String description;

    public Data(){

    }

    public Data(String name, String id, String description){
        this.name = name;
        this.id = id;
        this.description = description;
    }
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

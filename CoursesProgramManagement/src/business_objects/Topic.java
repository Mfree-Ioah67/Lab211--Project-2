/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business_objects;

/**
 *
 * @author Minhlahunday
 */
public class Topic {

    private String id;
    private String name;
    private String type;
    private String title;
    private int duration;

    public Topic(String id, String name, String type, String title, int duration) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.title = title;
        this.duration = duration;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    // Getters and Setters
    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public String getTitle() {
        return title;
    }

    public int getDuration() {
        return duration;
    }

    // Override toString for easier display
    @Override
    public String toString() {
        return String.format("ID: %s, Name: %s, Type: %s, Title: %s, Duration: %d",
                id, name, type, title, duration);
    }

}

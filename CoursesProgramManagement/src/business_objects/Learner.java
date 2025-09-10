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
import java.time.LocalDate;

public class Learner {

    private String id;
    private String name;
    private LocalDate birthDate;
    private double score;
    private Course course;

    public Learner(String id, String name, LocalDate birthDate, Course course) {
        this.id = id;
        this.name = name;
        this.birthDate = birthDate;
        this.course = course;
        this.score = 0; // Default score
    }

    // Getters and Setters
    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public boolean isPassing() {
        return score >= 60;
    } // Assuming passing score is 60

    @Override
    public String toString() {
        return String.format("ID: %s, Name: %s, Birth Date: %s, Score: %.2f, Course: %s",
                id, name, birthDate, score, course.getName());
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

}

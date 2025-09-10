
package business_objects;

/**
 *
 * @author Minhlahunday
 */
import java.time.LocalDate;

public class Course {

    private String id;
    private String name;
    private String type;
    private String title;
    private LocalDate beginDate;
    private LocalDate endDate;
    private double tuitionFee;
    private Topic topic;

    public Course(String id, String name, String type, String title, LocalDate beginDate,
            LocalDate endDate, double tuitionFee, Topic topic) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.title = title;
        this.beginDate = beginDate;
        this.endDate = endDate;
        this.tuitionFee = tuitionFee;
        this.topic = topic;
    }

    public LocalDate getBeginDate() {
        return beginDate;
    }

    // Getters and Setters
    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Topic getTopic() {
        return topic;
    }

    @Override
    public String toString() {
        return String.format("ID: %s, Name: %s, Type: %s, Title: %s, Start: %s, End: %s, Fee: %.2f",
                id, name, type, title, beginDate, endDate, tuitionFee);
    }
}


package application;

/**
 *
 * @author Minhlahunday
 */
import business_objects.Course;
import business_objects.Learner;
import business_objects.Topic;
import java.util.*;
import java.io.*;
import java.time.LocalDate;

public class CourseManagementSystem {

    private List<Topic> topics;
    private List<Course> courses;
    private List<Learner> learners;
    private Scanner scanner;

    public CourseManagementSystem() {
        topics = new ArrayList<>();
        courses = new ArrayList<>();
        learners = new ArrayList<>();
        scanner = new Scanner(System.in);
        loadDataFromFile(); // Load data at startup
    }

    public void showMainMenu() {
        while (true) {
            System.out.println("----- Course Management System -----");
            System.out.println("1. Manage Topics");
            System.out.println("2. Manage Courses");
            System.out.println("3. Manage Learners");
            System.out.println("4. Search Information");
            System.out.println("5. Save and Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    manageTopics();
                    break;
                case 2:
                    manageCourses();
                    break;
                case 3:
                    manageLearners();
                    break;
                case 4:
                    searchInformation();
                    break;
                case 5:
                    saveDataToFile();
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    public void manageTopics() {
        while (true) {
            System.out.println("----- Manage Topics -----");
            System.out.println("1. Add Topic");
            System.out.println("2. Update Topic");
            System.out.println("3. Delete Topic");
            System.out.println("4. Display All Topics");
            System.out.println("5. Back to Main Menu");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    addTopic();
                    break;
                case 2:
                    updateTopic();
                    break;
                case 3:
                    deleteTopic();
                    break;
                case 4:
                    displayAllTopics();
                    break;
                case 5:
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

// Add Topic
    private void addTopic() {
        System.out.print("Enter Topic ID: ");
        String id = scanner.nextLine();
        if (isDuplicateTopicID(id)) {
            System.out.println("Topic ID already exists.");
            return;
        }

        System.out.print("Enter Topic Name: ");
        String name = scanner.nextLine();
        System.out.print("Enter Topic Type (long/short): ");
        String type = scanner.nextLine();
        System.out.print("Enter Topic Title: ");
        String title = scanner.nextLine();
        System.out.print("Enter Topic Duration (in hours): ");
        int duration = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        Topic newTopic = new Topic(id, name, type, title, duration);
        topics.add(newTopic);
        System.out.println("Topic added successfully.");
    }

// Check for Duplicate Topic ID
    private boolean isDuplicateTopicID(String id) {
        for (Topic topic : topics) {
            if (topic.getId().equals(id)) {
                return true;
            }
        }
        return false;
    }

// Update Topic
    private void updateTopic() {
        System.out.print("Enter Topic ID to update: ");
        String id = scanner.nextLine();
        Topic topic = findTopicByID(id);
        if (topic == null) {
            System.out.println("The topic does not exist.");
            return;
        }

        System.out.print("Enter new name (leave blank to keep current): ");
        String name = scanner.nextLine();
        if (!name.isEmpty()) {
            topic.setName(name);
        }

        // Repeat for other fields...
        System.out.println("Topic updated successfully.");
    }

// Delete Topic
    private void deleteTopic() {
        System.out.print("Enter Topic ID to delete: ");
        String id = scanner.nextLine();
        Topic topic = findTopicByID(id);
        if (topic == null) {
            System.out.println("The topic does not exist.");
            return;
        }

        topics.remove(topic);
        System.out.println("Topic deleted successfully.");
    }

// Find Topic by ID
    private Topic findTopicByID(String id) {
        for (Topic topic : topics) {
            if (topic.getId().equals(id)) {
                return topic;
            }
        }
        return null;
    }

// Display All Topics
    private void displayAllTopics() {
        topics.sort(Comparator.comparing(Topic::getName));
        for (Topic topic : topics) {
            System.out.println(topic);
        }
    }

    public void manageCourses() {
        while (true) {
            System.out.println("----- Manage Courses -----");
            System.out.println("1. Add Course");
            System.out.println("2. Update Course");
            System.out.println("3. Delete Course");
            System.out.println("4. Display Course Information");
            System.out.println("5. Display All Courses");
            System.out.println("6. Back to Main Menu");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    addCourse();
                    break;
                case 2:
                    updateCourse();
                    break;
                case 3:
                    deleteCourse();
                    break;
                case 4:
                    displayCourseInfo();
                    break;
                case 5:
                    displayAllCourses();
                    break;
                case 6:
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

// Add Course
    private void addCourse() {
        System.out.print("Enter Course ID: ");
        String id = scanner.nextLine();
        if (isDuplicateCourseID(id)) {
            System.out.println("Course ID already exists.");
            return;
        }

        System.out.print("Enter Course Name: ");
        String name = scanner.nextLine();
        System.out.print("Enter Course Type (online/offline): ");
        String type = scanner.nextLine();
        System.out.print("Enter Course Title: ");
        String title = scanner.nextLine();
        System.out.print("Enter Begin Date (YYYY-MM-DD): ");
        LocalDate beginDate = LocalDate.parse(scanner.nextLine());
        System.out.print("Enter End Date (YYYY-MM-DD): ");
        LocalDate endDate = LocalDate.parse(scanner.nextLine());
        System.out.print("Enter Tuition Fee: ");
        double tuitionFee = scanner.nextDouble();
        scanner.nextLine(); // Consume newline

        Topic topic = selectTopic(); // Assume a method to select an existing topic

        Course newCourse = new Course(id, name, type, title, beginDate, endDate, tuitionFee, topic);
        courses.add(newCourse);
        System.out.println("Course added successfully.");
    }
    // Method to select a topic from the available topics

    private Topic selectTopic() {
        System.out.println("Available Topics:");
        for (Topic topic : topics) {
            System.out.println(topic); // Ensure Topic class has a proper toString() method
        }

        System.out.print("Enter Topic ID to select: ");
        String id = scanner.nextLine();

        Topic selectedTopic = findTopicByID(id); // Method to find topic by ID
        if (selectedTopic == null) {
            System.out.println("Topic not found. Please try again.");
            return selectTopic(); // Recursive call until a valid topic is selected
        }

        return selectedTopic;
    }


    private boolean isDuplicateCourseID(String id) {
        for (Course course : courses) {
            if (course.getId().equals(id)) {
                return true;
            }
        }
        return false;
    }

// Update Course
    private void updateCourse() {
        System.out.print("Enter Course ID to update: ");
        String id = scanner.nextLine();
        Course course = findCourseByID(id);
        if (course == null) {
            System.out.println("The course does not exist.");
            return;
        }

        // Update fields similarly to update topic...
        System.out.println("Course updated successfully.");
    }

// Delete Course
    private void deleteCourse() {
        System.out.print("Enter Course ID to delete: ");
        String id = scanner.nextLine();
        Course course = findCourseByID(id);
        if (course == null) {
            System.out.println("The course does not exist.");
            return;
        }

        courses.remove(course);
        System.out.println("Course deleted successfully.");
    }

// Find Course by ID
    private Course findCourseByID(String id) {
        for (Course course : courses) {
            if (course.getId().equals(id)) {
                return course;
            }
        }
        return null;
    }

// Display Course Information
    private void displayCourseInfo() {
        System.out.print("Enter Course ID: ");
        String id = scanner.nextLine();
        Course course = findCourseByID(id);
        if (course == null) {
            System.out.println("The course does not exist.");
            return;
        }
        System.out.println(course);
    }

// Display All Courses
    private void displayAllCourses() {
        if (courses.isEmpty()) {
            System.out.println("No courses available.");
            return;
        }

        // Sort courses by begin date
        courses.sort(Comparator.comparing(Course::getBeginDate));

        System.out.println("----- All Courses -----");
        for (Course course : courses) {
            System.out.println(course); // Ensure Course class has a proper toString() method
        }
    }

    public void manageLearners() {
        while (true) {
            System.out.println("----- Manage Learners -----");
            System.out.println("1. Add Learner to Course");
            System.out.println("2. Enter Scores for Learners");
            System.out.println("3. Display Learner Information");
            System.out.println("4. Back to Main Menu");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    addLearner();
                    break;
                case 2:
                    enterScores();
                    break;
                case 3:
                    displayLearnerInfo();
                    break;
                case 4:
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

// Add Learner to Course
    private void addLearner() {
        System.out.print("Enter Learner ID: ");
        String id = scanner.nextLine();
        if (isDuplicateLearnerID(id)) {
            System.out.println("Learner ID already exists.");
            return;
        }

        System.out.print("Enter Learner Name: ");
        String name = scanner.nextLine();
        System.out.print("Enter Birth Date (YYYY-MM-DD): ");
        LocalDate birthDate = LocalDate.parse(scanner.nextLine());
        Course course = selectCourse(); // Assume a method to select an existing course

        Learner newLearner = new Learner(id, name, birthDate, course);
        learners.add(newLearner);
        System.out.println("Learner added successfully.");
    }

// Check for Duplicate Learner ID
    private boolean isDuplicateLearnerID(String id) {
        for (Learner learner : learners) {
            if (learner.getId().equals(id)) {
                return true;
            }
        }
        return false;
    }

// Enter Scores for Learners
    private void enterScores() {
        System.out.print("Enter Learner ID to enter score: ");
        String id = scanner.nextLine();
        Learner learner = findLearnerByID(id);
        if (learner == null) {
            System.out.println("The learner does not exist.");
            return;
        }

        System.out.print("Enter score: ");
        double score = scanner.nextDouble();
        learner.setScore(score);
        System.out.println("Score updated successfully.");
    }

// Find Learner by ID
    private Learner findLearnerByID(String id) {
        for (Learner learner : learners) {
            if (learner.getId().equals(id)) {
                return learner;
            }
        }
        return null;
    }

// Display Learner Information
    private void displayLearnerInfo() {
        System.out.print("Enter Learner ID: ");
        String id = scanner.nextLine();
        Learner learner = findLearnerByID(id);
        if (learner == null) {
            System.out.println("The learner does not exist.");
            return;
        }
        System.out.println(learner);
    }

    public void searchInformation() {
        while (true) {
            System.out.println("----- Search Information -----");
            System.out.println("1. Search Topic");
            System.out.println("2. Search Course");
            System.out.println("3. Back to Main Menu");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    searchTopic();
                    break;
                case 2:
                    searchCourse();
                    break;
                case 3:
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

// Search Topic
    private void searchTopic() {
        System.out.print("Enter Topic Name to search: ");
        String name = scanner.nextLine();
        for (Topic topic : topics) {
            if (topic.getName().toLowerCase().contains(name.toLowerCase())) {
                System.out.println(topic);
            }
        }
    }
    // Method to select a course from the available courses

    private Course selectCourse() {
        System.out.println("Available Courses:");
        for (Course course : courses) {
            System.out.println(course);
        }
        System.out.print("Enter Course ID to select: ");
        String id = scanner.nextLine();

        Course selectedCourse = findCourseByID(id);
        if (selectedCourse == null) {
            System.out.println("Course not found. Please try again.");
            return selectCourse(); // Recursive call until a valid course is selected
        }
        return selectedCourse;
    }

// Search Course
    private void searchCourse() {
        System.out.print("Enter Course Name to search: ");
        String name = scanner.nextLine();
        for (Course course : courses) {
            if (course.getName().toLowerCase().contains(name.toLowerCase())) {
                System.out.println(course);
            }
        }

        System.out.print("Search by Topic (Enter Topic ID): ");
        String topicId = scanner.nextLine();
        for (Course course : courses) {
            if (course.getTopic().getId().equals(topicId)) {
                System.out.println(course);
            }
        }
    }

    private void saveDataToFile() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("data.dat"))) {
            oos.writeObject(topics);
            oos.writeObject(courses);
            oos.writeObject(learners);
            System.out.println("Data saved successfully.");
        } catch (IOException e) {
            System.out.println("Error saving data: " + e.getMessage());
        }
    }

    private void loadDataFromFile() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("data.dat"))) {
            topics = (List<Topic>) ois.readObject();
            courses = (List<Course>) ois.readObject();
            learners = (List<Learner>) ois.readObject();
            System.out.println("Data loaded successfully.");
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error loading data: " + e.getMessage());
        }
    }

}

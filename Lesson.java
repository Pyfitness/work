import java.util.ArrayList;
import java.util.List;

public class Lesson {
    private String day;
    private String time;
    private int grade;
    private Coach coach;
    private List<Learner> learners;

    // Constructor
    public Lesson(String day, String time, int grade, Coach coach) {
        this.day = day;
        this.time = time;
        this.grade = grade;
        this.coach = coach;
        this.learners = new ArrayList<>();
    }

    // Getter for day
    public String getDay() {
        return day;
    }

    // Setter for day
    public void setDay(String day) {
        this.day = day;
    }

    // Getter for time
    public String getTime() {
        return time;
    }

    // Setter for time
    public void setTime(String time) {
        this.time = time;
    }

    // Getter for grade
    public int getGrade() {
        return grade;
    }

    // Setter for grade
    public void setGrade(int grade) {
        this.grade = grade;
    }

    // Getter for coach
    public Coach getCoach() {
        return coach;
    }

    // Setter for coach
    public void setCoach(Coach coach) {
        this.coach = coach;
    }

    // Getter for learners
    public List<Learner> getLearners() {
        return new ArrayList<>(learners); // Return a copy of the list to prevent external modification
    }

    // Method to check if there is vacancy
    public boolean hasVacancy() {
        return learners.size() < 4;
    }

    // Method to add a learner
    public void addLearner(Learner learner) {
        if (learner != null && hasVacancy() && !learners.contains(learner)) {
            learners.add(learner);
        }
    }
}

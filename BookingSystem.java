import java.util.ArrayList;
import java.util.List;

public class BookingSystem {
    private List<Lesson> lessons;
    private List<Learner> learners;
    private List<Coach> coaches;

    public BookingSystem() {
        this.lessons = new ArrayList<>();
        this.learners = new ArrayList<>();
        this.coaches = new ArrayList<>();
    }

    // Methods for managing learners, lessons, bookings, and generating reports

    public void addLearner(Learner learner) {
        if (learner != null && !learners.contains(learner)) {
            learners.add(learner);
        }
    }

    public void addCoach(Coach coach) {
        if (coach != null && !coaches.contains(coach)) {
            coaches.add(coach);
        }
    }

    public void addLesson(Lesson lesson) {
        if (lesson != null && !lessons.contains(lesson)) {
            lessons.add(lesson);
        }
    }

    public void bookLesson(String learnerName, String day, String time, int grade) {
        for (Lesson lesson : lessons) {
            if (lesson.getDay().equals(day) && lesson.getTime().equals(time) && lesson.getGrade() == grade
                    && lesson.hasVacancy()) {
                for (Learner learner : learners) {
                    if (learner.getName().equals(learnerName)
                            && (learner.getGrade() == grade || learner.getGrade() + 1 == grade)) {
                        lesson.addLearner(learner);
                        System.out.println("Booking successful for " + learnerName);
                        return;
                    }
                }
                System.out.println("No such learner found");
                return;
            }
        }
        System.out.println("No such lesson found");
    }

    // Method to view the timetable by day
    public void viewTimetableByDay(String day) {
        System.out.println("Timetable for " + day + ":");
        for (Lesson lesson : lessons) {
            if (lesson.getDay().equalsIgnoreCase(day)) {
                System.out.println("Time: " + lesson.getTime() + ", Grade: " + lesson.getGrade() + ", Coach: "
                        + lesson.getCoach().getName() + ", Vacancy: " + (4 - lesson.getLearners().size()));
            }
        }
    }

    // Method to view the timetable by grade
    public void viewTimetableByGrade(int grade) {
        System.out.println("Timetable for Grade " + grade + ":");
        for (Lesson lesson : lessons) {
            if (lesson.getGrade() == grade) {
                System.out.println("Day: " + lesson.getDay() + ", Time: " + lesson.getTime() + ", Coach: "
                        + lesson.getCoach().getName() + ", Vacancy: " + (4 - lesson.getLearners().size()));
            }
        }
    }

    // Method to view the timetable by coach
    public void viewTimetableByCoach(String coachName) {
        System.out.println("Timetable for Coach " + coachName + ":");
        for (Lesson lesson : lessons) {
            if (lesson.getCoach().getName().equalsIgnoreCase(coachName)) {
                System.out.println("Day: " + lesson.getDay() + ", Time: " + lesson.getTime() + ", Grade: "
                        + lesson.getGrade() + ", Vacancy: " + (4 - lesson.getLearners().size()));
            }
        }
    }

    // Method to change a booking
    public void changeBooking(String learnerName, String oldDay, String oldTime, String newDay, String newTime,
            int newGrade) {
        // Find and remove the learner from the old lesson
        boolean removed = false;
        for (Lesson lesson : lessons) {
            if (lesson.getDay().equals(oldDay) && lesson.getTime().equals(oldTime)) {
                for (Learner learner : lesson.getLearners()) {
                    if (learner.getName().equals(learnerName)) {
                        lesson.getLearners().remove(learner);
                        removed = true;
                        break;
                    }
                }
                if (removed)
                    break;
            }
        }
        // Add the learner to the new lesson if they were successfully removed from the
        // old one
        if (removed) {
            bookLesson(learnerName, newDay, newTime, newGrade);
        } else {
            System.out.println("Failed to change booking: No existing booking found.");
        }
    }

    // Method to cancel a booking
    public void cancelBooking(String learnerName, String day, String time) {
        for (Lesson lesson : lessons) {
            if (lesson.getDay().equals(day) && lesson.getTime().equals(time)) {
                for (int i = 0; i < lesson.getLearners().size(); i++) {
                    if (lesson.getLearners().get(i).getName().equals(learnerName)) {
                        lesson.getLearners().remove(i);
                        System.out.println("Booking cancelled for " + learnerName);
                        return;
                    }
                }
                System.out.println("Failed to cancel booking: Learner not found in the specified lesson.");
                return;
            }
        }
        System.out.println("Failed to cancel booking: No such lesson found.");
    }

    public void printLessons() {
        for (Lesson lesson : lessons) {
            System.out.println("Day: " + lesson.getDay() + ", Time: " + lesson.getTime() + ", Grade: "
                    + lesson.getGrade() + ", Coach: " + lesson.getCoach().getName() + ", Vacancy: "
                    + (4 - lesson.getLearners().size()));
        }
    }

    public void attendLesson(String learnerName, String day, String time) {
        for (Lesson lesson : lessons) {
            if (lesson.getDay().equalsIgnoreCase(day) && lesson.getTime().equalsIgnoreCase(time)) {
                for (Learner learner : lesson.getLearners()) {
                    if (learner.getName().equalsIgnoreCase(learnerName)) {
                        // This is where you'd typically log attendance. For now, we'll just print.
                        System.out.println(learnerName + " attended the lesson on " + day + " at " + time);
                        return;
                    }
                }
                System.out.println("Learner " + learnerName + " is not enrolled in this lesson.");
                return;
            }
        }
        System.out.println("No lesson found for the given day and time.");
    }

    public void printMonthlyLearnerReport() {
        System.out.println("Monthly Learner Report:");
        for (Learner learner : learners) {
            System.out.println("Learner: " + learner.getName());
            for (Lesson lesson : lessons) {
                if (lesson.getLearners().contains(learner)) {
                    System.out.println("Attended: " + lesson.getDay() + " " + lesson.getTime() + " with Coach "
                            + lesson.getCoach().getName());
                }
            }
            System.out.println("-----");
        }
    }

    public void printMonthlyCoachReport() {
        System.out.println("Monthly Coach Report:");
        for (Coach coach : coaches) {
            System.out.println("Coach: " + coach.getName());
            double averageRating = coach.calculateAverageRating();
            System.out.println("Average Rating: " + averageRating);
            System.out.println("Lessons conducted:");
            for (Lesson lesson : lessons) {
                if (lesson.getCoach().equals(coach)) {
                    System.out.println(lesson.getDay() + " " + lesson.getTime() + " - Grade " + lesson.getGrade());
                }
            }
            System.out.println("-----");
        }
    }

}

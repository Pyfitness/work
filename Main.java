import java.util.Scanner;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        BookingSystem system = new BookingSystem();

        // Example data population (You would add actual learners, coaches, and lessons
        // here)
        // Coaches
        Coach coach1 = new Coach("John");
        Coach coach2 = new Coach("Emily");
        Coach coach3 = new Coach("Alex");
        system.addCoach(coach1);
        system.addCoach(coach2);
        system.addCoach(coach3);

        // Lessons (Monday, Wednesday, Friday, Saturday)
        String[] days = { "Monday", "Wednesday", "Friday", "Saturday" };
        String[] timesMonToFri = { "4-5pm", "5-6pm", "6-7pm" };
        String[] timesSaturday = { "2-3pm", "3-4pm" };
        for (int grade = 1; grade <= 5; grade++) {
            for (String day : days) {
                String[] times = (day.equals("Saturday")) ? timesSaturday : timesMonToFri;
                for (String time : times) {
                    // change the coaches for every lesson for variety
                    Coach currentCoach;
                    if (grade % 3 == 1) {
                        currentCoach = coach2;
                    } else if (grade % 3 == 2) {
                        currentCoach = coach3;
                    } else {
                        currentCoach = coach1;
                    }

                    system.addLesson(new Lesson(day, time, grade, currentCoach));
                }
            }
        }

        // Learners for the swimming class
        String[] learnerNames = { "Sam", "Peter", "Robert", "John", "Asticy", "taylor", "henry", "leo", "Justin",
                "Luic", "Jennifer", "Ditto", "Candace", "Nora", "bob" };
        String[] genders = { "Female", "Male", "Female", "Male", "Female", "Male", "Female", "Male", "Female", "Male",
                "Female", "Male", "Female", "Male", "Female" };
        int[] ages = { 11, 9, 7, 18, 10, 6, 7, 8, 14, 10, 17, 19, 13, 12, 16 };
        String[] emergencyContacts = { "258-147-3690", "123-654-0789", "563-879-0258", "526-745-6523", "787-852-4585",
                "741-852-1234", "741-852-9630", "856-741-8956", "741-852-8547", "874-852-8745", "789-741-8560",
                "854-857-8874", "145-852-7452", "783-520-8563", "630-851-3201" };
        int[] grades = { 2, 1, 5, 3, 4, 2, 1, 5, 5, 4, 3, 2, 5, 5, 3 };

        for (int i = 0; i < learnerNames.length; i++) {
            system.addLearner(new Learner(learnerNames[i], genders[i], ages[i], emergencyContacts[i], grades[i]));
        }
        while (true) {
            System.out.println("Welcome to the Hatfield Junior Swimming School Booking System");
            System.out.println("1. View timetable by day");
            System.out.println("2. View timetable by grade");
            System.out.println("3. View timetable by coach");
            System.out.println("4. Book a lesson");
            System.out.println("5. Change a booking");
            System.out.println("6. Cancel a booking");
            System.out.println("7. Attend a swimming lesson");
            System.out.println("8. Monthly learner report");
            System.out.println("9. Monthly coach report");
            System.out.println("10. Register a new learner");
            System.out.println("11. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // consume the newline

            switch (choice) {
                case 1:
                    System.out.print("Enter the day (e.g., Monday): ");
                    String day = scanner.nextLine();
                    system.viewTimetableByDay(day);
                    break;

                case 2:
                    System.out.print("Enter the grade (1-5): ");
                    int grade = scanner.nextInt();
                    scanner.nextLine(); // consume the newline
                    system.viewTimetableByGrade(grade);
                    break;

                case 3:
                    System.out.print("Enter the coach's name: ");
                    String coachName = scanner.nextLine();
                    system.viewTimetableByCoach(coachName);
                    break;

                case 4:
                    System.out.print(
                            "Enter learner name, day, time, and grade separated by commas (e.g., Sam,Monday,4-5pm,1): ");
                    String[] bookingInfo = scanner.nextLine().split(",");
                    if (bookingInfo.length == 4) {
                        system.bookLesson(bookingInfo[0].trim(), bookingInfo[1].trim(), bookingInfo[2].trim(),
                                Integer.parseInt(bookingInfo[3].trim()));
                    } else {
                        System.out.println("Invalid input format.");
                    }
                    break;

                case 5:
                    System.out.print(
                            "Enter learner name, old day, old time, new day, new time, and new grade separated by commas: ");
                    String[] changeInfo = scanner.nextLine().split(",");
                    if (changeInfo.length == 6) {
                        system.changeBooking(changeInfo[0].trim(), changeInfo[1].trim(), changeInfo[2].trim(),
                                changeInfo[3].trim(), changeInfo[4].trim(), Integer.parseInt(changeInfo[5].trim()));
                    } else {
                        System.out.println("Invalid input format.");
                    }
                    break;

                case 6:
                    System.out
                            .print("Enter learner name, day, and time separated by commas (e.g., Sam,Monday,4-5pm): ");
                    String[] cancelInfo = scanner.nextLine().split(",");
                    if (cancelInfo.length == 3) {
                        system.cancelBooking(cancelInfo[0].trim(), cancelInfo[1].trim(), cancelInfo[2].trim());
                    } else {
                        System.out.println("Invalid input format.");
                    }
                    break;

                case 7:
                    System.out
                            .print("Enter learner name, day, and time separated by commas (e.g., Sam,Monday,4-5pm): ");
                    String[] attendanceInfo = scanner.nextLine().split(",");
                    if (attendanceInfo.length == 3) {
                        system.attendLesson(attendanceInfo[0].trim(), attendanceInfo[1].trim(),
                                attendanceInfo[2].trim());
                    } else {
                        System.out.println("Invalid input format.");
                    }
                    break;

                case 8:
                    system.printMonthlyLearnerReport();
                    break;

                case 9:
                    system.printMonthlyCoachReport();
                    break;

                case 10:
                    System.out.print(
                            "Enter learner name, gender, age, emergency contact, and grade separated by commas: ");
                    String[] learnerInfo = scanner.nextLine().split(",");
                    if (learnerInfo.length == 5) {
                        system.addLearner(new Learner(learnerInfo[0].trim(), learnerInfo[1].trim(),
                                Integer.parseInt(learnerInfo[2].trim()), learnerInfo[3].trim(),
                                Integer.parseInt(learnerInfo[4].trim())));
                        System.out.println("Learner registered successfully.");
                    } else {
                        System.out.println("Invalid input format.");
                    }
                    break;

                case 11:
                    System.out.println("Exiting... Thank you for using the booking system.");
                    System.exit(0);
                    break;
                default:

                    System.out.println("Invalid choice. Please enter a number between 1 and 7.");
            }
            System.out.println();
        }
    }
}

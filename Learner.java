public class Learner {
    private String name;
    private String gender;
    private int age;
    private String emergencyContact;
    private int grade;

    public Learner(String name, String gender, int age, String emergencyContact, int grade) {
        this.name = name;
        this.gender = gender;
        this.age = age;
        this.emergencyContact = emergencyContact;
        this.grade = grade;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getEmergencyContact() {
        return emergencyContact;
    }

    public void setEmergencyContact(String emergencyContact) {
        this.emergencyContact = emergencyContact;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    // Constructor, getters, and setters
}

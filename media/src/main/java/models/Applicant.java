package models;

public class Applicant {
    private int id;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String email;
    private int applicationCode;

    public Applicant(){
        this.id = 0;
        this.firstName = "";
        this.lastName = "";
        this.phoneNumber = "";
        this.email = "";
        this.applicationCode = 0;
    }
    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getApplicationCode() {
        return applicationCode;
    }

    public void setApplicationCode(int applicationCode) {
        this.applicationCode = applicationCode;
    }
}

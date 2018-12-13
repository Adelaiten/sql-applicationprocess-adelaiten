package views;

public class MenusPrinter {

    public void printMainMenu() {
        System.out.println("What would you like to do?");
        System.out.println("(1) Mentors menu\n(2) Applicants");
    }

    public void printMentorMenu(){
        System.out.println("(1) Read all Mentors \n(2) Read mentors first and last name\n(3) Read mentor by id\n(4) Read mentor by city");
    }

    public void printApplicantMenu() {
        System.out.println("(1) Read all Applicants\n(2) Read applicant by first name\n(3) Read applicant by email\n(4) Read applicant by applicant code\n(5) Add applicant\n (6) Update applicant");
    }
}

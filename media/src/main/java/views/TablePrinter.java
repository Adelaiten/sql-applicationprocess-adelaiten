package views;

import models.Applicant;
import models.Mentor;

import java.util.List;

public class TablePrinter {


    public void printMentorListTable(List<Mentor> mentors){
        String format = "|%1$-3s|%2$-15s|%3$-15s|%4$-15s|%5$-20s|%6$-10s|%7$-35s|%8$-16s|\n";
        System.out.println("Mentors:");
        System.out.format(format,"id","First Name", "Last Name", "Nick Name", "Phone Number", "City", "Email", "Favourite Number");
        for(Mentor mentor : mentors) {

            System.out.format(format, mentor.getId(), mentor.getFirstName(), mentor.getLastName(), mentor.getNickName(), mentor.getPhoneNumber(), mentor.getCity(), mentor.getEmail(), mentor.getFavouriteNumber());
        }
    }


    public void printApplicantListTable(List<Applicant> applicants){
        String format = "|%1$-3s|%2$-15s|%3$-15s|%4$-20s|%5$-60s|%6$-16s|\n";
        System.out.println("Applicants:");
        System.out.format(format,"id","First Name", "Last Name",  "Phone Number", "Email", "Application Code");
        for(Applicant applicant : applicants) {
            System.out.format(format, applicant.getId(), applicant.getFirstName(), applicant.getLastName(), applicant.getPhoneNumber(), applicant.getEmail(), applicant.getApplicationCode());
        }
    }


}

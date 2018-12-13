package view;

import dao.ApplicantsDAO;
import dao.MentorsDAO;
import dao.daoInterface.ApplicantsDaoInterface;
import models.Applicant;
import models.Mentor;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class View {




    public void printMentorMenu(){
        System.out.println("(1) Read all Mentors \n(2) Read mentors first and last name\n(3) Read mentor by id\n(4) Read mentor by city");
    }

    public void printApplicantMenu() {
        System.out.println("(1) Read all Applicants\n(2) Read applicant by first name\n(3) Read applicant by email\n(4) Read applicant by applicant code\n(5) Add applicant\n (6) Update applicant");
    }
}

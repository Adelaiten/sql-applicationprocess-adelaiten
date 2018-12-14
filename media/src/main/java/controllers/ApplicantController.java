package controllers;

import dao.ApplicantsDAO;
import dao.daoInterface.ApplicantsDaoInterface;
import models.Applicant;
import models.Mentor;
import views.MenusPrinter;
import views.TablePrinter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class ApplicantController {
    private BufferedReader bufferedReader;
    private String answer = "";
    private ApplicantsDaoInterface applicantsDao;
    private TablePrinter tablePrinter;

    public ApplicantController(Connection connection) {
        this.bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        this.applicantsDao =  new ApplicantsDAO(connection);
        this.tablePrinter = new TablePrinter();
    }

    public void runApplicantController(MenusPrinter menusPrinter){
        menusPrinter.printApplicantMenu();
        try{
            answer = bufferedReader.readLine();
        }catch(IOException e){
            System.out.println("Wrong input!");
        }
        if(answer.equals("5")){
            System.out.println("Leaving to main menu!");
        }else if(answer.equals("1")){
            try{
                readApplicants();
            }catch(SQLException sql){
                System.out.println("Couldn't find those mentors!");
            }
        }else if(answer.equals("2")){
            try{
                readApplicants(mentorsDao.getMentorsFirstAndLastName());
            }catch(SQLException sql){
                System.out.println("Couldn't find those mentors!");
            }
        }else if(answer.equals("3")){
            try{
                readMentors(mentorsDao.getAllMentors());
            }catch(SQLException sql){
                System.out.println("Couldn't find those mentors!");
            }
            readMentorById();

        }else if(answer.equals("4")){
            try{
                readMentors(mentorsDao.getAllMentors());
            }catch(SQLException sql){
                System.out.println("Couldn't find those mentors!");
            }
            getMentorByCity();
        }
    }

    private void readApplicants(List<Applicant> applicants) {
        List<Applicant> allApplicants = applicants;
        if (applicants != null) {
            tablePrinter.printApplicantListTable(applicants);
        } else {
            System.out.println("There are no mentors!");
        }
    }
}

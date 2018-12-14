package controllers;

import dao.ApplicantsDAO;
import dao.daoInterface.ApplicantsDaoInterface;
import models.Applicant;
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
                List<Applicant> applicantList = applicantsDao.getAllApplicants();
                readApplicants(applicantList);
            }catch(SQLException sql){
                System.out.println("Couldn't find those mentors!");
            }
        }else if(answer.equals("2")){
            System.out.println("Tell us name of applicant you would like to find!");
            String name = "";
            try{
                name = bufferedReader.readLine();
            }catch(IOException e){
                System.out.println("Wrong input");
            }
            try{
                readApplicants(applicantsDao.getApplicantByFirstName(name));
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

    private void readApplicants(List<Applicant> applicantsList) {

        List<Applicant> allApplicants =  applicantsList;
        if (allApplicants != null) {
            tablePrinter.printApplicantListTable(allApplicants);
        } else {
            System.out.println("There are no mentors!");
        }
    }
    private void getApplicantByCity() {
        System.out.println("What applicant would you like to see (name)?");
        try{
            answer = bufferedReader.readLine();
        }catch(IOException e){
            System.out.println("Wrong input!");
        }
        try{
            List<Applicant> applicantByFirstName = applicantsDao.getApplicantByFirstName(answer);
            tablePrinter.printApplicantListTable(applicantByFirstName);
        }catch(SQLException sql){
            System.out.println("There is no mentors from this city!");
        }
    }

}

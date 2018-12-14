package controllers;

import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;
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
        if(answer.equals("7")){
            System.out.println("Leaving to main menu!");
        }else if(answer.equals("1")){
            readAllAplicants();
        }else if(answer.equals("2")){
            readApplicantsByFirstName();
        }else if(answer.equals("3")){
            readApplicantByEmail();
        }else if(answer.equals("4")){
           readApplicantByApplicationCode();
        }else if(answer.equals("5")){
            addAplicant();
        }else if(answer.equals("6")){
            updateApplicant();
        }
    }

    private void updateApplicant(){
        readAllAplicants();
        System.out.println("Provide id of applicants which you'd like to change!");
        answer = inputGetter();
        Applicant applicant = new Applicant();
        try{
            System.out.println(answer);
            applicant = applicantsDao.getApplicantById(Integer.parseInt(answer));
        }catch(SQLException sql) {
            System.out.println("Cannot find this applicant");
        }

        System.out.println("What would you like to update?\n(1) First Name\n(2) Last Name\n(3) Phone number\n (4) Email\n (5) Application Code");
        answer = inputGetter();
        if(answer.equals("1")){
            System.out.println("Provide new first name");
            String firstName = inputGetter();
            applicant.setFirstName(firstName);
            applicantUpdater(applicant);
        }else if(answer.equals("2")){
            System.out.println("Provide new last name");
            String lastName =inputGetter();
            applicant.setLastName(lastName);
            applicantUpdater(applicant);
        }else if(answer.equals("3")){
            System.out.println("Provide new phone number");
            String phoneNumber =inputGetter();
            applicant.setPhoneNumber(phoneNumber);
            applicantUpdater(applicant);
        }else if(answer.equals("4")){
            System.out.println("Provide new email");
            String email =inputGetter();
            applicant.setEmail(email);
            applicantUpdater(applicant);
        }else if(answer.equals("5")){
            System.out.println("Provide new application code");
            int applicationCode =Integer.parseInt(inputGetter());
            applicant.setApplicationCode(applicationCode);
            applicantUpdater(applicant);
        }
    }

    private void applicantUpdater(Applicant applicant) {
        try{
            applicantsDao.updateApplicant(applicant);
        }catch(SQLException sql) {
            System.out.println("Cannot update");
        }
    }

    private void readAllAplicants() {
        try{
            List<Applicant> applicantList = applicantsDao.getAllApplicants();
            readApplicants(applicantList);
        }catch(SQLException sql){
            System.out.println("Couldn't find those mentors!");
        }
    }

    private void readApplicantsByFirstName() {
        System.out.println("Tell us name of applicant you would like to find!");
        String name = inputGetter();
        try{
            readApplicants(applicantsDao.getApplicantByFirstName(name));
        }catch(SQLException sql){
            System.out.println("Couldn't find those mentors!");
        }
    }

    private void addAplicant() {
        String firstName = "";
        String lastName = "";
        String phoneNumber = "";
        String email = "";
        int applicationCode = 0;
        System.out.println("First name:");
        firstName = inputGetter();
        System.out.println("Last name:");
        lastName = inputGetter();
        System.out.println("Phone Number:");
        phoneNumber = inputGetter();
        System.out.println("Email:");
        email = inputGetter();
        System.out.println("Application Code");
        applicationCode = Integer.parseInt(inputGetter());
        if(firstName.equals("") || lastName.equals("") || phoneNumber.equals("") || email.equals("") || applicationCode == 0){
            System.out.println("wrong input!");
        }else{
            Applicant applicant = new Applicant();
            applicant.setFirstName(firstName);
            applicant.setLastName(lastName);
            applicant.setPhoneNumber(phoneNumber);
            applicant.setEmail(email);
            applicant.setApplicationCode(applicationCode);
            try{
                applicantsDao.addApplicant(applicant);
            }catch(SQLException e){
                System.out.println("Cannot add this applicant!");
            }

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
    private void readApplicantByApplicationCode() {
        System.out.println("What applicant would you like to see (Application Code)?");
        answer = inputGetter();
        try{
            int answerInt = Integer.parseInt(answer);
            Applicant applicantByApplicationCode = applicantsDao.getApplicantByApplicationCode(answerInt);
            String format = "|%1$-3s|%2$-15s|%3$-15s|%4$-20s|%5$-60s|%6$-16s|\n";
            System.out.println("Applicants:");
            System.out.format(format,"id","First Name", "Last Name",  "Phone Number", "Email", "Application Code");
            System.out.format(format, applicantByApplicationCode.getId(), applicantByApplicationCode.getFirstName(), applicantByApplicationCode.getLastName(), applicantByApplicationCode.getPhoneNumber(), applicantByApplicationCode.getEmail(), applicantByApplicationCode.getApplicationCode());

        }catch(SQLException sql){
            System.out.println("There is no mentors from this city!");
        }
    }

    private void readApplicantByEmail() {
        System.out.println("What applicant would you like to see (email)?");
        answer = inputGetter();
        Applicant applicantByEmail = null;
        try{
            applicantByEmail = applicantsDao.getApplicantByEmail(answer);
        }catch(SQLException e){
            System.out.println("There is not applicant with this id!");
        }
        if(applicantByEmail != null){
            String format = "|%1$-3s|%2$-15s|%3$-15s|%4$-20s|%5$-60s|%6$-16s|\n";
            System.out.println("Applicants:");
            System.out.format(format,"id","First Name", "Last Name",  "Phone Number", "Email", "Application Code");
            System.out.format(format, applicantByEmail.getId(), applicantByEmail.getFirstName(), applicantByEmail.getLastName(), applicantByEmail.getPhoneNumber(), applicantByEmail.getEmail(), applicantByEmail.getApplicationCode());

        }else{
            System.out.println("Applicant is empty!");
        }
    }


    private String inputGetter(){
        answer = "";
        try{
            answer = bufferedReader.readLine();
        }catch(IOException e){
            System.out.println("Wrong input!");
        }

        return answer;
    }

}

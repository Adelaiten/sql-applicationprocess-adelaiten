package controllers;

import dao.MentorsDAO;
import dao.daoInterface.MentorsDaoInterface;
import models.Mentor;
import views.MenusPrinter;
import views.TablePrinter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class MentorController {
    private BufferedReader bufferedReader;
    private String answer = "";
    private MentorsDaoInterface mentorsDao;
    private TablePrinter tablePrinter;

    public MentorController(Connection connection) {
        this.bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        this.mentorsDao =  new MentorsDAO(connection);
        this.tablePrinter = new TablePrinter();
    }

    public void runMentorController(MenusPrinter menusPrinter){
        menusPrinter.printMentorMenu();
        try{
            answer = bufferedReader.readLine();
        }catch(IOException e){
            System.out.println("Wrong input!");
        }
        if(answer.equals("5")){
            System.out.println("Leaving to main menu!");
        }else if(answer.equals("1")){
            try{
                readMentors(mentorsDao.getAllMentors());
            }catch(SQLException sql){
                System.out.println("Couldn't find those mentors!");
            }
        }else if(answer.equals("2")){
            try{
                readMentors(mentorsDao.getMentorsFirstAndLastName());
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

    private void getMentorByCity() {
        System.out.println("What mentors would you like to see (city)?");
        try{
            answer = bufferedReader.readLine();
        }catch(IOException e){
            System.out.println("Wrong input!");
        }
        try{
            List<Mentor> mentorsNicknames = mentorsDao.getMentorsNickNamesByCity(answer);
            tablePrinter.printMentorListTable(mentorsNicknames);
        }catch(SQLException sql){
            System.out.println("There is no mentors from this city!");
        }
    }

    private void readMentorById() {
        System.out.println("What mentor would you like to see (ID)?");
        try{
            answer = bufferedReader.readLine();
        }catch(IOException e){
            System.out.println("Wrong input!");
        }
        int intAnswer = Integer.parseInt(answer);
        Mentor mentorById = null;
        try{
            mentorById = mentorsDao.getMentorById(intAnswer);
        }catch(SQLException e){
            System.out.println("There is not mentor with this id!");
        }
        if(mentorById != null){
            String format = "|%1$-3s|%2$-15s|%3$-15s|%4$-15s|%5$-20s|%6$-10s|%7$-35s|%8$-16s|\n";
            System.out.println("Mentors:");
            System.out.format(format,"id","First Name", "Last Name", "Nick Name", "Phone Number", "City", "Email", "Favourite Number");
            System.out.format(format, mentorById.getId(), mentorById.getFirstName(), mentorById.getLastName(), mentorById.getNickName(), mentorById.getPhoneNumber(), mentorById.getCity(), mentorById.getEmail(), mentorById.getFavouriteNumber());
        }else{
            System.out.println("Mentor is empty!");
        }
    }

    private void readMentors(List<Mentor> mentors) {
        List<Mentor> allMentors = mentors;
        if (mentors != null) {
            tablePrinter.printMentorListTable(mentors);
        } else {
            System.out.println("There are no mentors!");
        }
    }
}

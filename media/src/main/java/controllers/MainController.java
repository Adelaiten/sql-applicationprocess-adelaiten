package controllers;

import views.MenusPrinter;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;

public class MainController {
    private MenusPrinter menusPrinter = new MenusPrinter();
    private BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    private String answer = "";


    public void run(){
        boolean isRun = true;
        Connection connection = SQLConnector.getConnection();
        MentorController mentorController = new MentorController(connection);
        ApplicantController applicantController = new ApplicantController(connection);
        while(isRun) {
            menusPrinter.printMainMenu();
            try{
                answer = bufferedReader.readLine();
            }catch(IOException e){
                System.out.println("Wrong input!");
            }
            if(answer.equals("3")){
                isRun = false;
            }else if(answer.equals("1")) {
                mentorController.runMentorController(menusPrinter);

            }else if(answer.equals("2")){
                applicantController.runApplicantController(menusPrinter);
            }

        }
    }


}

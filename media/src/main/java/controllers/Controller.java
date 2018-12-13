package controllers;

import views.MenusPrinter;
import views.TablePrinter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class Controller {

    public void run(){
        boolean isRun = true;
        TablePrinter tablePrinter = new TablePrinter();
        MenusPrinter menusPrinter = new MenusPrinter();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String answer = "";
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
                menusPrinter.printMentorMenu();
            }else if(answer.equals("2")){
                menusPrinter.printApplicantMenu();;
            }

        }
    }
}

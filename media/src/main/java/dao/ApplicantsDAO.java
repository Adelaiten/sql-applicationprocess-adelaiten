package dao;

import dao.daoInterface.ApplicantsDaoInterface;
import models.Applicant;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ApplicantsDAO implements ApplicantsDaoInterface {
    private Connection connection;


    public ApplicantsDAO(Connection connection) {
        this.connection = connection;
    }


    public Applicant getApplicantById(int id) throws SQLException {
        String query = "SELECT * FROM applicants where id = ?;";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, id);
        Applicant applicant = new Applicant();
        ResultSet resultSet = preparedStatement.executeQuery();
        while(resultSet.next()){
            applicant.setId(id);
            applicant.setFirstName(resultSet.getString("first_name"));
            applicant.setLastName(resultSet.getString("last_name"));
            applicant.setPhoneNumber(resultSet.getString("phone_number"));
            applicant.setEmail(resultSet.getString("email"));
            applicant.setApplicationCode(resultSet.getInt("application_code"));
        }
        return applicant;
    }
    public List<Applicant> getAllApplicants() throws SQLException {
        String query = "SELECT * FROM applicants";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        List<Applicant> applicantsList = new ArrayList<Applicant>();
        ResultSet resultSet = preparedStatement.executeQuery();
        while(resultSet.next()){
            Applicant applicant = new Applicant();
            applicant.setId(resultSet.getInt("id"));
            applicant.setFirstName(resultSet.getString("first_name"));
            applicant.setLastName(resultSet.getString("last_name"));
            applicant.setPhoneNumber(resultSet.getString("phone_number"));
            applicant.setEmail(resultSet.getString("email"));
            applicant.setApplicationCode(resultSet.getInt("application_code"));
            applicantsList.add(applicant);
        }
        return applicantsList;
    }


    public List<Applicant> getApplicantByFirstName(String firstName) throws SQLException {
        String query = "SELECT * FROM applicants WHERE first_name=?;";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, "'" + firstName + "'");
        List<Applicant> applicantsList = new ArrayList<Applicant>();
        ResultSet resultSet = preparedStatement.executeQuery();
        while(resultSet.next()){
            Applicant applicant = new Applicant();
            applicant.setId(resultSet.getInt("id"));
            applicant.setFirstName(resultSet.getString("first_name"));
            applicant.setLastName(resultSet.getString("last_name"));
            applicant.setPhoneNumber(resultSet.getString("phone_number"));
            applicant.setEmail(resultSet.getString("email"));
            applicant.setApplicationCode(resultSet.getInt("application_code"));
            applicantsList.add(applicant);
        }
        return applicantsList;
    }


    public Applicant getApplicantByEmail(String email) throws SQLException{
        String query = "SELECT * FROM applicants WHERE email like ?;";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, "'%" + email +"'");
        Applicant applicant = new Applicant();
        ResultSet resultSet = preparedStatement.executeQuery();
        fillApplicant(resultSet, applicant);
        return applicant;
    }


    public Applicant getApplicantByApplicationCode(int applicationCode) throws SQLException{
        String query = "SELECT * FROM applicants WHERE application_code=?;";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, applicationCode);
        Applicant applicant = new Applicant();
        ResultSet resultSet = preparedStatement.executeQuery();
        fillApplicant(resultSet, applicant);
        return applicant;
    }


    public void addApplicant(Applicant applicant) throws SQLException{
        String query = "INSERT INTO applicants (first_name, last_name, phone_number, email, application_code) VALUES (?,?,?,?,?);";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, applicant.getFirstName());
        preparedStatement.setString(2, applicant.getLastName());
        preparedStatement.setString(3, applicant.getPhoneNumber());
        preparedStatement.setString(4, applicant.getEmail());
        preparedStatement.setInt(5, applicant.getApplicationCode());
        preparedStatement.executeUpdate();
    }


    public void updateApplicant(Applicant applicant) throws SQLException{ //najpierw wyzej ma pytac o id aplikanta, pozniej co chce uzupelnic, przekazuje sie obiekt
        String query = "UPDATE applicants SET first_name = ?, last_name = ?, phone_number = ?, email = ?, application_code = ? WHERE id=?;";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, applicant.getFirstName());
        preparedStatement.setString(2, applicant.getLastName());
        preparedStatement.setString(3, applicant.getPhoneNumber());
        preparedStatement.setString(4, applicant.getEmail());
        preparedStatement.setInt(5, applicant.getApplicationCode());
        preparedStatement.setInt(6, applicant.getId());
        preparedStatement.executeUpdate();
    }

    public List<Applicant> searchApplicantsByPhrase(String phrase) throws SQLException{
        String query = "select * FROM applicants WHERE id = ? OR first_name LIKE ? or last_name LIKE ? OR phone_number LIKE ? OR email LIKE ? OR application_code = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        if(isNumeric(phrase)){
            preparedStatement.setInt(1, Integer.parseInt(phrase));
            preparedStatement.setInt(6, Integer.parseInt(phrase));
        }else {
            preparedStatement.setNull(1, 0);
            preparedStatement.setNull(6, 0);
        }
        preparedStatement.setString(2, "'%" + phrase +"%'");
        preparedStatement.setString(3, "'%" + phrase +"%'");
        preparedStatement.setString(4, "'%" + phrase +"%'");
        preparedStatement.setString(5, "'%" + phrase +"%'");
        List<Applicant> applicantsList = new ArrayList<>();
        ResultSet resultSet = preparedStatement.executeQuery();
        while(resultSet.next()){
            System.out.println("JEST");
            Applicant applicant = new Applicant();
            fillApplicant(resultSet, applicant);
            applicantsList.add(applicant);
        }
        return applicantsList;
    }


    private void fillApplicant(ResultSet resultSet, Applicant applicant) throws SQLException{
        while(resultSet.next()){
            applicant.setId(resultSet.getInt("id"));
            applicant.setFirstName(resultSet.getString("first_name"));
            applicant.setLastName(resultSet.getString("last_name"));
            applicant.setPhoneNumber(resultSet.getString("phone_number"));
            applicant.setEmail(resultSet.getString("email"));
            applicant.setApplicationCode(resultSet.getInt("application_code"));
        }
    }

    private boolean isNumeric(String phrase) {
        try{
            Integer.parseInt(phrase);
        }catch(NumberFormatException | NullPointerException e){
            return false;
        }
        return true;
    }

}

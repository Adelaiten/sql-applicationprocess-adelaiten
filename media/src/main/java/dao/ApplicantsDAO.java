package dao;

import dao.daoInterface.ApplicantsDaoInterface;
import models.Applicant;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ApplicantsDAO implements ApplicantsDaoInterface {
    Connection connection;
    public ApplicantsDAO(Connection connection) {
        this.connection = connection;
    }
    public Applicant getApplicantByFirstName(String firstName) throws SQLException {
        String query = "SELECT * FROM applicants WHERE first_name=?;";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, firstName);
        Applicant applicant = new Applicant();
        ResultSet resultSet = preparedStatement.executeQuery();
        fillApplicant(resultSet, applicant);
        return applicant;
    }

    public Applicant getApplicantByEmail(String email) throws SQLException{
        String query = "SELECT * FROM applicants WHERE email=?;";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, email);
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

    public Applicant getApplicantByApplicationCode(int applicationCode) throws SQLException{
        String query = "SELECT * FROM applicants WHERE application_code=?;";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, applicationCode);
        Applicant applicant = new Applicant();
        ResultSet resultSet = preparedStatement.executeQuery();
        fillApplicant(resultSet, applicant);
        return applicant;
    }

    public void updateApplicant(Applicant applicant) throws SQLException{

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
}

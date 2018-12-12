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

    public void addApplicant(Applicant applicant) {

    }

    public Applicant getApplicantByApplicationCode(int applicationCode) {
        return null;
    }

    public void updateApplicant(Applicant applicant) {

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

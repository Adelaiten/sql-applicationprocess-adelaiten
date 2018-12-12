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
        while(resultSet.next()){
            applicant.setId(resultSet.getInt("id"));
            applicant.setFirstName(resultSet.getString("first_name"));
            applicant.setLastName(resultSet.getString("last_name"));
            applicant.setPhoneNumber(resultSet.getString("phone_number"));
            applicant.setEmail(resultSet.getString("email"));
            applicant.setApplicationCode(resultSet.getInt("application_code"));
        }
        return applicant;
    }

    public Applicant getApplicantByEmail(String email) {
        return null;
    }

    public void addApplicant(Applicant applicant) {

    }

    public Applicant getApplicantByApplicationCode(int applicationCode) {
        return null;
    }

    public void updateApplicant(Applicant applicant) {

    }
}

package dao.daoInterface;

import models.Applicant;

import java.sql.SQLException;
import java.util.List;

public interface ApplicantsDaoInterface {
    List<Applicant> getAllApplicants() throws SQLException;
    Applicant getApplicantByFirstName(String firstName) throws SQLException;
    Applicant getApplicantByEmail(String email) throws SQLException;
    void addApplicant(Applicant applicant) throws SQLException;
    Applicant getApplicantByApplicationCode(int applicationCode) throws SQLException;
    void updateApplicant(Applicant applicant) throws SQLException;
}

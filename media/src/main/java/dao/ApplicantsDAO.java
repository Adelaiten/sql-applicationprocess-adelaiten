package dao;

import models.Applicant;

public interface ApplicantsDAO {

    Applicant getApplicantByFirstName(String firstName);
    Applicant getApplicantByEmail(String email);
    void addApplicant(Applicant applicant);
    Applicant getApplicantByApplicationCode(int applicationCode);
    void updateApplicant(Applicant applicant);
}
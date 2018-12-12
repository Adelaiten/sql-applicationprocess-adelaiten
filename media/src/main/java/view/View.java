package view;

import dao.ApplicantsDAO;
import dao.MentorsDAO;
import dao.daoInterface.ApplicantsDaoInterface;
import models.Applicant;
import models.Mentor;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class View {
    public static void main(String[] args) throws SQLException {
        TablePrinter tablePrinter = new TablePrinter();
        Connection connection = SQLConnector.getConnection();
        ApplicantsDaoInterface applicantsDAO = new ApplicantsDAO(connection);
        List<Applicant> list = applicantsDAO.getAllApplicants();
        tablePrinter.printApplicantListTable(list);
    }
}

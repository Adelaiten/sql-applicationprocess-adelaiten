package view;

import dao.MentorsDAO;
import models.Mentor;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class View {
    public static void main(String[] args) throws SQLException {
        TablePrinter tablePrinter = new TablePrinter();
        Connection connection = SQLConnector.getConnection();
        MentorsDAO mentorsDAO = new MentorsDAO(connection);
        List<Mentor> list = new ArrayList<Mentor>();
        list.add(mentorsDAO.getMentorById(5));
        tablePrinter.printMentorListTable(list);
    }
}

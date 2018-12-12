package dao;

import dao.daoInterface.MentorsDaoInterface;
import models.Mentor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MentorsDAO implements MentorsDaoInterface {
    private Connection connection;

    public MentorsDAO(Connection connection) {
        this.connection = connection;
    }

    public List<Mentor> getMentorsFirstAndLastName() throws SQLException {
        String query = "SELECT first_name, last_name FROM mentors";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        List<Mentor> mentorsList = new ArrayList<Mentor>();
        ResultSet resultSet = preparedStatement.executeQuery();
        while(resultSet.next()){
            Mentor mentor = new Mentor();
            mentor.setId(resultSet.getInt("id"));
            mentor.setFirstName(resultSet.getString("first_name"));
            mentor.setLastName(resultSet.getString("last_name"));
            mentor.setNickName(resultSet.getString("nick_name"));
            mentor.setPhoneNumber(resultSet.getString("phone_number"));
            mentor.setEmail(resultSet.getString("email"));
            mentor.setCity(resultSet.getString("city"));
            mentor.setFavouriteNumber(resultSet.getInt("favourite_number"));
            mentorsList.add(mentor);
        }
        return mentorsList;

    }

    public List<Mentor> getAllMentors() throws SQLException {
        String query = "SELECT * FROM mentors;";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        List<Mentor> mentorsList = new ArrayList<Mentor>();
        ResultSet resultSet = preparedStatement.executeQuery();
        while(resultSet.next()){
            Mentor mentor = new Mentor();
            mentor.setId(resultSet.getInt("id"));
            mentor.setFirstName(resultSet.getString("first_name"));
            mentor.setLastName(resultSet.getString("last_name"));
            mentor.setNickName(resultSet.getString("nick_name"));
            mentor.setPhoneNumber(resultSet.getString("phone_number"));
            mentor.setEmail(resultSet.getString("email"));
            mentor.setCity(resultSet.getString("city"));
            mentor.setFavouriteNumber(resultSet.getInt("favourite_number"));
            mentorsList.add(mentor);
        }
        return mentorsList;
    }

    public Mentor getMentorById() throws SQLException {
        return null;
    }

    public List<Mentor> getMentorsNickNamesByCity(String city) throws SQLException{
        return null;
    }
}

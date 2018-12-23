package dao;

import dao.daoInterface.MentorsDaoInterface;
import models.Mentor;
import org.apache.commons.lang3.ObjectUtils;

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
        String query = "SELECT first_name, last_name FROM mentors;";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        List<Mentor> mentorsList = new ArrayList<Mentor>();
        ResultSet resultSet = preparedStatement.executeQuery();
        while(resultSet.next()){
            Mentor mentor = new Mentor();
            mentor.setFirstName(resultSet.getString("first_name"));
            mentor.setLastName(resultSet.getString("last_name"));
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
            mentorFiller(resultSet, mentor);
            mentorsList.add(mentor);
        }
        return mentorsList;
    }

    public Mentor getMentorById(int id) throws SQLException {
        String query = "SELECT * FROM mentors where id = ?;";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, id);
        Mentor mentor = new Mentor();
        ResultSet resultSet = preparedStatement.executeQuery();
        while(resultSet.next()){

        }
        return mentor;
    }

    public List<Mentor> getMentorsNickNamesByCity(String city) throws SQLException{
        String query = "SELECT nick_name FROM mentors where city = ?;";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, city);
        List<Mentor> mentorsList = new ArrayList<Mentor>();
        ResultSet resultSet = preparedStatement.executeQuery();
        while(resultSet.next()){
            Mentor mentor = new Mentor();
            mentor.setNickName(resultSet.getString("nick_name"));
            mentorsList.add(mentor);
        }
        return mentorsList;
    }

    public List<Mentor> searchMentorByPhrase(String phrase) throws SQLException{
        String query = "select * FROM mentors WHERE id = ? OR first_name LIKE ? or last_name LIKE ? OR nick_name LIKE ? OR phone_number LIKE ? OR email LIKE ? or city LIKE ? OR favourite_number = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        if(isNumeric(phrase)){
            preparedStatement.setInt(1, Integer.parseInt(phrase));
            preparedStatement.setInt(8, Integer.parseInt(phrase));
        }else {
            preparedStatement.setNull(1, 0);
            preparedStatement.setNull(8, 0);
        }
        preparedStatement.setString(2, "'%" + phrase +"%'");
        preparedStatement.setString(3, "'%" + phrase +"%'");
        preparedStatement.setString(4, "'%" + phrase +"%'");
        preparedStatement.setString(5, "'%" + phrase +"%'");
        preparedStatement.setString(6, "'%" + phrase +"%'");
        preparedStatement.setString(7, "'%" + phrase +"%'");
        List<Mentor> mentorsList = new ArrayList<>();
        ResultSet resultSet = preparedStatement.executeQuery();
        while(resultSet.next()){
            Mentor mentor = new Mentor();
            mentorFiller(resultSet, mentor);
            mentorsList.add(mentor);
        }
        return mentorsList;
    }

    private void mentorFiller(ResultSet resultSet, Mentor mentor) throws SQLException{
        mentor.setId(resultSet.getInt("id"));
        mentor.setFirstName(resultSet.getString("first_name"));
        mentor.setLastName(resultSet.getString("last_name"));
        mentor.setNickName(resultSet.getString("nick_name"));
        mentor.setPhoneNumber(resultSet.getString("phone_number"));
        mentor.setEmail(resultSet.getString("email"));
        mentor.setCity(resultSet.getString("city"));
        mentor.setFavouriteNumber(resultSet.getInt("favourite_number"));
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

package dao.daoInterface;

import models.Mentor;

import java.sql.SQLException;
import java.util.List;

public interface MentorsDaoInterface {

    List<Mentor> getAllMentors() throws SQLException;
    Mentor getMentorById(int id) throws SQLException;
    List<Mentor> getMentorsFirstAndLastName() throws SQLException;
    List<Mentor> getMentorsNickNamesByCity(String city) throws SQLException;
    List<Mentor> searchMentorByPhrase(String phrase) throws SQLException;

}

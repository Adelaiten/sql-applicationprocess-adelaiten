package dao;

import models.Mentor;

import java.util.List;

public interface MentorsDAO {

    List<Mentor> getMentorsFirstAndLastName();
    List<Mentor> getMentorsNickNamesByCity(String city);

}

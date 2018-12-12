package dao.daoInterface;

import models.Mentor;

import java.util.List;

public interface MentorsDaoInterface {

    List<Mentor> getMentorsFirstAndLastName();
    List<Mentor> getMentorsNickNamesByCity(String city);

}

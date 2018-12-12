package dao;

import models.Mentor;

import java.util.List;

public interface ApplicantsDAO {

    List<Mentor> getMentorsFirstAndLastName();
    List<Mentor> getMentorsNickNamesByCity();

}

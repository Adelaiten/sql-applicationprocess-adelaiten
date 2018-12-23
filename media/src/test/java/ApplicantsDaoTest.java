
import dao.ApplicantsDAO;
import dao.daoInterface.ApplicantsDaoInterface;
import models.Applicant;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

public class ApplicantsDaoTest {


    @Test
    public void testGetAllAplicantsFilled() throws SQLException {
        Connection connection = mock(Connection.class);
        ApplicantsDaoInterface applicantsDao = new ApplicantsDAO(connection);
        ResultSet resultSet = mock(ResultSet.class);
        PreparedStatement preparedStatement = mock(PreparedStatement.class);
        when(connection.prepareStatement(Mockito.anyString())).thenReturn(preparedStatement);
        when(preparedStatement.executeQuery()).thenReturn(resultSet);
        when(resultSet.next()).thenReturn(true).thenReturn(false);
        when(resultSet.getInt(Mockito.anyString())).thenReturn(1);
        when(resultSet.getString(Mockito.anyString())).thenReturn("Karol");
        when(resultSet.getString(Mockito.anyString())).thenReturn("Trzaska");
        when(resultSet.getString(Mockito.anyString())).thenReturn("2093821");
        when(resultSet.getString(Mockito.anyString())).thenReturn("karoltrzaska19@gmail.com");
        when(resultSet.getInt(Mockito.anyString())).thenReturn(1000);
        assertTrue(applicantsDao.getAllApplicants().get(0) != null);
    }

    @Test
    public void testGetApplicantByIdReturnApplicant() throws SQLException {
        Connection connection = mock(Connection.class);
        ApplicantsDaoInterface applicantsDao = new ApplicantsDAO(connection);
        ResultSet resultSet = mock(ResultSet.class);
        PreparedStatement preparedStatement = mock(PreparedStatement.class);
        when(connection.prepareStatement(Mockito.anyString())).thenReturn(preparedStatement);
        when(preparedStatement.executeQuery()).thenReturn(resultSet);
        when(resultSet.next()).thenReturn(true).thenReturn(false);
        when(resultSet.getInt(Mockito.anyString())).thenReturn(1);
        when(resultSet.getString(Mockito.anyString())).thenReturn("Karol");
        when(resultSet.getString(Mockito.anyString())).thenReturn("Trzaska");
        when(resultSet.getString(Mockito.anyString())).thenReturn("2093821");
        when(resultSet.getString(Mockito.anyString())).thenReturn("karoltrzaska19@gmail.com");
        when(resultSet.getInt(Mockito.anyString())).thenReturn(1000);
        assertTrue(applicantsDao.getApplicantById(2) != null);
    }

    @Test
    public void testGetApplicantByEmailReturnApplicant() throws SQLException {
        Connection connection = mock(Connection.class);
        ApplicantsDaoInterface applicantsDao = new ApplicantsDAO(connection);
        ResultSet resultSet = mock(ResultSet.class);
        PreparedStatement preparedStatement = mock(PreparedStatement.class);
        when(connection.prepareStatement(Mockito.anyString())).thenReturn(preparedStatement);
        when(preparedStatement.executeQuery()).thenReturn(resultSet);
        when(resultSet.next()).thenReturn(true).thenReturn(false);
        when(resultSet.getInt(Mockito.anyString())).thenReturn(1);
        when(resultSet.getString(Mockito.anyString())).thenReturn("Karol");
        when(resultSet.getString(Mockito.anyString())).thenReturn("Trzaska");
        when(resultSet.getString(Mockito.anyString())).thenReturn("2093821");
        when(resultSet.getString(Mockito.anyString())).thenReturn("karoltrzaska19@gmail.com");
        when(resultSet.getInt(Mockito.anyString())).thenReturn(1000);
        assertTrue(applicantsDao.getApplicantByEmail("karoltrzaska19@gmail.com") != null);
    }
}

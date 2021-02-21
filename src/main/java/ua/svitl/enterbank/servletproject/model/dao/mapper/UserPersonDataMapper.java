package ua.svitl.enterbank.servletproject.model.dao.mapper;

import ua.svitl.enterbank.servletproject.model.dto.UserPersonDataDto;
import ua.svitl.enterbank.servletproject.model.entity.User;
import ua.svitl.enterbank.servletproject.model.entity.constants.EntityConstants;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class UserPersonDataMapper implements ObjectMapper<UserPersonDataDto> {
    /**
     * Builds object of type T from the given query result.
     *
     * @param resultSet result set from the query
     * @return a built object
     * @throws SQLException if could not be completed
     */
    @Override
    public UserPersonDataDto map(ResultSet resultSet) throws SQLException {
        UserPersonDataDto userPersonData = new UserPersonDataDto();
        userPersonData.setUserId(resultSet.getInt("user_id"));
        userPersonData.setUserId(resultSet.getInt("person_id"));
        userPersonData.setUserName(resultSet.getString("user_name"));
        userPersonData.setUserEmail(resultSet.getString("email"));
        userPersonData.setLastName(resultSet.getString("last_name"));
        userPersonData.setFirstName(resultSet.getString("first_name"));
        userPersonData.setMiddleName(resultSet.getString("middle_name"));
        userPersonData.setBirthDate(
           resultSet.getTimestamp("birth_date").toLocalDateTime()
        );
        userPersonData.setPhoneCountryCode(resultSet.getString("phone_country_code"));
        userPersonData.setPhone(resultSet.getString("phone"));
        userPersonData.setActive(resultSet.getBoolean("is_active"));
        return userPersonData;
    }
}

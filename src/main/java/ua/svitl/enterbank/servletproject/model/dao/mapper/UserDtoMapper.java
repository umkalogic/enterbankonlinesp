package ua.svitl.enterbank.servletproject.model.dao.mapper;

import ua.svitl.enterbank.servletproject.model.dto.UserDto;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDtoMapper implements ObjectMapper<UserDto> {

    /**
     * Builds object of type T from the given query result.
     *
     * @param resultSet result set from the query
     * @return a built object
     * @throws SQLException if could not be completed
     */
    @Override
    public UserDto map(ResultSet resultSet) throws SQLException {
        UserDto user = new UserDto();
        user.setUserId(resultSet.getInt("user_id"));
        user.setEmail(resultSet.getString("email"));
        user.setActive(resultSet.getBoolean("is_active"));
        user.setUserName(resultSet.getString("user_name"));
        return user;
    }
}

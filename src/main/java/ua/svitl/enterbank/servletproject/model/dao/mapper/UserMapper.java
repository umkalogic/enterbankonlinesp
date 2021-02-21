package ua.svitl.enterbank.servletproject.model.dao.mapper;

import ua.svitl.enterbank.servletproject.model.entity.User;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserMapper implements ObjectMapper<User> {
    @Override
    public User map(ResultSet resultSet) throws SQLException {
        User user = new User();
        user.setUserId(resultSet.getInt("user_id"));
        user.setEmail(resultSet.getString("email"));
        user.setIsActive(resultSet.getBoolean("is_active"));
        user.setUserName(resultSet.getString("user_name"));
        user.setPassword(resultSet.getString("password"));
        user.setPersonId(resultSet.getInt("person_id"));
        user.getPerson().setFirstName(resultSet.getString("first_name"));
        user.getPerson().setLastName(resultSet.getString("last_name"));
        user.getRole().setRoleId(resultSet.getInt("role_id"));
        user.getRole().setRoleName(resultSet.getString("role"));
        user.setRoleId(resultSet.getInt("role_id"));
        return user;
    }
}

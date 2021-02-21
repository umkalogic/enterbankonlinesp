package ua.svitl.enterbank.servletproject.model.dao;

import ua.svitl.enterbank.servletproject.model.dto.UserPersonDataDto;
import ua.svitl.enterbank.servletproject.model.entity.User;
import ua.svitl.enterbank.servletproject.utils.exception.DaoException;

import java.util.List;
import java.util.Optional;

public interface UserDao extends IDao<User, Integer> {
    Optional<User> findUserByUserNameAndPassword(String userName, String password) throws DaoException;
    boolean updateUserIsActive(int id, Boolean activeStatus) throws DaoException;
    List<UserPersonDataDto> getUserPersonData(int offset, int pageSize, String sortField, String sortDir) throws DaoException;
}

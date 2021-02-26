package ua.svitl.enterbank.servletproject.model.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.svitl.enterbank.servletproject.model.dao.AbstractDaoFactory;
import ua.svitl.enterbank.servletproject.model.dao.UserDao;
import ua.svitl.enterbank.servletproject.model.dto.UserDto;
import ua.svitl.enterbank.servletproject.model.dto.UserPersonDataDto;
import ua.svitl.enterbank.servletproject.model.entity.User;
import ua.svitl.enterbank.servletproject.utils.exception.ServiceException;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

public class UserService implements Serializable {
    private static final long serialVersionUID = 3465516945841743146L;

    private static final Logger LOG = LogManager.getLogger(UserService.class);

    private final AbstractDaoFactory daoFactory = AbstractDaoFactory.getInstance();

    public List<UserPersonDataDto> getAllUsers(int pageNo, int pageSize, String sortField, String sortDir)
            throws ServiceException {
        try (UserDao dao = daoFactory.createUserDao()) {
            LOG.debug("Start get user data paginated: offset={}, records={}, sortField={}, sortDir={}",
                    (pageNo - 1) * (pageSize - 1), pageSize, sortField, sortDir);
            return dao.getUserPersonData((pageNo - 1) * (pageSize - 1), pageSize, sortField, sortDir);
        } catch (Exception ex) {
            LOG.error("Couldn't get UserPersonData from UserService#getAllUsers(): {}", ex.getMessage());
            throw new ServiceException("Couldn't get user person data");
        }
    }

    public Optional<User> findByNameAndPassword(String userName, String password) throws ServiceException {
        try (UserDao dao = daoFactory.createUserDao()) {
            LOG.debug("Start find user by user_name and is_active ==> {}, {}", userName, password);
            return dao.findUserByUserNameAndPassword(userName, password);
        } catch (Exception ex) {
            LOG.error("Couldn't get find user by username and password: {} <== {}", userName, ex.getMessage());
            throw new ServiceException("Wrong username and/or password");
        }
    }

    public Optional<UserDto> getUserById(int id) throws ServiceException {
        try (UserDao dao = daoFactory.createUserDao()) {
            LOG.debug("Start get user by id={}", id);
            return dao.findUserById(id);
        } catch (Exception ex) {
            LOG.error("Couldn't get user by id from UserService#getUserById(): {}", ex.getMessage());
            throw new ServiceException("Couldn't find user");
        }
    }

    public boolean updateUser(User user) throws ServiceException {
        try (UserDao dao = daoFactory.createUserDao()) {
            LOG.debug("Start update user ==> {}", user);
            return dao.update(user);
        } catch (Exception ex) {
            LOG.error("Couldn't update user ==> user");
            throw new ServiceException("Couldn't update user with id = " + user.getUserId());
        }
    }

    public boolean updateUserActive(int id, boolean active) throws ServiceException {
        try (UserDao dao = daoFactory.createUserDao()) {
            LOG.debug("Start change user status ==> id={}", id);
            return dao.updateUserIsActive(id, active);
        } catch (Exception ex) {
            LOG.error("Couldn't update user ==> user");
            throw new ServiceException("Couldn't update user with id = " + id);
        }
    }
}

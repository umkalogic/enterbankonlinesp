package ua.svitl.enterbank.servletproject.model.dao.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.mindrot.jbcrypt.BCrypt;
import ua.svitl.enterbank.servletproject.model.dao.UserDao;
import ua.svitl.enterbank.servletproject.model.dao.mapper.UserMapper;
import ua.svitl.enterbank.servletproject.model.dao.mapper.UserPersonDataMapper;
import ua.svitl.enterbank.servletproject.model.dto.UserPersonDataDto;
import ua.svitl.enterbank.servletproject.model.entity.User;
import ua.svitl.enterbank.servletproject.utils.exception.DaoException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class JdbcUserDao implements UserDao {
    private static final Logger LOG = LogManager.getLogger(JdbcUserDao.class);
    private final Connection connection;
    private final DaoProperties daoProperties = DaoProperties.getInstance();

    public JdbcUserDao(Connection connection) {
        LOG.debug(" --> connection in {}", JdbcUserDao.class.getName());
        this.connection = connection;
    }

    @Override
    public Optional<User> findUserByUserNameAndPassword(String userName, String password) throws DaoException {
        LOG.debug("Start User findByUserName: userName ==> {}", userName);
        String query = daoProperties.getProperty("query.find.user.by.username.and.password");
        try (PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, userName);
            LOG.trace("QUERY: [ {} ], ?={}", query, userName);
            ResultSet rs = statement.executeQuery();
            UserMapper userMapper = new UserMapper();
            User user = new User();
            while (rs.next()) {
                user = userMapper.map(rs);
            }
            if (BCrypt.checkpw(password, user.getPassword())) {
                LOG.debug("End User findByUserName: userName ==> {}", userName);
                return Optional.of(user);
            } else {
                LOG.error("Wrong user password: {} <==> {}", password, user.getPassword());
                throw new DaoException("Wrong password");
            }
        } catch (SQLException ex) {
            LOG.error("Couldn't find user by username: {}", ex.getMessage());
            throw new DaoException("Couldn't find user by username and password");
        }
    }

    @Override
    public Optional<User> findById(Integer id) throws DaoException {
        LOG.debug("Start User findById: id ==> {}", id);
        String query = daoProperties.getProperty("query.find.user.by.user.id");
        try (PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            statement.setInt(1, id);
            LOG.trace("QUERY: [ {} ], ?={}", query, id);
            ResultSet rs = statement.executeQuery();
            UserMapper userMapper = new UserMapper();
            User user = new User();
            while (rs.next()) {
                user = userMapper.map(rs);
            }
            LOG.debug("End User findById: id ==> {}", id);
            return Optional.ofNullable(user);
        } catch (SQLException ex) {
            LOG.error("Couldn't find user by user id: {}", ex.getMessage());
            throw new DaoException("Couldn't find user");
        }
    }

    @Override
    public List<User> findAll() throws DaoException {
        LOG.debug("Start User findAll");
        String query = daoProperties.getProperty("query.user.find.all");
        try (PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            LOG.trace("QUERY: [ {} ]", query);
            ResultSet rs = statement.executeQuery();
            UserMapper userMapper = new UserMapper();
            List<User> userList = new ArrayList<>();
            while (rs.next()) {
                User user = userMapper.map(rs);
                userList.add(user);
            }
            LOG.debug("End User findAll: [[[ {} ]]]", userList);
            return userList;
        } catch (SQLException ex) {
            LOG.error("Couldn't find users: {}", ex.getMessage());
            throw new DaoException("Couldn't find users");
        }
    }

    @Override
    public boolean create(User user) throws DaoException {
        LOG.debug("Start User insert: [ {} ]", user);
        String query = daoProperties.getProperty("query.create.new.user");
        // mail, password, user_name, person_id, role_id
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, user.getEmail());
            statement.setString(2, user.getPassword());
            statement.setString(3, user.getUserName());
            statement.setInt(4, user.getPersonId());
            statement.setInt(5, user.getRoleId());
            LOG.trace("QUERY: [ {} ], VALUES ({}, {}, {}, {}, {})",
                    query, user.getEmail(), user.getPassword(), user.getUserName(),
                    user.getPersonId(), user.getRoleId());
            statement.executeUpdate();
            LOG.debug("End User create: [ {} ]", user);
            return true;
        } catch (SQLException e) {
            LOG.error("Couldn't create user in DB: {}", user);
            throw new DaoException("Couldn't create user in DB");
        }
    }

    @Override
    public boolean update(User user) throws DaoException {
        LOG.debug("Start User update: [ {} ]", user);
        String query = daoProperties.getProperty("query.update.user");
        // email, user_name, is_active
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, user.getEmail());
            statement.setString(2, user.getUserName());
            statement.setBoolean(3, user.getIsActive());
            LOG.trace("QUERY: [ {} ], VALUES ({}, {}, {})",
                    query, user.getEmail(), user.getUserName(), user.getIsActive());
            statement.executeUpdate();
            LOG.debug("End User update: [ {} ]", user);
            return true;
        } catch (SQLException e) {
            LOG.error("Couldn't update user in DB: {}", user);
            throw new DaoException("Couldn't update user in DB");
        }
    }

    @Override
    public boolean updateUserIsActive(int id, Boolean activeStatus) throws DaoException {
        LOG.debug("Start update User (id = {}): isActive ==> {}", id, activeStatus);
        String query = daoProperties.getProperty("query.update.user.is.active");
        // is_active
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setBoolean(1, activeStatus);
            statement.setInt(2, id);
            LOG.trace("QUERY: [ {} ], VALUES ({}, {})", query, activeStatus, id);
            statement.executeUpdate();
            LOG.debug("End update User is_active : {}", activeStatus);
            return true;
        } catch (SQLException e) {
            LOG.error("Couldn't update user in DB: id = {}", id);
            throw new DaoException("Couldn't update user in DB.");
        }
    }

    @Override
    public List<UserPersonDataDto> getUserPersonData(int offset, int pageSize, String sortField, String sortDir)
            throws DaoException {
        LOG.debug("Start get all users with role 'USER' personal data");
        String query = daoProperties.getProperty("query.find.data.for.all.users.with.user.role") +
                " ORDER BY " + sortField + " " + sortDir + " LIMIT " + pageSize + " OFFSET " + offset + ";";
        try (PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            List<UserPersonDataDto> userPersonDataList = new ArrayList<>();
            LOG.trace("QUERY: [ {} ]", query);
            ResultSet rs = statement.executeQuery();

            LOG.trace("Mapping UserPersonData...");
            while (rs.next()) {
                UserPersonDataMapper userPersonDataMapper = new UserPersonDataMapper();
                UserPersonDataDto userPersonDataDto = userPersonDataMapper.map(rs);
                userPersonDataList.add(userPersonDataDto);
            }
            LOG.debug("End get all users with role 'USER' personal data");
            return userPersonDataList;
        } catch (SQLException ex) {
            LOG.error("Couldn't find users with USER role in DB --> {}", ex.getMessage());
            throw new DaoException("Couldn't find users with USER role in DB");
        }
    }


    @Override
    public boolean delete(User user) throws DaoException {
        LOG.debug("Start User delete: [ {} ]", user);
        String query = daoProperties.getProperty("query.delete.from.users.by.id");
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, String.valueOf(user.getUserId()));
            LOG.trace("QUERY: [ {} ], ? = '{}'", query, user.getUserId());
            statement.executeUpdate();
            LOG.debug("End User delete: [ {} ]", user);
            return true;
        } catch (SQLException e) {
            LOG.error("Couldn't delete user from DB: {}", user);
            throw new DaoException("Couldn't delete user from DB");
        }
    }


    @Override
    public void close() throws Exception {
        try {
            LOG.debug("Closing connection... {}", JdbcUserDao.class.getName());
            connection.close();
        } catch (Exception ex) {
            LOG.error("{} --> {}", JdbcUserDao.class.getName(), ex.getMessage());
            throw new DaoException("Could not close DB connection");
        }
    }

    private void close(AutoCloseable res) {
        if (res != null) {
            try {
                res.close();
            } catch (Exception ex) {
                LOG.error("{} --> {}", JdbcUserDao.class.getName(), ex.getMessage());
            }
        }
    }
}

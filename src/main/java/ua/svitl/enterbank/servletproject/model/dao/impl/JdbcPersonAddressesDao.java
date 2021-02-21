package ua.svitl.enterbank.servletproject.model.dao.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.svitl.enterbank.servletproject.model.dao.PersonAddressesDao;
import ua.svitl.enterbank.servletproject.model.entity.PersonAddresses;
import ua.svitl.enterbank.servletproject.utils.exception.DaoException;

import java.sql.Connection;
import java.util.List;
import java.util.Optional;

public class JdbcPersonAddressesDao implements PersonAddressesDao {
    private static final Logger LOG = LogManager.getLogger(JdbcPersonAddressesDao.class);
    private Connection connection;

    public JdbcPersonAddressesDao(Connection connection) {
        LOG.debug(" --> connection in {}", JdbcPersonAddressesDao.class.getName());
        this.connection = connection;
    }

    @Override
    public void close() throws Exception {
        try {
            LOG.debug("Closing connection... {}", JdbcPersonAddressesDao.class.getName());
            connection.close();
        } catch (Exception ex) {
            LOG.error("{} --> {}", JdbcPersonAddressesDao.class.getName(), ex.getMessage());
            throw new DaoException("Could not close DB connection");
        }
    }


    @Override
    public Optional<PersonAddresses> findById(Integer id) throws DaoException {
        return Optional.empty();
    }

    @Override
    public List<PersonAddresses> findAll() throws DaoException {
        return null;
    }

    @Override
    public boolean create(PersonAddresses entity) throws DaoException {
        return false;
    }

    @Override
    public boolean update(PersonAddresses entity) throws DaoException {
        return false;
    }

    @Override
    public boolean delete(PersonAddresses entity) {
        return false;
    }
}

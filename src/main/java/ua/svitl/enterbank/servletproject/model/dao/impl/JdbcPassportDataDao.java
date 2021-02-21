package ua.svitl.enterbank.servletproject.model.dao.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.svitl.enterbank.servletproject.model.dao.PassportDataDao;
import ua.svitl.enterbank.servletproject.model.entity.PassportData;
import ua.svitl.enterbank.servletproject.utils.exception.DaoException;

import java.sql.Connection;
import java.util.List;
import java.util.Optional;

public class JdbcPassportDataDao implements PassportDataDao {
    private static final Logger LOG = LogManager.getLogger(JdbcPassportDataDao.class);
    private Connection connection;

    public JdbcPassportDataDao(Connection connection) {
        LOG.debug(" --> connection in {}", JdbcPassportDataDao.class.getName());
        this.connection = connection;
    }

    @Override
    public void close() throws Exception {
        try {
            LOG.debug("Closing connection... {}", JdbcPassportDataDao.class.getName());
            connection.close();
        } catch (Exception ex) {
            LOG.error("{} --> {}", JdbcPassportDataDao.class.getName(), ex.getMessage());
            throw new DaoException("Could not close DB connection");
        }
    }


    @Override
    public Optional<PassportData> findById(Integer id) throws DaoException {
        return Optional.empty();
    }

    @Override
    public List<PassportData> findAll() throws DaoException {
        return null;
    }

    @Override
    public boolean create(PassportData entity) throws DaoException {
        return false;
    }

    @Override
    public boolean update(PassportData entity) throws DaoException {
        return false;
    }

    @Override
    public boolean delete(PassportData entity) throws DaoException {
        return false;
    }
}

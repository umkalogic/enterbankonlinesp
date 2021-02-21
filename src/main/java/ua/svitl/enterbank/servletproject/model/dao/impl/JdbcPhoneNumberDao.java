package ua.svitl.enterbank.servletproject.model.dao.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.svitl.enterbank.servletproject.model.dao.PhoneNumberDao;
import ua.svitl.enterbank.servletproject.model.entity.PhoneNumber;
import ua.svitl.enterbank.servletproject.utils.exception.DaoException;

import java.sql.Connection;
import java.util.List;
import java.util.Optional;

public class JdbcPhoneNumberDao implements PhoneNumberDao {
    private static final Logger LOG = LogManager.getLogger(JdbcPhoneNumberDao.class);
    private final Connection connection;

    public JdbcPhoneNumberDao(Connection connection) {
        LOG.debug(" --> connection in {}", JdbcPhoneNumberDao.class.getName());
        this.connection = connection;
    }

    @Override
    public void close() throws Exception {
        try {
            LOG.debug("Closing connection... {}", JdbcPhoneNumberDao.class.getName());
            connection.close();
        } catch (Exception ex) {
            LOG.error("{} --> {}", JdbcPhoneNumberDao.class.getName(), ex.getMessage());
            throw new DaoException("Could not close DB connection");
        }
    }

    @Override
    public Optional<PhoneNumber> findById(Integer id) {
        return Optional.empty();
    }

    @Override
    public List<PhoneNumber> findAll() {
        return null;
    }

    @Override
    public boolean create(PhoneNumber entity) throws DaoException {
        return false;
    }

    @Override
    public boolean update(PhoneNumber entity) throws DaoException {
        return false;
    }


    @Override
    public boolean delete(PhoneNumber entity) {
        return false;
    }
}

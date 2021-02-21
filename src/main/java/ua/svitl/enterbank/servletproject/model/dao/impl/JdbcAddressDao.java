package ua.svitl.enterbank.servletproject.model.dao.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.svitl.enterbank.servletproject.model.dao.AddressDao;
import ua.svitl.enterbank.servletproject.model.entity.Address;
import ua.svitl.enterbank.servletproject.utils.exception.DaoException;

import java.sql.Connection;
import java.util.List;
import java.util.Optional;

public class JdbcAddressDao implements AddressDao {
    private static final Logger LOG = LogManager.getLogger(JdbcAddressDao.class);
    private final Connection connection;

    public JdbcAddressDao(Connection connection) {
        LOG.debug(" --> connection in {}", JdbcAddressDao.class.getName());
        this.connection = connection;
    }

    @Override
    public void close() throws Exception {
        try {
            LOG.debug("Closing connection... {}", JdbcAddressDao.class.getName());
            connection.close();
        } catch (Exception ex) {
            LOG.error("{} --> {}", JdbcAddressDao.class.getName(), ex.getMessage());
            throw new DaoException("Could not close DB connection");
        }
    }


    @Override
    public Optional<Address> findById(Integer id) throws DaoException {
        return Optional.empty();
    }

    @Override
    public List<Address> findAll() throws DaoException {
        return null;
    }

    @Override
    public boolean create(Address entity) throws DaoException {
        return false;
    }

    @Override
    public boolean update(Address entity) throws DaoException {
        return false;
    }

    @Override
    public boolean delete(Address entity) throws DaoException {
        return false;
    }
}

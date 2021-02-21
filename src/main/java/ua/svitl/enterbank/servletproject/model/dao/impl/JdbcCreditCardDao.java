package ua.svitl.enterbank.servletproject.model.dao.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.svitl.enterbank.servletproject.model.dao.CreditCardDao;
import ua.svitl.enterbank.servletproject.model.entity.CreditCard;
import ua.svitl.enterbank.servletproject.utils.exception.DaoException;

import java.sql.Connection;
import java.util.List;
import java.util.Optional;

public class JdbcCreditCardDao implements CreditCardDao {
    private static final Logger LOG = LogManager.getLogger(JdbcCreditCardDao.class);
    private final Connection connection;

    public JdbcCreditCardDao(Connection connection) {
        LOG.debug(" --> connection in {}", JdbcCreditCardDao.class.getName());
        this.connection = connection;
    }


    @Override
    public void close() throws Exception {
        try {
            LOG.debug("Closing connection... {}", JdbcCreditCardDao.class.getName());
            connection.close();
        } catch (Exception ex) {
            LOG.error("{} --> {}", JdbcCreditCardDao.class.getName(), ex.getMessage());
            throw new DaoException("Could not close DB connection");
        }
    }


    @Override
    public Optional<CreditCard> findById(Integer id) throws DaoException {
        return Optional.empty();
    }

    @Override
    public List<CreditCard> findAll() throws DaoException {
        return null;
    }

    @Override
    public boolean create(CreditCard entity) throws DaoException {
        return false;
    }

    @Override
    public boolean update(CreditCard entity) throws DaoException {
        return false;
    }

    @Override
    public boolean delete(CreditCard entity) throws DaoException {
        return false;
    }
}

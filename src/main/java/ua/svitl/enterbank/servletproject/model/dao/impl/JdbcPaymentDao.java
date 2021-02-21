package ua.svitl.enterbank.servletproject.model.dao.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.svitl.enterbank.servletproject.model.dao.PaymentDao;
import ua.svitl.enterbank.servletproject.model.entity.Payment;
import ua.svitl.enterbank.servletproject.utils.exception.DaoException;

import java.sql.Connection;
import java.util.List;
import java.util.Optional;

public class JdbcPaymentDao implements PaymentDao {
    private static final Logger LOG = LogManager.getLogger(JdbcPaymentDao.class);
    private Connection connection;

    public JdbcPaymentDao(Connection connection) {
        LOG.debug(" --> connection in {}", JdbcPaymentDao.class.getName());
        this.connection = connection;
    }

    @Override
    public void close() throws Exception {
        try {
            LOG.debug("Closing connection... {}", JdbcPaymentDao.class.getName());
            connection.close();
        } catch (Exception ex) {
            LOG.error("{} --> {}", JdbcPaymentDao.class.getName(), ex.getMessage());
            throw new DaoException("Could not close DB connection");
        }
    }

    @Override
    public Optional<Payment> findById(Integer id) throws DaoException {
        return Optional.empty();
    }

    @Override
    public List<Payment> findAll() throws DaoException {
        return null;
    }

    @Override
    public boolean create(Payment entity) throws DaoException {
        return false;
    }

    @Override
    public boolean update(Payment entity) throws DaoException {
        return false;
    }

    @Override
    public boolean delete(Payment entity) throws DaoException {
        return false;
    }
}

package ua.svitl.enterbank.servletproject.model.dao.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.svitl.enterbank.servletproject.model.dao.*;
import ua.svitl.enterbank.servletproject.model.entity.PassportData;
import ua.svitl.enterbank.servletproject.utils.exception.DaoException;

import javax.sql.DataSource;
import java.sql.Connection;

public class JdbcDaoFactory extends AbstractDaoFactory {
    private static final Logger LOG = LogManager.getLogger(JdbcDaoFactory.class);
    private static final String CREATING = "Creating {}";

    private final transient DataSource dataSource = ConnectionPoolHolder.getDataSource();

    private Connection getConnection() throws DaoException {
        try {
            LOG.debug("Establishing connection to the connection pool");
            return dataSource.getConnection();
        } catch (Exception ex) {
            LOG.error("Could not establish database connection --> {}", ex.getMessage());
            throw new DaoException("Could not establish database connection");
        }
    }

    @Override
    public UserDao createUserDao() throws DaoException {
        LOG.debug("Creating UserDao");
        return new JdbcUserDao(getConnection());
    }

    @Override
    public BankAccountDao createBankAccountDao() throws DaoException {
        LOG.debug(CREATING, BankAccountDao.class.getName());
        return new JdbcBankAccountDao(getConnection());
    }

    @Override
    public PaymentDao createPaymentDao() throws DaoException {
        LOG.debug(CREATING, PaymentDao.class.getName());
        return new JdbcPaymentDao(getConnection());
    }


}

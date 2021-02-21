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

    private final DataSource dataSource = ConnectionPoolHolder.getDataSource();

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
    public PersonDao createPersonDao() throws DaoException {
        LOG.debug(CREATING, PersonDao.class.getName());
        return new JdbcPersonDao(getConnection());
    }

    @Override
    public RoleDao createRoleDao() throws DaoException {
        LOG.debug(CREATING, RoleDao.class.getName());
        return new JdbcRoleDao(getConnection());
    }

    @Override
    public PhoneNumberDao createPhoneNumberDao() throws DaoException {
        LOG.debug(CREATING, PhoneNumberDao.class.getName());
        return new JdbcPhoneNumberDao(getConnection());
    }

    @Override
    public PassportDataDao createPassportDataDao() throws DaoException {
        LOG.debug(CREATING, PassportData.class.getName());
        return new JdbcPassportDataDao(getConnection());
    }

    @Override
    public BankAccountDao createBankAccountDao() throws DaoException {
        LOG.debug(CREATING, BankAccountDao.class.getName());
        return new JdbcBankAccountDao(getConnection());
    }

    @Override
    public CreditCardDao createCreditCardDao() throws DaoException {
        LOG.debug(CREATING, CreditCardDao.class.getName());
        return new JdbcCreditCardDao(getConnection());
    }

    @Override
    public PaymentDao createPaymentDao() throws DaoException {
        LOG.debug(CREATING, PaymentDao.class.getName());
        return new JdbcPaymentDao(getConnection());
    }

    @Override
    public AddressDao createAddressDao() throws DaoException {
        LOG.debug(CREATING, AddressDao.class.getName());
        return new JdbcAddressDao(getConnection());
    }

    @Override
    public UkraineRegionsDao createUkraineRegionsDao() throws DaoException {
        LOG.debug(CREATING, UkraineRegionsDao.class.getName());
        return new JdbcUkraineRegionsDao(getConnection());
    }

    @Override
    public PersonAddressesDao createPersonAddressesDao() throws DaoException {
        LOG.debug(CREATING, PersonAddressesDao.class.getName());
        return new JdbcPersonAddressesDao(getConnection());
    }
}

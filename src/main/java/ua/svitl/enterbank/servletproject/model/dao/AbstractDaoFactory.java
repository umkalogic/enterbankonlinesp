package ua.svitl.enterbank.servletproject.model.dao;

import ua.svitl.enterbank.servletproject.model.dao.impl.*;
import ua.svitl.enterbank.servletproject.utils.exception.DaoException;

import java.io.Serializable;

public abstract class AbstractDaoFactory implements Serializable {
    private static final long serialVersionUID = -8278488422467029968L;

    private static AbstractDaoFactory daoFactory;

    public abstract UserDao createUserDao() throws DaoException;
    public abstract PersonDao createPersonDao() throws DaoException;
    public abstract RoleDao createRoleDao() throws DaoException;
    public abstract PhoneNumberDao createPhoneNumberDao() throws DaoException;
    public abstract PassportDataDao createPassportDataDao() throws DaoException;
    public abstract BankAccountDao createBankAccountDao() throws DaoException;
    public abstract CreditCardDao createCreditCardDao() throws DaoException;
    public abstract PaymentDao createPaymentDao() throws DaoException;
    public abstract AddressDao createAddressDao() throws DaoException ;
    public abstract UkraineRegionsDao createUkraineRegionsDao() throws DaoException;

    public abstract PersonAddressesDao createPersonAddressesDao() throws DaoException;

    public static synchronized AbstractDaoFactory getInstance(){
        if (daoFactory == null) {
            daoFactory = new JdbcDaoFactory();
        }
        return daoFactory;
    }
}


package ua.svitl.enterbank.servletproject.model.dao;

import ua.svitl.enterbank.servletproject.model.dao.impl.*;
import ua.svitl.enterbank.servletproject.utils.exception.DaoException;

import java.io.Serializable;

public abstract class AbstractDaoFactory implements Serializable {
    private static final long serialVersionUID = -8278488422467029968L;

    private static AbstractDaoFactory daoFactory;

    public abstract UserDao createUserDao() throws DaoException;
    public abstract BankAccountDao createBankAccountDao() throws DaoException;
    public abstract PaymentDao createPaymentDao() throws DaoException;

    public static synchronized AbstractDaoFactory getInstance(){
        if (daoFactory == null) {
            daoFactory = new JdbcDaoFactory();
        }
        return daoFactory;
    }
}


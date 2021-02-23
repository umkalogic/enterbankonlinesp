package ua.svitl.enterbank.servletproject.model.dao;

import ua.svitl.enterbank.servletproject.model.entity.Payment;
import ua.svitl.enterbank.servletproject.model.entity.User;
import ua.svitl.enterbank.servletproject.utils.exception.DaoException;

import java.util.Optional;

public interface PaymentDao extends IDao<Payment, Integer> {
    Payment createPayment(User user, Payment payment, String bankAccountNumber) throws DaoException;
    boolean updatePayment(User user, Payment payment) throws DaoException;
}

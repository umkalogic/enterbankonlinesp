package ua.svitl.enterbank.servletproject.model.dao;

import ua.svitl.enterbank.servletproject.model.entity.Payment;
import ua.svitl.enterbank.servletproject.model.entity.User;
import ua.svitl.enterbank.servletproject.utils.exception.DaoException;

import java.util.List;

public interface PaymentDao extends IDao<Payment, Integer> {
    Payment createPayment(User user, Payment payment, String bankAccountNumber) throws DaoException;
    boolean updatePayment(User user, Payment payment) throws DaoException;
    List<Payment> findAllByUser(User user, int offset, int pageSize, String sortField, String sortDir) throws DaoException;
    boolean deletePaymentByIdForUser(User user, int id) throws DaoException;
}

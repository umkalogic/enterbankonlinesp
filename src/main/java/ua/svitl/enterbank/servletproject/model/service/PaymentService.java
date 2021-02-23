package ua.svitl.enterbank.servletproject.model.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.svitl.enterbank.servletproject.model.dao.AbstractDaoFactory;
import ua.svitl.enterbank.servletproject.model.dao.BankAccountDao;
import ua.svitl.enterbank.servletproject.model.dao.PaymentDao;
import ua.svitl.enterbank.servletproject.model.entity.Payment;
import ua.svitl.enterbank.servletproject.model.entity.User;
import ua.svitl.enterbank.servletproject.utils.exception.ServiceException;

import java.io.Serializable;
import java.util.Optional;

public class PaymentService implements Serializable {
    private static final long serialVersionUID = 941103133357495193L;

    private static final Logger LOG = LogManager.getLogger(PaymentService.class);

    private final AbstractDaoFactory daoFactory = AbstractDaoFactory.getInstance();

    public Payment createPayment(User user, Payment payment, String bankAccountNumber)
            throws ServiceException {
        LOG.debug("Try create new payment in DB for user: {}, payment={}, ba={}",
                user, payment, bankAccountNumber);

        try (PaymentDao dao = daoFactory.createPaymentDao()) {
            LOG.debug("Start create new payment in DB for user: {}, payment={}", user, payment);
            return dao.createPayment(user, payment, bankAccountNumber);
        } catch (Exception ex) {
            LOG.error("Couldn't create new payment in DB ==> {}", ex.getMessage());
            throw new ServiceException("Couldn't create payment in DB");
        }
    }

    public boolean updatePayment(User user, Payment payment) throws ServiceException {
        LOG.debug("Start update payment ==> [ {} ] ==> for user ==> [ {} ]", payment, user);
        try (PaymentDao dao = daoFactory.createPaymentDao()) {
            dao.updatePayment(user, payment);
        } catch (Exception ex) {
            LOG.error("Couldn't update payment in DB ==> {}", ex.getMessage());
            throw new ServiceException("Couldn't update payment in DB");
        }
        return false;
    }
}
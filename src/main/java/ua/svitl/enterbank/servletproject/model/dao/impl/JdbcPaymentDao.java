package ua.svitl.enterbank.servletproject.model.dao.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.svitl.enterbank.servletproject.model.dao.PaymentDao;
import ua.svitl.enterbank.servletproject.model.entity.Payment;
import ua.svitl.enterbank.servletproject.model.entity.User;
import ua.svitl.enterbank.servletproject.utils.exception.DaoException;

import java.sql.*;
import java.util.List;
import java.util.Optional;

public class JdbcPaymentDao implements PaymentDao {
    private static final Logger LOG = LogManager.getLogger(JdbcPaymentDao.class);
    private final Connection connection;
    private final DaoProperties daoProperties = DaoProperties.getInstance();


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
    public boolean create(Payment payment) throws DaoException {
        return false;
    }

    @Override
    public boolean update(Payment payment) throws DaoException {
        LOG.debug("Start payment update: [ {} ]", payment);
        String query = daoProperties.getProperty("query.update.payment");

        try (PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            LOG.trace("QUERY: [ {} ]", query);
            statement.setInt(1, payment.getPaymentId());
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                payment.setSent(rs.getBoolean("is_sent"));
                payment.setPaymentDate(rs.getTimestamp("payment_date").toLocalDateTime());
            }

            LOG.debug("End update: [ {} ]", payment);
            return true;
        } catch (SQLException e) {
            LOG.error("Couldn't update payment in DB: {}", payment);
            throw new DaoException("Couldn't update payment in DB");
        }
    }

    @Override
    public boolean delete(Payment entity) throws DaoException {
        return false;
    }

    @Override
    public Payment createPayment(User user, Payment payment, String bankAccountNumber) throws DaoException {
        LOG.debug("Start create payment");

//        INSERT INTO payment (payment_amount, to_bank_account, bank_account_id) \
//        VALUES (?, (SELECT bank_account_number FROM bank_accounts WHERE bank_account_number = ?), \
//        (SELECT bank_account_id FROM bank_accounts as ba \
//        WHERE ba.person_id = ? AND ba.bank_account_number = ? and ba.account_amount >= ? \
//        and not ba.bank_account_number = ? \
//  ) \
//)

        String query = daoProperties.getProperty("query.insert.payment.with.check.conditions");

        try (PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            statement.setDouble(1, payment.getPaymentAmount()); // payment_amount
            statement.setString(2, payment.getToBankAccount()); // to_bank_account
            statement.setInt(3, user.getPersonId()); // person_id
            statement.setString(4, bankAccountNumber); // bank_account_number =
            statement.setDouble(5, payment.getPaymentAmount()); // account_amount >= payment_amount
            statement.setString(6, payment.getToBankAccount());// bank_account_number != to_bank_account

            LOG.trace("QUERY: [ {} ]", query);
            LOG.trace("Statement: [[ {} ]]", statement);

            ResultSet rs = statement.executeQuery();
            LOG.trace("Mapping payment...");

            while (rs.next()) {
                payment.setPaymentId(rs.getInt("payment_id"));
                payment.setPaymentDate(rs.getTimestamp("payment_date").toLocalDateTime());
            }

            LOG.debug("End create payment");
            return payment;
        } catch (SQLException ex) {
            LOG.error("Couldn't create payment --> {}", ex.getMessage());
            throw new DaoException("Couldn't create payment in DB");
        }
    }

    @Override
    public boolean updatePayment(User user, Payment payment) throws DaoException {
        LOG.debug("Start update payment");

//        UPDATE payment as p, bank_accounts as ba, bank_accounts as b \
//        SET p.is_sent = true, p.payment_date = CURRENT_TIMESTAMP, \
//        ba.account_amount = ba.account_amount - ?, \
//        b.account_amount = b.account_amount + ? \
//        WHERE p.payment_id = ? \
//        AND ba.bank_account_id = ? and ba.account_amount >= ? and ba.is_active = true and ba.person_id = ? \
//        AND b.bank_account_number = ? and b.is_active = true

        String query = daoProperties.getProperty("query.update.payment.with.check.conditions");

        try (PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            statement.setDouble(1, payment.getPaymentAmount()); // payment_amount
            statement.setDouble(2, payment.getPaymentAmount()); // payment_amount
            statement.setInt(3, payment.getPaymentId()); // payment_id
            statement.setInt(4, payment.getBankAccountId()); // from bank_account_id
            statement.setDouble(5, payment.getPaymentAmount()); // account_amount >=
            statement.setInt(6, user.getPersonId()); // person_id
            statement.setString(7, payment.getToBankAccount()); // to bank_account_number

            LOG.trace("QUERY: [ {} ]", query);
            LOG.trace("Statement: [[ {} ]]", statement);

            connection.setSavepoint();
            connection.setAutoCommit(false);

            ResultSet rs = statement.executeQuery();

            connection.commit();

            LOG.trace("Mapping payment...");

            while (rs.next()) {
                payment.setPaymentId(rs.getInt("payment_id"));
                payment.setPaymentDate(rs.getTimestamp("payment_date").toLocalDateTime());
            }

            LOG.debug("End update payment");
            return true;
        } catch (SQLException ex) {
            try {
                connection.rollback();
            } catch (SQLException e) {
                LOG.error("Couldn't rollback --> {}", e.getMessage());
            }
            LOG.error("Couldn't confirm payment --> {}", ex.getMessage());
            throw new DaoException("Couldn't perform payment transacton");
        }
    }


}

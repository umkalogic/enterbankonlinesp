package ua.svitl.enterbank.servletproject.model.dao.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.svitl.enterbank.servletproject.model.dao.PaymentDao;
import ua.svitl.enterbank.servletproject.model.dao.mapper.PaymentMapper;
import ua.svitl.enterbank.servletproject.model.entity.Payment;
import ua.svitl.enterbank.servletproject.model.entity.User;
import ua.svitl.enterbank.servletproject.utils.exception.DaoException;

import java.sql.*;
import java.util.ArrayList;
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
    public void close() throws DaoException {
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
//        (SELECT ba.bank_account_id FROM bank_accounts as ba \
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

            statement.execute();

            ResultSet rs = statement.getGeneratedKeys();

            LOG.trace("Mapping payment...");
            while (rs.next()) {
                LOG.trace("Generated id ==> {}", rs.getInt(1));
                payment.setPaymentId(rs.getInt(1));
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
        LOG.debug("Start update existing payment for User ==> [ {} ] ==> Payment ==> [ {} ]", user, payment);

//        UPDATE payment as p, bank_accounts as ba, bank_accounts as b \
//        SET p.is_sent = true, p.payment_date = CURRENT_TIMESTAMP, \
//        ba.account_amount = ba.account_amount - ?, \
//        b.account_amount = b.account_amount + ? \
//        WHERE p.payment_id = ? \
//        AND ba.bank_account_id = ? and ba.account_amount >= ? and ba.is_active = true and ba.person_id = ? \
//        AND b.bank_account_number = ? and b.is_active = true

        String query = daoProperties.getProperty("query.update.payment.with.check.conditions");
        LOG.trace("QUERY: [ {} ]", query);

        try (PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            LOG.trace("payment.getPaymentAmount() = {}", payment.getPaymentAmount());
            statement.setDouble(1, payment.getPaymentAmount()); // payment_amount
            statement.setDouble(2, payment.getPaymentAmount()); // payment_amount
            LOG.trace("payment.getPaymentId() = {}", payment.getPaymentId());
            statement.setInt(3, payment.getPaymentId()); // payment_id
            LOG.trace("payment.getBankAccountId() = {}", payment.getBankAccountId());
            statement.setInt(4, payment.getBankAccountId()); // from bank_account_id
            LOG.trace("payment.getPaymentAmount() = {}",payment.getPaymentAmount());
            statement.setDouble(5, payment.getPaymentAmount()); // account_amount >=
            statement.setInt(6, user.getPersonId()); // person_id
            LOG.trace("payment.getToBankAccount() = {}", payment.getToBankAccount());
            statement.setString(7, payment.getToBankAccount()); // to bank_account_number

            LOG.trace("Statement: [[ {} ]]", statement);

            LOG.debug("End update payment");
            return statement.execute();
        } catch (SQLException ex) {
            LOG.error("Couldn't confirm payment --> {}", ex.getMessage());
            throw new DaoException("Couldn't perform payment transaction");
        }
    }

    @Override
    public List<Payment> findAllByUser(User user, int offset, int pageSize, String sortField, String sortDir)
            throws DaoException {

//        query.find.all.payments.for.given.user=SELECT * from payment \
//        INNER JOIN bank_accounts ba ON payment.bank_account_id = ba.bank_account_id \
//        WHERE ba.person_id = ?

        LOG.debug("Start get all payments for user ==> {}", user);
        LOG.debug("Pagination ==> pageSize={}, offset={}", pageSize, offset);
        LOG.debug("Sort: sortField={}, sortDir={}", sortField, sortDir);

        String query = daoProperties.getProperty("query.find.all.payments.for.given.user") +
               " ORDER BY " + sortField + " " + sortDir + " LIMIT " + pageSize + " OFFSET " + offset + ";";

        try (PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            statement.setInt(1, user.getPersonId());

            LOG.trace("QUERY: [ {} ]", query);
            LOG.trace("STATEMENT [[ {} ]]", statement);

            List<Payment> payments = new ArrayList<>();

            ResultSet rs = statement.executeQuery();

            LOG.trace("Mapping payments...");
            while (rs.next()) {
                LOG.trace("TIMESTAMP: {} ", rs.getTimestamp("payment_date"));
                PaymentMapper paymentMapper = new PaymentMapper();
                Payment payment = paymentMapper.map(rs);
                payments.add(payment);
            }

            LOG.debug("End get all payments for given user ==> [[ {} ]]", payments);
            return payments;
        } catch (SQLException ex) {
            LOG.error("Couldn't find user payments --> {}", ex.getMessage());
            throw new DaoException("Couldn't find user payments in DB");
        }

    }

    @Override
    public boolean deletePaymentByIdForUser(User user, int id) throws DaoException {
        LOG.debug("Start delete existing payment for User ==> [ {} ] ==> Payment id ==> [ {} ]", user, id);
        String query = daoProperties.getProperty("query.delete.payment.by.id.for.user");

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);
            statement.setInt(2, user.getPersonId());
            statement.execute();
            LOG.debug("End delete payment [{}] for given user ==> [ {} ]", id, user);
            return true;
        } catch (SQLException ex) {
            LOG.error("Couldn't delete payment by id={} for user ==> [{}]", id, user);
            throw new DaoException("Couldn't delete payment with id=" + id + " for given user in DB");
        }
    }


}

package ua.svitl.enterbank.servletproject.model.dao.mapper;

import ua.svitl.enterbank.servletproject.model.entity.Payment;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PaymentMapper implements ObjectMapper<Payment> {

    /**
     * Builds object of type T from the given query result.
     *
     * @param resultSet result set from the query
     * @return a built object
     * @throws SQLException if could not be completed
     */
    @Override
    public Payment map(ResultSet resultSet) throws SQLException {
        Payment payment = new Payment();
        payment.setPaymentId(resultSet.getInt("payment_id"));
        payment.setPaymentDate(resultSet.getTimestamp("payment_date").toLocalDateTime());
        payment.setPaymentAmount(resultSet.getDouble("payment_amount"));
        payment.setSent(resultSet.getBoolean("is_sent"));
        payment.setToBankAccount(resultSet.getString("to_bank_account"));
        payment.setBankAccountId(resultSet.getInt("bank_account_id"));
        payment.getBankAccount().setBankAccountId(resultSet.getInt("bank_account_id"));
        payment.getBankAccount().setAccountAmount(resultSet.getDouble("account_amount"));
        payment.getBankAccount().setAccountType(resultSet.getString("account_type"));
        payment.getBankAccount().setBankAccountNumber(resultSet.getString("bank_account_number"));
        payment.getBankAccount().setCurrency(resultSet.getString("currency"));
        payment.getBankAccount().setEnableRequest(resultSet.getBoolean("enable_request"));
        payment.getBankAccount().setActive(resultSet.getBoolean("is_active"));
        return payment;
    }
}

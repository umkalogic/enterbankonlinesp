package ua.svitl.enterbank.servletproject.model.dao.mapper;

import ua.svitl.enterbank.servletproject.model.dto.BankAccountDto;
import ua.svitl.enterbank.servletproject.model.dto.CreditCardDto;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class BankAccountDtoMapper implements ObjectMapper<BankAccountDto> {
    /**
     * Builds object of type T from the given query result.
     *
     * @param resultSet result set from the query
     * @return a built object
     * @throws SQLException if could not be completed
     */
    @Override
    public BankAccountDto map(ResultSet resultSet) throws SQLException {
        /*
        ba.bank_account_id, ba.account_amount, ba.account_type, ba.bank_account_number, \
        ba.currency, ba.enable_request, ba.is_active, ba.person_id, \
         */
        BankAccountDto ba = new BankAccountDto();
        ba.setBankAccountId(resultSet.getInt("bank_account_id"));
        ba.setAccountAmount(resultSet.getDouble("account_amount"));
        ba.setAccountType(resultSet.getString("account_type"));
        ba.setBankAccountNumber(resultSet.getString("bank_account_number"));
        ba.setCurrency(resultSet.getString("currency"));
        ba.setEnableRequest(resultSet.getBoolean("enable_request"));
        ba.setActive(resultSet.getBoolean("is_active"));
        ba.setPersonId(resultSet.getInt("person_id"));
        return ba;
    }

}

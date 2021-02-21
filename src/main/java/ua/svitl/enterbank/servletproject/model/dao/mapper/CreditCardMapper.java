package ua.svitl.enterbank.servletproject.model.dao.mapper;

import ua.svitl.enterbank.servletproject.model.dto.CreditCardDto;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CreditCardMapper implements ObjectMapper<CreditCardDto> {
    /**
     * Builds object of type T from the given query result.
     *
     * @param resultSet result set from the query
     * @return a built object
     * @throws SQLException if could not be completed
     */
    @Override
    public CreditCardDto map(ResultSet resultSet) throws SQLException {
        CreditCardDto creditCard = new CreditCardDto();
        // result set: cc.is_active, cc.credit_card_id, cc.card_name, cc.card_number, cc.expire_date, cc.is_active, cc.owner_name
        creditCard.setBankAccountId(resultSet.getInt("bank_account_id"));
        creditCard.setCreditCardId(resultSet.getInt("credit_card_id"));
        creditCard.setCardName(resultSet.getString("card_name"));
        creditCard.setCardNumber(resultSet.getLong("card_number"));
        creditCard.setExpireDate(resultSet.getTimestamp("expire_date").toLocalDateTime());
        creditCard.setActive(resultSet.getBoolean("is_active"));
        creditCard.setOwnerName(resultSet.getString("owner_name"));
        return creditCard;
    }
}

package ua.svitl.enterbank.servletproject.model.dao;

import ua.svitl.enterbank.servletproject.model.dto.BankAccountDto;
import ua.svitl.enterbank.servletproject.model.entity.BankAccount;
import ua.svitl.enterbank.servletproject.utils.exception.DaoException;

import java.util.List;

public interface BankAccountDao extends IDao<BankAccount, Integer> {
    List<BankAccountDto> getUserAccounts(String userName, String sortField, String sortDir) throws DaoException;
    List<BankAccountDto> getUserAccountsById(int id, String sortField, String sortDir) throws DaoException;
}

package ua.svitl.enterbank.servletproject.model.dao;

import ua.svitl.enterbank.servletproject.model.dto.BankAccountDto;
import ua.svitl.enterbank.servletproject.model.entity.BankAccount;
import ua.svitl.enterbank.servletproject.model.entity.User;
import ua.svitl.enterbank.servletproject.utils.exception.DaoException;

import java.util.List;

public interface BankAccountDao extends IDao<BankAccount, Integer> {
    List<BankAccountDto> getUserAccounts(User user, String sortField, String sortDir) throws DaoException;
    List<BankAccountDto> getUserAccountsById(int id, String sortField, String sortDir) throws DaoException;
    boolean exists(BankAccount bankAccount) throws DaoException;
    boolean isActive(BankAccount bankAccount) throws DaoException;
    boolean updateAccountIsActive(int id, boolean status) throws DaoException;
    boolean updateAccountIsActive(User user, int id, boolean status) throws DaoException;
    boolean updateAccountEnableRequest(User user, int id) throws DaoException;
}

package ua.svitl.enterbank.servletproject.model.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.svitl.enterbank.servletproject.model.dao.AbstractDaoFactory;
import ua.svitl.enterbank.servletproject.model.dao.BankAccountDao;
import ua.svitl.enterbank.servletproject.model.dao.mapper.BankAccountDtoToBankAccountMapper;
import ua.svitl.enterbank.servletproject.model.dto.BankAccountDto;
import ua.svitl.enterbank.servletproject.model.entity.BankAccount;
import ua.svitl.enterbank.servletproject.utils.exception.ServiceException;

import java.io.Serializable;
import java.util.List;

public class BankAccountService implements Serializable {
    private static final long serialVersionUID = -4904694826944986914L;

    private static final Logger LOG = LogManager.getLogger(BankAccountService.class);

    private final AbstractDaoFactory daoFactory = AbstractDaoFactory.getInstance();

    public List<BankAccountDto> getUserAccounts(String userName, String sortField, String sortDir)
            throws ServiceException {
        try (BankAccountDao dao = daoFactory.createBankAccountDao()) {
            LOG.debug("Start get account: sortField={}, sortDir={}", sortField, sortDir);
            return dao.getUserAccounts(userName, sortField, sortDir);
        } catch (Exception ex) {
            LOG.error("Couldn't get BankAccount from BankAccountService#getUserAccounts(): {}", ex.getMessage());
            throw new ServiceException("Couldn't get user accounts", ex);
        }

    }

    public List<BankAccountDto> getUserAccountsById(int id, String sortField, String sortDir) throws ServiceException {
        try (BankAccountDao dao = daoFactory.createBankAccountDao()) {
            LOG.debug("Start get account data paginated: sortField={}, sortDir={}", sortField, sortDir);
            return dao.getUserAccountsById(id, sortField, sortDir);
        } catch (Exception ex) {
            LOG.error("Couldn't get BankAccount from BankAccountService#getUserAccountsById(): {}", ex.getMessage());
            throw new ServiceException("Couldn't get user accounts", ex);
        }
    }

    public static BankAccount mapToBankAccount(BankAccountDto bankAccountDto) {
        return BankAccountDtoToBankAccountMapper.mapToBankAccount(bankAccountDto);
    }

    public boolean updateAccountActive(int id, boolean status) throws ServiceException {
        try (BankAccountDao dao = daoFactory.createBankAccountDao()) {
            LOG.debug("Start change user status ==> id={}", id);
            return dao.updateAccountIsActive(id, status);
        } catch (Exception ex) {
            LOG.error("Couldn't update bank account with id = {}", id);
            throw new ServiceException("Couldn't update bank account with id = " + id);
        }
    }
}

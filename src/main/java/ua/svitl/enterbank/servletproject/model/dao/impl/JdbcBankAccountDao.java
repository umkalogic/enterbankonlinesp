package ua.svitl.enterbank.servletproject.model.dao.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.svitl.enterbank.servletproject.model.dao.BankAccountDao;
import ua.svitl.enterbank.servletproject.model.dao.mapper.BankAccountDtoMapper;
import ua.svitl.enterbank.servletproject.model.dao.mapper.CreditCardMapper;
import ua.svitl.enterbank.servletproject.model.dto.BankAccountDto;
import ua.svitl.enterbank.servletproject.model.dto.CreditCardDto;
import ua.svitl.enterbank.servletproject.model.entity.BankAccount;
import ua.svitl.enterbank.servletproject.utils.exception.DaoException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class JdbcBankAccountDao implements BankAccountDao {
    private static final Logger LOG = LogManager.getLogger(JdbcBankAccountDao.class);
    private final Connection connection;
    private final DaoProperties daoProperties = DaoProperties.getInstance();

    public JdbcBankAccountDao(Connection connection) {
        LOG.debug(" --> connection in {}", JdbcBankAccountDao.class.getName());
        this.connection = connection;
    }

    @Override
    public void close() throws Exception {
        try {
            LOG.debug("Closing connection... {}", JdbcBankAccountDao.class.getName());
            connection.close();
        } catch (Exception ex) {
            LOG.error("{} --> {}", JdbcBankAccountDao.class.getName(), ex.getMessage());
            throw new DaoException("Could not close DB connection");
        }
    }

    @Override
    public Optional<BankAccount> findById(Integer id) throws DaoException {
        return Optional.empty();
    }

    @Override
    public List<BankAccount> findAll() throws DaoException {
        return null;
    }

    @Override
    public boolean create(BankAccount entity) throws DaoException {
        return false;
    }

    @Override
    public boolean update(BankAccount entity) throws DaoException {
        return false;
    }

    @Override
    public boolean delete(BankAccount entity) throws DaoException {
        return false;
    }

    @Override
    public List<BankAccountDto> getUserAccounts(String userName, String sortField, String sortDir)
            throws DaoException {
        LOG.debug("Start get all user bank accounts");
        String query = daoProperties.getProperty("query.find.bank.accounts.for.user") +
                " ORDER BY " + sortField + " " + sortDir + ";";
        try (PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, userName);
            List<BankAccountDto> bankAccountDtoList = new ArrayList<>();
            List<CreditCardDto> cards = new ArrayList<>();
            LOG.trace("QUERY: [ {} ]", query);
            ResultSet rs = statement.executeQuery();

            LOG.trace("Mapping bank accounts...");
            while (rs.next()) {
                BankAccountDtoMapper bankAccountDtoMapper = new BankAccountDtoMapper();
                BankAccountDto bankAccountDto = bankAccountDtoMapper.map(rs);
                bankAccountDtoList.add(bankAccountDto);

                CreditCardMapper creditCardMapper = new CreditCardMapper();
                CreditCardDto creditCardDto = creditCardMapper.map(rs);
                cards.add(creditCardDto);
            }

            List<BankAccountDto> finalBankAccountDtoList1 = bankAccountDtoList;
            List<CreditCardDto> cc = cards.stream()
                    .filter(e -> e.getBankAccountId().equals(finalBankAccountDtoList1.get(0).getBankAccountId()))
                    .collect(Collectors.toList());
            bankAccountDtoList.get(0).setBankAccountCreditCards(cc);

            for (int i = 1; i < bankAccountDtoList.size(); i++) {
                if (!bankAccountDtoList.get(i).getBankAccountId().equals(bankAccountDtoList.get(i - 1).getBankAccountId())) {
                    int finalI = i;
                    List<BankAccountDto> finalBankAccountDtoList = bankAccountDtoList;
                    List<CreditCardDto> c = cards.stream()
                            .filter(e -> e.getBankAccountId().equals(finalBankAccountDtoList.get(finalI).getBankAccountId()))
                            .collect(Collectors.toList());
                    bankAccountDtoList.get(i).setBankAccountCreditCards(c);
                    LOG.trace("Bank account: {}", bankAccountDtoList.get(i));
                }
            }

            bankAccountDtoList = bankAccountDtoList.stream()
                    .filter(e -> !e.getBankAccountCreditCards().isEmpty())
                    .collect(Collectors.toList());

            LOG.debug("End get accounts for user with username={}", userName);
            return bankAccountDtoList;
        } catch (SQLException ex) {
            LOG.error("Couldn't find accounts for user --> {}", ex.getMessage());
            throw new DaoException("Couldn't find accounts for the given user in DB");
        }
    }

    @Override
    public List<BankAccountDto> getUserAccountsById(int id, String sortField, String sortDir) throws DaoException {
        LOG.debug("Start get all user bank accounts");
        String query = daoProperties.getProperty("query.find.bank.accounts.for.user.id") +
                " ORDER BY " + sortField + " " + sortDir + ";";
        try (PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            statement.setInt(1, id);
            List<BankAccountDto> bankAccountDtoList = new ArrayList<>();
            List<CreditCardDto> cards = new ArrayList<>();
            LOG.trace("QUERY: [ {} ]", query);
            ResultSet rs = statement.executeQuery();

            LOG.trace("Mapping bank accounts...");
            while (rs.next()) {
                BankAccountDtoMapper bankAccountDtoMapper = new BankAccountDtoMapper();
                BankAccountDto bankAccountDto = bankAccountDtoMapper.map(rs);
                bankAccountDtoList.add(bankAccountDto);

                CreditCardMapper creditCardMapper = new CreditCardMapper();
                CreditCardDto creditCardDto = creditCardMapper.map(rs);
                cards.add(creditCardDto);
            }

            for (BankAccountDto ba: bankAccountDtoList) {
                List<CreditCardDto> cc = cards.stream()
                        .filter(e -> e.getBankAccountId().equals(ba.getBankAccountId()))
                        .collect(Collectors.toList());
                ba.setBankAccountCreditCards(cc);
                LOG.trace("Bank account: {}", ba);
            }

            LOG.debug("End get accounts for user with id={}", id);
            return bankAccountDtoList;
        } catch (SQLException ex) {
            LOG.error("Couldn't find accounts for user --> {}", ex.getMessage());
            throw new DaoException("Couldn't find accounts for the given user in DB");
        }
    }
}

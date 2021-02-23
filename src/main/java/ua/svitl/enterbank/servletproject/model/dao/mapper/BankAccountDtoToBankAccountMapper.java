package ua.svitl.enterbank.servletproject.model.dao.mapper;

import ua.svitl.enterbank.servletproject.model.dto.BankAccountDto;
import ua.svitl.enterbank.servletproject.model.entity.BankAccount;

public class BankAccountDtoToBankAccountMapper {
    public static BankAccount mapToBankAccount(BankAccountDto bankAccountDto) {
        BankAccount bankAccount = new BankAccount();
        bankAccount.setBankAccountId(bankAccountDto.getBankAccountId());
        bankAccount.setAccountAmount(bankAccountDto.getAccountAmount());
        bankAccount.setAccountType(bankAccountDto.getAccountType());
        bankAccount.setBankAccountNumber(bankAccountDto.getBankAccountNumber());
        bankAccount.setCurrency(bankAccountDto.getCurrency());
        bankAccount.setEnableRequest(bankAccountDto.getEnableRequest());
        bankAccount.setActive(bankAccountDto.getActive());
        bankAccount.setPersonId(bankAccountDto.getPersonId());
        return bankAccount;
    }

    private BankAccountDtoToBankAccountMapper() {}
}

package ua.svitl.enterbank.servletproject.model.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class BankAccountDto implements Serializable {
    private static final long serialVersionUID = 8739311001577570178L;

    private Integer bankAccountId;
    private Double accountAmount;
    private String accountType;
    private String bankAccountNumber;
    private String currency;
    private Boolean enableRequest;
    private Boolean active;
    private Integer personId;
    private List<CreditCardDto> bankAccountCreditCards = new ArrayList<>();

    public BankAccountDto() {
    }

    public BankAccountDto(Integer bankAccountId, Double accountAmount, String accountType, String bankAccountNumber,
                          String currency, Boolean enableRequest, Boolean active, Integer personId,
                          List<CreditCardDto> bankAccountCreditCards) {
        this.bankAccountId = bankAccountId;
        this.accountAmount = accountAmount;
        this.accountType = accountType;
        this.bankAccountNumber = bankAccountNumber;
        this.currency = currency;
        this.enableRequest = enableRequest;
        this.active = active;
        this.personId = personId;
        this.bankAccountCreditCards = bankAccountCreditCards;
    }

    public Integer getBankAccountId() {
        return bankAccountId;
    }

    public void setBankAccountId(Integer bankAccountId) {
        this.bankAccountId = bankAccountId;
    }

    public Double getAccountAmount() {
        return accountAmount;
    }

    public void setAccountAmount(Double accountAmount) {
        this.accountAmount = accountAmount;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public String getBankAccountNumber() {
        return bankAccountNumber;
    }

    public void setBankAccountNumber(String bankAccountNumber) {
        this.bankAccountNumber = bankAccountNumber;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public Boolean getEnableRequest() {
        return enableRequest;
    }

    public void setEnableRequest(Boolean enableRequest) {
        this.enableRequest = enableRequest;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public Integer getPersonId() {
        return personId;
    }

    public void setPersonId(Integer personId) {
        this.personId = personId;
    }

    public List<CreditCardDto> getBankAccountCreditCards() {
        return bankAccountCreditCards;
    }

    public void setBankAccountCreditCards(List<CreditCardDto> bankAccountCreditCards) {
        this.bankAccountCreditCards = bankAccountCreditCards;
    }

    @Override
    public String toString() {
        return "BankAccountDto{" +
                "bankAccountId=" + bankAccountId +
                ", accountAmount=" + accountAmount +
                ", accountType='" + accountType + '\'' +
                ", bankAccountNumber='" + bankAccountNumber + '\'' +
                ", currency='" + currency + '\'' +
                ", enableRequest=" + enableRequest +
                ", active=" + active +
                ", personId=" + personId +
                ", bankAccountCreditCards=" + bankAccountCreditCards +
                '}';
    }
}

package ua.svitl.enterbank.servletproject.model.entity;

import ua.svitl.enterbank.servletproject.model.entity.constants.EntityConstants;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class BankAccount implements Serializable {

    private static final long serialVersionUID = -7644187500483904647L;

    private Integer bankAccountId;
    private Double accountAmount = 0.00D;
    private String accountType = EntityConstants.DEFAULT_BANK_ACCOUNT_TYPE;
    private String bankAccountNumber = EntityConstants.DEFAULT_BANK_ACCOUNT_NUMBER;
    private String currency = EntityConstants.DEFAULT_CURRENCY;
    private Boolean enableRequest = Boolean.FALSE;
    private Boolean active = Boolean.TRUE;
    // private Integer personId; // column
    private List<Payment> bankAccountPayments = new ArrayList<>();
    private List<CreditCard> bankAccountCreditCards = new ArrayList<>();
    private Person person = new Person();

    public BankAccount() {
    }

    public BankAccount(Integer bankAccountId, Double accountAmount, String accountType, String bankAccountNumber,
                       String currency, Boolean enableRequest, Boolean active, Person person) {
        this.bankAccountId = bankAccountId;
        this.accountAmount = accountAmount;
        this.accountType = accountType;
        this.bankAccountNumber = bankAccountNumber;
        this.currency = currency;
        this.enableRequest = enableRequest;
        this.active = active;
        this.person = person;
    }

    public BankAccount(Double accountAmount, String accountType, String bankAccountNumber,
                       String currency, Boolean enableRequest, Boolean active, Person person) {
        this.accountAmount = accountAmount;
        this.accountType = accountType;
        this.bankAccountNumber = bankAccountNumber;
        this.currency = currency;
        this.enableRequest = enableRequest;
        this.active = active;
        this.person = person;
    }

    public void setBankAccountId(Integer bankAccountId) {
        this.bankAccountId = bankAccountId;
    }

    public Integer getBankAccountId() {
        return bankAccountId;
    }

    public void setAccountAmount(Double accountAmount) {
        this.accountAmount = accountAmount;
    }

    public Double getAccountAmount() {
        return accountAmount;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setBankAccountNumber(String bankAccountNumber) {
        this.bankAccountNumber = bankAccountNumber;
    }

    public String getBankAccountNumber() {
        return bankAccountNumber;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getCurrency() {
        return currency;
    }

    public void setEnableRequest(Boolean enableRequest) {
        this.enableRequest = enableRequest;
    }

    public Boolean getEnableRequest() {
        return enableRequest;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public Boolean isActive() {
        return active;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public Person getPerson() {
        return person;
    }

    public List<Payment> getBankAccountPayments() {
        return bankAccountPayments;
    }

    public void setBankAccountPayments(List<Payment> bankAccountPayments) {
        this.bankAccountPayments = bankAccountPayments;
    }

    public void setBankAccountPayment(Payment bankAccountPayment) {
        bankAccountPayments.add(bankAccountPayment);
    }

    public List<CreditCard> getBankAccountCreditCards() {
        return bankAccountCreditCards;
    }

    public void setBankAccountCreditCards(List<CreditCard> bankAccountCreditCards) {
        this.bankAccountCreditCards = bankAccountCreditCards;
    }

    public void setBankAccountCreditCard(CreditCard bankAccountCreditCard) {
        bankAccountCreditCards.add(bankAccountCreditCard);
    }

    @Override
    public String toString() {
        return "BankAccounts{" +
                "bankAccountId=" + bankAccountId + '\'' +
                "bankAccountNumber=" + bankAccountNumber + '\'' +
                "accountType=" + accountType + '\'' +
                "accountAmount=" + accountAmount + '\'' +
                "currency=" + currency + '\'' +
                "enableRequest=" + enableRequest + '\'' +
                "active=" + active + '\'' +
                "person tax code=" + person.getIdNumberTaxCode() + '\'' +
                '}';
    }

    /**
     * The bank account ID is unique for each BankAccount. So this should compare BankAccount by ID only.
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object other) {
        return (other instanceof BankAccount) && (bankAccountId != null)
                ? bankAccountId.equals(((BankAccount) other).bankAccountId)
                : (other == this);
    }

    /**
     * The bank account ID is unique for each BankAccount. So BankAccount with same ID should return same hashcode.
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        return (bankAccountId != null)
                ? (this.getClass().hashCode() + bankAccountId.hashCode())
                : super.hashCode();
    }
}

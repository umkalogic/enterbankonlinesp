package ua.svitl.enterbank.servletproject.model.entity;

import ua.svitl.enterbank.servletproject.model.entity.constants.EntityConstants;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class CreditCard implements Serializable {

    private static final long serialVersionUID = 79493619125553986L;

    private Integer creditCardId;
    private String cardName = EntityConstants.DEFAULT_CREDIT_CARD_NAME;
    private Long cardNumber;
    private String cvc2;
    private LocalDateTime expireDate = LocalDateTime.parse("2025-01-31T00:00:00");
    private Boolean active = Boolean.TRUE;
    private LocalDateTime issueDate = LocalDateTime.parse("2020-01-05T00:00:00");
    private String ownerName;
    private Integer bankAccountId;
    private BankAccount bankAccount = new BankAccount();

    public CreditCard() {
    }

    public CreditCard(Integer creditCardId, String cardName, Long cardNumber, String cvc2, LocalDateTime expireDate,
                      Boolean active, LocalDateTime issueDate, String ownerName, BankAccount bankAccount) {
        this.creditCardId = creditCardId;
        this.cardName = cardName;
        this.cardNumber = cardNumber;
        this.cvc2 = cvc2;
        this.expireDate = LocalDateTime.parse(expireDate.toString(),
                DateTimeFormatter.ofPattern(EntityConstants.FORMAT_DATE_TIME));
        this.active = active;
        this.issueDate = LocalDateTime.parse(issueDate.toString(),
                DateTimeFormatter.ofPattern(EntityConstants.FORMAT_DATE_TIME));
        this.ownerName = ownerName;
        this.bankAccount = bankAccount;
    }

    public CreditCard(String cardName, Long cardNumber, String cvc2, LocalDateTime expireDate,
                      Boolean active, LocalDateTime issueDate, String ownerName, BankAccount bankAccount) {
        this.cardName = cardName;
        this.cardNumber = cardNumber;
        this.cvc2 = cvc2;
        this.expireDate = LocalDateTime.parse(expireDate.toString(),
                DateTimeFormatter.ofPattern(EntityConstants.FORMAT_DATE_TIME));
        this.active = active;
        this.issueDate = LocalDateTime.parse(issueDate.toString(),
                DateTimeFormatter.ofPattern(EntityConstants.FORMAT_DATE_TIME));
        this.ownerName = ownerName;
        this.bankAccount = bankAccount;
    }

    public CreditCard(Integer creditCardId, String cardName, Long cardNumber, String cvc2,
                      String ownerName, BankAccount bankAccount) {
        this.creditCardId = creditCardId;
        this.cardName = cardName;
        this.cardNumber = cardNumber;
        this.cvc2 = cvc2;
        this.ownerName = ownerName;
        this.bankAccount = bankAccount;
    }

    public CreditCard(String cardName, Long cardNumber, String cvc2,
                      String ownerName, BankAccount bankAccount) {
        this.cardName = cardName;
        this.cardNumber = cardNumber;
        this.cvc2 = cvc2;
        this.ownerName = ownerName;
        this.bankAccount = bankAccount;
    }

    public Integer getBankAccountId() {
        return bankAccountId;
    }

    public void setBankAccountId(Integer bankAccountId) {
        this.bankAccountId = bankAccountId;
    }

    public void setCreditCardId(Integer creditCardId) {
        this.creditCardId = creditCardId;
    }

    public Integer getCreditCardId() {
        return creditCardId;
    }

    public void setCardName(String cardName) {
        this.cardName = cardName;
    }

    public String getCardName() {
        return cardName;
    }

    public void setCardNumber(Long cardNumber) {
        this.cardNumber = cardNumber;
    }

    public Long getCardNumber() {
        return cardNumber;
    }

    public void setCvc2(String cvc2) {
        this.cvc2 = cvc2;
    }

    public String getCvc2() {
        return cvc2;
    }

    public void setExpireDate(LocalDateTime expireDate) {
        this.expireDate = LocalDateTime.parse(expireDate.toString(),
                DateTimeFormatter.ofPattern(EntityConstants.FORMAT_DATE_TIME));
    }

    public LocalDateTime getExpireDate() {
        return expireDate;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public Boolean isActive() {
        return active;
    }

    public void setIssueDate(LocalDateTime issueDate) {
        this.issueDate = LocalDateTime.parse(issueDate.toString(),
                DateTimeFormatter.ofPattern(EntityConstants.FORMAT_DATE_TIME));
    }

    public LocalDateTime getIssueDate() {
        return issueDate;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setBankAccount(BankAccount bankAccount) {
        this.bankAccount = bankAccount;
    }

    public BankAccount getBankAccount() {
        return bankAccount;
    }

    @Override
    public String toString() {
        return "CreditCard{" +
                "creditCardId=" + creditCardId + '\'' +
                "cardName=" + cardName + '\'' +
                "cardNumber=" + cardNumber + '\'' +
                "issueDate=" + issueDate + '\'' +
                "expireDate=" + expireDate + '\'' +
                "active=" + active + '\'' +
                "ownerName=" + ownerName + '\'' +
                "bank account number" + bankAccount.getBankAccountNumber() + '\'' +
                '}';
    }
    /**
     * The credit card ID is unique for each CreditCard. So this should compare CreditCard by ID only.
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object other) {
        return (other instanceof CreditCard) && (creditCardId != null)
                ? creditCardId.equals(((CreditCard) other).creditCardId)
                : (other == this);
    }

    /**
     * The credit card ID is unique for each CreditCard. So CreditCard with same ID should return same hashcode.
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        return (creditCardId != null)
                ? (this.getClass().hashCode() + creditCardId.hashCode())
                : super.hashCode();
    }
}

package ua.svitl.enterbank.servletproject.model.dto;

import ua.svitl.enterbank.servletproject.model.entity.BankAccount;
import ua.svitl.enterbank.servletproject.model.entity.constants.EntityConstants;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class CreditCardDto implements Serializable {

    private static final long serialVersionUID = -6197685312954349518L;

    private Integer creditCardId;
    private String cardName;
    private Long cardNumber;
    private LocalDateTime expireDate;
    private Boolean active;
    private String ownerName;
    private Integer bankAccountId;

    public CreditCardDto() {
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

    public void setExpireDate(LocalDateTime expireDate) {
        this.expireDate = expireDate;
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


    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public String getOwnerName() {
        return ownerName;
    }

    @Override
    public String toString() {
        return "CreditCard{" +
                "creditCardId=" + creditCardId + '\'' +
                "cardName=" + cardName + '\'' +
                "cardNumber=" + cardNumber + '\'' +
                "expireDate=" + expireDate + '\'' +
                "active=" + active + '\'' +
                "ownerName=" + ownerName + '\'' +
                "bank account id" + bankAccountId + '\'' +
                '}';
    }
    /**
     * The credit card ID is unique for each CreditCard. So this should compare CreditCard by ID only.
     * @see Object#equals(Object)
     */
    @Override
    public boolean equals(Object other) {
        return (other instanceof CreditCardDto) && (creditCardId != null)
                ? creditCardId.equals(((CreditCardDto) other).creditCardId)
                : (other == this);
    }

    /**
     * The credit card ID is unique for each CreditCard. So CreditCard with same ID should return same hashcode.
     * @see Object#hashCode()
     */
    @Override
    public int hashCode() {
        return (creditCardId != null)
                ? (this.getClass().hashCode() + creditCardId.hashCode())
                : super.hashCode();
    }
}

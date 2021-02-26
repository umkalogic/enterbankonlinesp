package ua.svitl.enterbank.servletproject.model.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

public class Payment implements Serializable {

    private static final long serialVersionUID = 579377061195694855L;

    private Integer paymentId;
    private Boolean sent = Boolean.FALSE;
    private Double paymentAmount;
    private LocalDateTime paymentDate = LocalDateTime.now();
    private String toBankAccount;
    private Integer bankAccountId; // column
    private BankAccount bankAccount = new BankAccount();

    public Integer getBankAccountId() {
        return bankAccountId;
    }

    public void setBankAccountId(Integer bankAccountId) {
        this.bankAccountId = bankAccountId;
    }

    public Payment() {
    }

    public Payment(Integer paymentId, Boolean sent, Double paymentAmount,
                   String toBankAccount, BankAccount bankAccount) {
        this.paymentId = paymentId;
        this.sent = sent;
        this.paymentAmount = paymentAmount;
        this.toBankAccount = toBankAccount;
        this.bankAccount = bankAccount;
    }


    public Payment(Integer paymentId, Boolean sent, Double paymentAmount, LocalDateTime paymentDate,
                   String toBankAccount, BankAccount bankAccount) {
        this.paymentId = paymentId;
        this.sent = sent;
        this.paymentAmount = paymentAmount;
        this.paymentDate = paymentDate;
        this.toBankAccount = toBankAccount;
        this.bankAccount = bankAccount;
    }

    public Payment(Boolean sent, Double paymentAmount, LocalDateTime paymentDate,
                   String toBankAccount, BankAccount bankAccount) {
        this.sent = sent;
        this.paymentAmount = paymentAmount;
        this.paymentDate = paymentDate;
        this.toBankAccount = toBankAccount;
        this.bankAccount = bankAccount;
    }

    public Payment(Boolean sent, Double paymentAmount,
                   String toBankAccount, BankAccount bankAccount) {
        this.sent = sent;
        this.paymentAmount = paymentAmount;
        this.toBankAccount = toBankAccount;
        this.bankAccount = bankAccount;
    }

    public void setPaymentId(Integer paymentId) {
        this.paymentId = paymentId;
    }

    public Integer getPaymentId() {
        return paymentId;
    }

    public void setSent(Boolean sent) {
        this.sent = sent;
    }

    public Boolean getSent() {
        return sent;
    }

    public void setPaymentAmount(Double paymentAmount) {
        this.paymentAmount = paymentAmount;
    }

    public Double getPaymentAmount() {
        return paymentAmount;
    }

    public void setPaymentDate(LocalDateTime paymentDate) {
        this.paymentDate = paymentDate;
    }

    public LocalDateTime getPaymentDate() {
        return paymentDate;
    }

    public void setToBankAccount(String toBankAccount) {
        this.toBankAccount = toBankAccount;
    }

    public String getToBankAccount() {
        return toBankAccount;
    }

    public void setBankAccount(BankAccount bankAccount) {
        this.bankAccount = bankAccount;
    }

    public BankAccount getBankAccount() {
        return bankAccount;
    }

    @Override
    public String toString() {
        return "Payment{" +
                "paymentId=" + paymentId + '\'' +
                "toBankAccount=" + toBankAccount + '\'' +
                "paymentAmount=" + paymentAmount + '\'' +
                "paymentDate=" + paymentDate + '\'' +
                "sent=" + sent + '\'' +
                "bank account number=" + bankAccount.getBankAccountNumber() + '\'' +
                '}';
    }
    /**
     * The payment ID is unique for each Payment. So this should compare Payment by ID only.
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object other) {
        return (other instanceof Payment) && (paymentId != null)
                ? paymentId.equals(((Payment) other).paymentId)
                : (other == this);
    }

    /**
     * The payment ID is unique for each Payment. So Payment with same ID should return same hashcode.
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        return (paymentId != null)
                ? (this.getClass().hashCode() + paymentId.hashCode())
                : super.hashCode();
    }
}

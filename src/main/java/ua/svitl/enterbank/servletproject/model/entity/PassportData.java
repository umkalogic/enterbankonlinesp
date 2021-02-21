package ua.svitl.enterbank.servletproject.model.entity;

import ua.svitl.enterbank.servletproject.model.entity.constants.EntityConstants;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class PassportData implements Serializable {

    private static final long serialVersionUID = -5105710488336064631L;

    private Integer passportId;
    private Boolean domestic = Boolean.TRUE;
    private LocalDateTime passportIssueDate;
    private String passportIssuedBy;
    private Integer passportNumber;
    private String passportSeries;
    // private Integer personId; // column
    private Person person;

    public PassportData() {
    }

    public PassportData(Integer passportId, Boolean domestic, LocalDateTime passportIssueDate, String passportIssuedBy,
                        Integer passportNumber, String passportSeries, Person person) {
        this.passportId = passportId;
        this.domestic = domestic;
        this.passportIssueDate = LocalDateTime.parse(passportIssueDate.toString(),
                DateTimeFormatter.ofPattern(EntityConstants.FORMAT_DATE_TIME));
        this.passportIssuedBy = passportIssuedBy;
        this.passportNumber = passportNumber;
        this.passportSeries = passportSeries;
        this.person = person;
    }

    public PassportData(Boolean domestic, LocalDateTime passportIssueDate, String passportIssuedBy,
                        Integer passportNumber, String passportSeries, Person person) {
        this.domestic = domestic;
        this.passportIssueDate = LocalDateTime.parse(passportIssueDate.toString(),
                DateTimeFormatter.ofPattern(EntityConstants.FORMAT_DATE_TIME));
        this.passportIssuedBy = passportIssuedBy;
        this.passportNumber = passportNumber;
        this.passportSeries = passportSeries;
        this.person = person;
    }

    public void setPassportId(Integer passportId) {
        this.passportId = passportId;
    }

    public Integer getPassportId() {
        return passportId;
    }

    public void setDomestic(Boolean domestic) {
        this.domestic = domestic;
    }

    public Boolean isDomestic() {
        return domestic;
    }

    public void setPassportIssueDate(LocalDateTime passportIssueDate) {
        this.passportIssueDate = LocalDateTime.parse(passportIssueDate.toString(),
                DateTimeFormatter.ofPattern(EntityConstants.FORMAT_DATE_TIME));
    }

    public LocalDateTime getPassportIssueDate() {
        return passportIssueDate;
    }

    public void setPassportIssuedBy(String passportIssuedBy) {
        this.passportIssuedBy = passportIssuedBy;
    }

    public String getPassportIssuedBy() {
        return passportIssuedBy;
    }

    public void setPassportNumber(Integer passportNumber) {
        this.passportNumber = passportNumber;
    }

    public Integer getPassportNumber() {
        return passportNumber;
    }

    public void setPassportSeries(String passportSeries) {
        this.passportSeries = passportSeries;
    }

    public String getPassportSeries() {
        return passportSeries;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public Person getPerson() {
        return person;
    }

    @Override
    public String toString() {
        return "PassportData{" +
                "passportId=" + passportId + '\'' +
                "domestic=" + domestic + '\'' +
                "passportSeries=" + passportSeries + '\'' +
                "passportNumber=" + passportNumber + '\'' +
                "passportIssueDate=" + passportIssueDate + '\'' +
                "passportIssuedBy=" + passportIssuedBy + '\'' +
                "person tax code=" + person.getIdNumberTaxCode() + '\'' +
                '}';
    }

    /**
     * The passport ID is unique for each PassportData. So this should compare PassportData by ID only.
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object other) {
        return (other instanceof User) && (passportId != null)
                ? passportId.equals(((PassportData) other).passportId)
                : (other == this);
    }

    /**
     * The passport ID is unique for each PassportData. So PassportData with same ID should return same hashcode.
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        return (passportId != null)
                ? (this.getClass().hashCode() + passportId.hashCode())
                : super.hashCode();
    }
}

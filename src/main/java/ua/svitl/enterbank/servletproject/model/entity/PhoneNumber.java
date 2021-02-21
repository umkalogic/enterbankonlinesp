package ua.svitl.enterbank.servletproject.model.entity;

import ua.svitl.enterbank.servletproject.model.entity.constants.EntityConstants;

import java.io.Serializable;

public class PhoneNumber implements Serializable {

    private static final long serialVersionUID = 1392952185422223095L;

    private Integer phoneId;
    private Boolean primary = Boolean.TRUE;
    private String phone;
    private String phoneCountryCode = EntityConstants.DEFAULT_COUNTRY_CODE;
    // private Integer personId; // column name
    private Person person;

    public PhoneNumber() {
    }

    public PhoneNumber(Integer phoneId, Boolean primary, String phone, String phoneCountryCode,
                       Person person) {
        this.phoneId = phoneId;
        this.primary = primary;
        this.phone = phone;
        this.phoneCountryCode = phoneCountryCode;
        this.person = person;
    }

    public PhoneNumber(Boolean primary, String phone, String phoneCountryCode,
                       Person person) {
        this.primary = primary;
        this.phone = phone;
        this.phoneCountryCode = phoneCountryCode;
        this.person = person;
    }

    public void setPhoneId(Integer phoneId) {
        this.phoneId = phoneId;
    }

    public Integer getPhoneId() {
        return phoneId;
    }

    public void setPrimary(Boolean primary) {
        this.primary = primary;
    }

    public Boolean isPrimary() {
        return primary;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhoneCountryCode(String phoneCountryCode) {
        this.phoneCountryCode = phoneCountryCode;
    }

    public String getPhoneCountryCode() {
        return phoneCountryCode;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    @Override
    public String toString() {
        return "PhoneNumber{" +
                "phoneId=" + phoneId + '\'' +
                "phone=" + phoneCountryCode + ' ' + phone + '\'' +
                "primary=" + primary + '\'' +
                "person tax code=" + person.getIdNumberTaxCode() + '\'' +
                '}';
    }

    /**
     * The phone ID is unique for each PhoneNumber. So this should compare PhoneNumber by ID only.
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object other) {
        return (other instanceof PhoneNumber) && (phoneId != null)
                ? phoneId.equals(((PhoneNumber) other).phoneId)
                : (other == this);
    }

    /**
     * The phone ID is unique for each PhoneNumber. So PhoneNumber with same ID should return same hashcode.
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        return (phoneId != null)
                ? (this.getClass().hashCode() + phoneId.hashCode())
                : super.hashCode();
    }
}

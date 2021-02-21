package ua.svitl.enterbank.servletproject.model.entity;

import java.io.Serializable;

public class PersonAddresses implements Serializable {

    private static final long serialVersionUID = -3670516524133133553L;

    private Integer personId;
    private Integer addressId;

    public PersonAddresses() {
    }

    public PersonAddresses(Integer personId, Integer addressId) {
        this.personId = personId;
        this.addressId = addressId;
    }

    public void setPersonId(Integer personId) {
        this.personId = personId;
    }

    public Integer getPersonId() {
        return personId;
    }

    public void setAddressId(Integer addressId) {
        this.addressId = addressId;
    }

    public Integer getAddressId() {
        return addressId;
    }

    @Override
    public String toString() {
        return "PersonAddresses{" +
                "personId=" + personId + '\'' +
                "addressId=" + addressId + '\'' +
                '}';
    }
}

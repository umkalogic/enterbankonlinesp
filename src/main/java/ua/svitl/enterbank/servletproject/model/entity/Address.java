package ua.svitl.enterbank.servletproject.model.entity;

import java.io.Serializable;

public class Address implements Serializable {

    private static final long serialVersionUID = -344204030199772399L;

    private Integer addressId;
    private String addressLine1;
    private String addressLine2 = " ";
    private String city;
    private Boolean registered = Boolean.TRUE;
    private Integer regionId; // join column
    private UkraineRegions region;

    public Address() {
    }

    public Address(Integer addressId, String addressLine1, String addressLine2, String city, Boolean registered,
                   UkraineRegions region) {
        this.addressId = addressId;
        this.addressLine1 = addressLine1;
        this.addressLine2 = addressLine2;
        this.city = city;
        this.registered = registered;
        this.region = region;
    }

    public Address(Integer addressId, String addressLine1, String city, Boolean registered,
                   UkraineRegions region) {
        this.addressId = addressId;
        this.addressLine1 = addressLine1;
        this.city = city;
        this.registered = registered;
        this.region = region;
    }

    public Address(String addressLine1, String addressLine2, String city, Boolean registered,
                   UkraineRegions region) {
        this.addressLine1 = addressLine1;
        this.addressLine2 = addressLine2;
        this.city = city;
        this.registered = registered;
        this.region = region;
    }

    public Address(String addressLine1, String city, Boolean registered,
                   UkraineRegions region) {
        this.addressLine1 = addressLine1;
        this.city = city;
        this.registered = registered;
        this.region = region;
    }

    public UkraineRegions getRegion() {
        return region;
    }

    public void setRegion(UkraineRegions region) {
        this.region = region;
    }

    public void setAddressId(Integer addressId) {
        this.addressId = addressId;
    }

    public Integer getAddressId() {
        return addressId;
    }

    public void setAddressLine1(String addressLine1) {
        this.addressLine1 = addressLine1;
    }

    public String getAddressLine1() {
        return addressLine1;
    }

    public void setAddressLine2(String addressLine2) {
        this.addressLine2 = addressLine2;
    }

    public String getAddressLine2() {
        return addressLine2;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCity() {
        return city;
    }

    public void setRegistered(Boolean registered) {
        this.registered = registered;
    }

    public Boolean isRegistered() {
        return registered;
    }

    public Integer getRegionId() {
        return regionId;
    }

    public void setRegionId(Integer regionId) {
        this.regionId = regionId;
    }

    @Override
    public String toString() {
        return "Address{" +
                "addressId=" + addressId + '\'' +
                "addressLine1=" + addressLine1 + '\'' +
                "addressLine2=" + addressLine2 + '\'' +
                "city=" + city + '\'' +
                "registered=" + registered + '\'' +
                "region =" + region.getUkraineRegionName() + '\'' +
                '}';
    }

    /**
     * The address ID is unique for each Address. So this should compare Address by ID only.
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object other) {
        return (other instanceof Address) && (addressId != null)
                ? addressId.equals(((Address) other).addressId)
                : (other == this);
    }

    /**
     * The address ID is unique for each Address. So Address with same ID should return same hashcode.
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        return (addressId != null)
                ? (this.getClass().hashCode() + addressId.hashCode())
                : super.hashCode();
    }
}

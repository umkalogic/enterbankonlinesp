package ua.svitl.enterbank.servletproject.model.dto;

import java.io.Serializable;
import java.time.LocalDateTime;

public class UserPersonDataDto implements Serializable {
    private static final long serialVersionUID = 6637829016521698686L;

    private Integer userId;
    private Integer personId;
    private String userName;
    private String userEmail;
    private String lastName;
    private String firstName;
    private String middleName;
    private LocalDateTime birthDate;
    private String phoneCountryCode;
    private String phone;
    private Boolean isActive;

    public UserPersonDataDto() {
        // constructor
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getPersonId() {
        return personId;
    }

    public void setPersonId(int personId) {
        this.personId = personId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public LocalDateTime getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDateTime birthDate) {
        this.birthDate = birthDate;
    }

    public String getPhoneCountryCode() {
        return phoneCountryCode;
    }

    public void setPhoneCountryCode(String phoneCountryCode) {
        this.phoneCountryCode = phoneCountryCode;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }

    @Override
    public String toString() {
        return "UserPersonDataDto{" +
                "userId=" + userId +
                ", personId=" + personId +
                ", userName='" + userName + '\'' +
                ", userEmail='" + userEmail + '\'' +
                ", lastName='" + lastName + '\'' +
                ", firstName='" + firstName + '\'' +
                ", middleName='" + middleName + '\'' +
                ", birthDate=" + birthDate +
                ", phoneCountryCode='" + phoneCountryCode + '\'' +
                ", phone='" + phone + '\'' +
                ", isActive=" + isActive +
                '}';
    }

}

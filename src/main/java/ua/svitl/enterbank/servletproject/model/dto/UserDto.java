package ua.svitl.enterbank.servletproject.model.dto;

import java.io.Serializable;

public class UserDto implements Serializable {
    private static final long serialVersionUID = -1333640513196116551L;

    private Integer userId;
    private String userName;
    private String email;
    private Boolean isActive;

    public UserDto() {
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }

    @Override
    public String toString() {
        return "UserDto{" +
                "userId=" + userId +
                ", userName='" + userName + '\'' +
                ", email='" + email + '\'' +
                ", isActive=" + isActive +
                '}';
    }

    /**
     * The user ID is unique for each User. So this should compare User by ID only.
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object other) {
        return (other instanceof UserDto) && (userId != null)
                ? userId.equals(((UserDto) other).userId)
                : (other == this);
    }

    /**
     * The user ID is unique for each User. So User with same ID should return same hashcode.
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        return (userId != null)
                ? (this.getClass().hashCode() + userId.hashCode())
                : super.hashCode();
    }

}
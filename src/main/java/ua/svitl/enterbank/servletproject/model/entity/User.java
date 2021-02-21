package ua.svitl.enterbank.servletproject.model.entity;

import java.io.Serializable;

public class User implements Serializable {

    private static final long serialVersionUID = -8539884223725040320L;

    private Integer userId;
    private String email;
    private Boolean isActive = Boolean.TRUE;
    private String password;
    private String userName;
    private Integer personId; // column
    private Integer roleId; // column
    private Person person = new Person();
    private Role role = new Role();

    public User() {}

    public User(Integer userId, String email, Boolean isActive, String password,
                String userName, Integer personId, Integer roleId) {
        this.userId = userId;
        this.email = email;
        this.isActive = isActive;
        this.password = password;
        this.userName = userName;
        this.personId = personId;
        this.roleId = roleId;
    }

    public User(String email, Boolean isActive, String password, String userName,
                Integer personId, Integer roleId) {
        this.email = email;
        this.isActive = isActive;
        this.password = password;
        this.userName = userName;
        this.personId = personId;
        this.roleId = roleId;
    }

    public User(Integer userId, String email, Boolean active, String password, String userName, Person person,
                Role role) {
        this.userId = userId;
        this.email = email;
        this.isActive = active;
        this.password = password;
        this.userName = userName;
        this.person = person;
        this.role = role;
    }

    public User(String email, Boolean active, String password, String userName, Person person,
                Role role) {
        this.email = email;
        this.isActive = active;
        this.password = password;
        this.userName = userName;
        this.person = person;
        this.role = role;
    }

    public User(Integer userId, String email, String password, String userName, Person person,
                Role role) {
        this.userId = userId;
        this.email = email;
        this.password = password;
        this.userName = userName;
        this.person = person;
        this.role = role;
    }

    public User(String email, String password, String userName, Person person,
                Role role) {
        this.email = email;
        this.password = password;
        this.userName = userName;
        this.person = person;
        this.role = role;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

    public Boolean getIsActive() {
        return isActive;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserName() {
        return userName;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Integer getPersonId() {
        return personId;
    }

    public void setPersonId(Integer personId) {
        this.personId = personId;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    @Override
    public String toString() {
        return "Users{" +
                "userId=" + userId + '\'' +
                "userName=" + userName + '\'' +
                "email=" + email + '\'' +
                "active=" + isActive + '\'' +
                '}';
    }

    /**
     * The user ID is unique for each User. So this should compare User by ID only.
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object other) {
        return (other instanceof User) && (userId != null)
                ? userId.equals(((User) other).userId)
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

package ua.svitl.enterbank.servletproject.model.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Role implements Serializable {

    private static final long serialVersionUID = 3507926514750446696L;
    private Integer roleId;
    private String roleName = "USER";
    private List<User> users = new ArrayList<>(); // users

    public Role() {
    }

    public Role(Integer roleId) {
        this.roleId = roleId;
    }

    public Role(Integer roleId, String roleName) {
        this.roleId = roleId;
        this.roleName = roleName;
    }

    public Role(String roleName) {
        this.roleName = roleName;
    }


    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getRoleName() {
        return roleName;
    }

    @Override
    public String toString() {
        return "Roles{" +
                "roleId=" + roleId + '\'' +
                "role=" + roleName + '\'' +
                '}';
    }

    /**
     * The role ID is unique for each Role. So this should compare Role by ID only.
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object other) {
        return (other instanceof Role) && (roleId != null)
                ? roleId.equals(((Role) other).roleId)
                : (other == this);
    }

    /**
     * The role ID is unique for each Role. So Role with same ID should return same hashcode.
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        return (roleId != null)
                ? (this.getClass().hashCode() + roleId.hashCode())
                : super.hashCode();
    }
}

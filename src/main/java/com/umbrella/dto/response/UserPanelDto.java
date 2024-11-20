package com.umbrella.dto.response;

public class UserPanelDto {
    private Integer id;
    private String fullName;
    private String email;
    private String role;
    private boolean isAdmin;
    private String initials;

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }

    public String getInitials() {
        return initials;
    }

    public void setInitials(String initials) {
        this.initials = initials;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public UserPanelDto() {
    }

    public UserPanelDto(String fullName, String email, String role, boolean isAdmin, String initials) {
        this.fullName = fullName;
        this.email = email;
        this.role = role;
        this.isAdmin = isAdmin;
        this.initials = initials;
    }
}

package com.cg.onlineshoppingms.adminms.dto;

public class AdminDetails {

    private Long adminId;
    private String username;

    public AdminDetails(){}

    public AdminDetails(Long adminId, String username){
        this.adminId=adminId;
        this.username=username;
    }

    public Long getAdminId() {
        return adminId;
    }

    public void setAdminId(Long adminId) {
        this.adminId = adminId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}

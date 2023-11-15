package com.example.donorin;

public class DataModalForgot {

    private String username;
    private String newpassword;
    private String status;

    public DataModalForgot(String username, String newpassword) {
        this.username = username;
        this.newpassword = newpassword;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getNewpassword() {
        return newpassword;
    }

    public void setNewpassword(String newpassword) {
        this.newpassword = newpassword;
    }

    public String getStatus() {
        return status;
    }
}

package com.example.donorin;

public class DataModalLogin {
    private String username;
    private String password;
    private String status;
    private String alamat;
    private String darah;
    private String lahir;
    private String message;
    private String nama;
    private String nik;
    private String nohp;
    private int poin;

    public DataModalLogin(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getStatus() {
        return status;
    }

    public String getAlamat() {
        return alamat;
    }

    public String getDarah() {
        return darah;
    }

    public String getLahir() {
        return lahir;
    }

    public String getMessage() {
        return message;
    }

    public String getNama() {
        return nama;
    }

    public String getNik() {
        return nik;
    }

    public String getNohp() {
        return nohp;
    }

    public int getPoin() {
        return poin;
    }
}

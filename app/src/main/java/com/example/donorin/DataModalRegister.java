package com.example.donorin;

public class DataModalRegister {

    // string variables for our name and job
    private String username;
    private String password;
    private String nik;
    private String nama;
    private String lahir;
    private String darah;
    private String alamat;
    private String nohp;

    public DataModalRegister(String username, String password, String nik, String nama, String lahir, String darah, String alamat, String nohp) {
        this.username = username;
        this.password = password;
        this.nik = nik;
        this.nama = nama;
        this.lahir = lahir;
        this.darah = darah;
        this.alamat = alamat;
        this.nohp = nohp;
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

    public String getNik() {
        return nik;
    }

    public void setNik(String nik) {
        this.nik = nik;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getLahir() {
        return lahir;
    }

    public void setLahir(String lahir) {
        this.lahir = lahir;
    }

    public String getDarah() {
        return darah;
    }

    public void setDarah(String darah) {
        this.darah = darah;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getNohp() {
        return nohp;
    }

    public void setNohp(String nohp) {
        this.nohp = nohp;
    }
}


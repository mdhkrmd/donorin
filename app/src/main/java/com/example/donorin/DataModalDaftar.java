package com.example.donorin;

import com.google.gson.annotations.SerializedName;

public class DataModalDaftar {
    private String nik;
    private String nama;
    private String darah;
    private String alamat;
    private String nohp;
    private String lokasi;
    private String jadwal;
    @SerializedName("id")
    private String idPenerima;

    public DataModalDaftar(String nik, String nama, String darah, String alamat, String nohp, String lokasi, String jadwal, String idPenerima) {
        this.nik = nik;
        this.nama = nama;
        this.darah = darah;
        this.alamat = alamat;
        this.nohp = nohp;
        this.lokasi = lokasi;
        this.jadwal = jadwal;
        this.idPenerima = idPenerima;
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

    public String getLokasi() {
        return lokasi;
    }

    public void setLokasi(String lokasi) {
        this.lokasi = lokasi;
    }

    public String getJadwal() {
        return jadwal;
    }

    public void setJadwal(String jadwal) {
        this.jadwal = jadwal;
    }
}

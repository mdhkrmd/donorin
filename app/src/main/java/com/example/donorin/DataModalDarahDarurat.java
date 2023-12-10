package com.example.donorin;

import com.google.gson.annotations.SerializedName;

public class DataModalDarahDarurat {

    @SerializedName("id")
    private String idDarahDarurat;
    @SerializedName("nama")
    private String namaDarahDarurat;
    @SerializedName("gol_darah")
    private String gol_darahDarurat;
    @SerializedName("deskripsi")
    private String deskripsiDarahDarurat;
    @SerializedName("status")
    private String statusDarahDarurat;
    @SerializedName("tanggal")
    private String tanggalDarahDarurat;



    public DataModalDarahDarurat(String idDarahDarurat, String namaDarahDarurat, String gol_darahDarurat, String deskripsiDarahDarurat, String statusDarahDarurat, String tanggalDarahDarurat) {
        this.idDarahDarurat = idDarahDarurat;
        this.namaDarahDarurat = namaDarahDarurat;
        this.gol_darahDarurat = gol_darahDarurat;
        this.deskripsiDarahDarurat = deskripsiDarahDarurat;
        this.statusDarahDarurat = statusDarahDarurat;
        this.tanggalDarahDarurat = tanggalDarahDarurat;
    }

    public String getNamaDarahDarurat() {
        return namaDarahDarurat;
    }

    public void setNamaDarahDarurat(String namaDarahDarurat) {
        this.namaDarahDarurat = namaDarahDarurat;
    }

    public String getGol_darahDarurat() {
        return gol_darahDarurat;
    }

    public void setGol_darahDarurat(String gol_darahDarurat) {
        this.gol_darahDarurat = gol_darahDarurat;
    }

    public String getDeskripsiDarahDarurat() {
        return deskripsiDarahDarurat;
    }

    public void setDeskripsiDarahDarurat(String deskripsiDarahDarurat) {
        this.deskripsiDarahDarurat = deskripsiDarahDarurat;
    }

    public String getIdDarahDarurat() {
        return idDarahDarurat;
    }

    public void setIdDarahDarurat(String idDarahDarurat) {
        this.idDarahDarurat = idDarahDarurat;
    }

    public String getStatusDarahDarurat() {
        return statusDarahDarurat;
    }

    public void setStatusDarahDarurat(String statusDarahDarurat) {
        this.statusDarahDarurat = statusDarahDarurat;
    }

    public String getTanggalDarahDarurat() {
        return tanggalDarahDarurat;
    }

    public void setTanggalDarahDarurat(String tanggalDarahDarurat) {
        this.tanggalDarahDarurat = tanggalDarahDarurat;
    }
}

package com.example.donorin;

import com.google.gson.annotations.SerializedName;

public class DataModalDarahDarurat {

    @SerializedName("nama")
    private String namaDarahDarurat;
    @SerializedName("gol_darah")
    private String gol_darahDarurat;
    @SerializedName("deskripsi")
    private String deskripsiDarahDarurat;



    public DataModalDarahDarurat(String namaDarahDarurat, String gol_darahDarurat, String deskripsiDarahDarurat) {
        this.namaDarahDarurat = namaDarahDarurat;
        this.gol_darahDarurat = gol_darahDarurat;
        this.deskripsiDarahDarurat = deskripsiDarahDarurat;

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
}

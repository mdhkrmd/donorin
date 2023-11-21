package com.example.donorin;

import com.google.gson.annotations.SerializedName;

public class RspmiData  {

    @SerializedName("id")
    private int id;
    @SerializedName("nama")
    private String nama;
    private String alamat;
    private String deskripsi;
    @SerializedName("foto")
    private String foto;

    public String getNamaRspmi() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public int getId() {
        return id;
    }

    public String getFoto() {
        return foto;
    }
}


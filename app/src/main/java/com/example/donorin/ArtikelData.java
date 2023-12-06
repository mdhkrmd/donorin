package com.example.donorin;

import com.google.gson.annotations.SerializedName;

public class ArtikelData {
    @SerializedName("id")
    private int idArtikel;
    @SerializedName("penulis")
    private String penulisArtikel;
    @SerializedName("judul")
    private String judulArtikel;
    @SerializedName("foto")
    private String fotoArtikel;
    @SerializedName("link")
    private String linkArtikel;

    public int getIdArtikel() {
        return idArtikel;
    }

    public String getPenulisArtikel() {
        return penulisArtikel;
    }

    public String getJudulArtikel() {
        return judulArtikel;
    }

    public String getFotoArtikel() {
        return fotoArtikel;
    }

    public String getLinkArtikel() {
        return linkArtikel;
    }
}

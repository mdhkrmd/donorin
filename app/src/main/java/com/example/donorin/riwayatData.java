package com.example.donorin;
import com.google.gson.annotations.SerializedName;
public class riwayatData {
    @SerializedName("id")
    private String idRiwayat;
    @SerializedName("nik")
    private String nikRiwayat;
    @SerializedName("nama")
    private String namaRiwayat;
    @SerializedName("darah")
    private String darahRiwayat;
    @SerializedName("alamat")
    private String alamatRiwayat;
    @SerializedName("no")
    private String nohpRiwayat;
    @SerializedName("lokasi")
    private String lokasiRiwayat;
    @SerializedName("jadwal")
    private String jadwalRiwayat;
    @SerializedName("status")
    private String statusRiwayat;

    public String getIdRiwayat() {
        return idRiwayat;
    }

    public void setIdRiwayat(String idRiwayat) {
        this.idRiwayat = idRiwayat;
    }

    public String getNikRiwayat() {
        return nikRiwayat;
    }

    public void setNikRiwayat(String nikRiwayat) {
        this.nikRiwayat = nikRiwayat;
    }

    public String getNamaRiwayat() {
        return namaRiwayat;
    }

    public void setNamaRiwayat(String namaRiwayat) {
        this.namaRiwayat = namaRiwayat;
    }

    public String getDarahRiwayat() {
        return darahRiwayat;
    }

    public void setDarahRiwayat(String darahRiwayat) {
        this.darahRiwayat = darahRiwayat;
    }

    public String getAlamatRiwayat() {
        return alamatRiwayat;
    }

    public void setAlamatRiwayat(String alamatRiwayat) {
        this.alamatRiwayat = alamatRiwayat;
    }

    public String getNohpRiwayat() {
        return nohpRiwayat;
    }

    public void setNohpRiwayat(String nohpRiwayat) {
        this.nohpRiwayat = nohpRiwayat;
    }

    public String getLokasiRiwayat() {
        return lokasiRiwayat;
    }

    public void setLokasiRiwayat(String lokasiRiwayat) {
        this.lokasiRiwayat = lokasiRiwayat;
    }

    public String getJadwalRiwayat() {
        return jadwalRiwayat;
    }

    public void setJadwalRiwayat(String jadwalRiwayat) {
        this.jadwalRiwayat = jadwalRiwayat;
    }

    public String getStatusRiwayat() {
        return statusRiwayat;
    }

    public void setStatusRiwayat(String statusRiwayat) {
        this.statusRiwayat = statusRiwayat;
    }
}

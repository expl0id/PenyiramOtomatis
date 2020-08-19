package com.android.penyiramotomatis;

public class Tanaman {
    String id_tanaman;
    String nama_tanaman;
    Integer kiri_kering;
    Integer tengah_kering;
    Integer kanan_kering;
    Integer kiri_lembab;
    Integer tengah_lembab;
    Integer kanan_lembab;
    Integer kiri_basah;
    Integer tengah_basah;
    Integer kanan_basah;

    public Tanaman(){

    }
    public Tanaman(String id_tanaman,String nama_tanaman, Integer kiri_kering, Integer tengah_kering, Integer kanan_kering, Integer kiri_lembab, Integer tengah_lembab, Integer kanan_lembab, Integer kiri_basah, Integer tengah_basah, Integer kanan_basah) {
        this.id_tanaman = id_tanaman;
        this.nama_tanaman = nama_tanaman;
        this.kiri_kering = kiri_kering;
        this.tengah_kering = tengah_kering;
        this.kanan_kering = kanan_kering;
        this.kiri_lembab = kiri_lembab;
        this.tengah_lembab = tengah_lembab;
        this.kanan_lembab = kanan_lembab;
        this.kiri_basah = kiri_basah;
        this.tengah_basah = tengah_basah;
        this.kanan_basah = kanan_basah;

    }

    public String getId_tanaman() {
        return id_tanaman;
    }

    public String getNama_tanaman() {
        return nama_tanaman;
    }

    public Integer getKiri_kering() {
        return kiri_kering;
    }

    public Integer getTengah_kering() {
        return tengah_kering;
    }

    public Integer getKanan_kering() {
        return kanan_kering;
    }

    public Integer getKiri_lembab() {
        return kiri_lembab;
    }

    public Integer getTengah_lembab() {
        return tengah_lembab;
    }

    public Integer getKanan_lembab() {
        return kanan_lembab;
    }

    public Integer getKiri_basah() {
        return kiri_basah;
    }

    public Integer getTengah_basah() {
        return tengah_basah;
    }

    public Integer getKanan_basah() {
        return kanan_basah;
    }
}

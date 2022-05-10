package com.example.b18dccn022_nguyenlamanh.model;

import java.io.Serializable;

public class Item implements Serializable {
    private int id;
    private String ten, noidung, date, tinhtrang;
    private int congtac;

    public Item(int id, String ten, String noidung, String date, String tinhtrang, int congtac) {
        this.id = id;
        this.ten = ten;
        this.noidung = noidung;
        this.date = date;
        this.tinhtrang = tinhtrang;
        this.congtac = congtac;
    }

    public Item() {
    }

    public Item(String ten, String noidung, String date, String tinhtrang, int congtac) {
        this.ten = ten;
        this.noidung = noidung;
        this.date = date;
        this.tinhtrang = tinhtrang;
        this.congtac = congtac;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getNoidung() {
        return noidung;
    }

    public void setNoidung(String noidung) {
        this.noidung = noidung;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTinhtrang() {
        return tinhtrang;
    }

    public void setTinhtrang(String tinhtrang) {
        this.tinhtrang = tinhtrang;
    }

    public int getCongtac() {
        return congtac;
    }

    public void setCongtac(int congtac) {
        this.congtac = congtac;
    }
}

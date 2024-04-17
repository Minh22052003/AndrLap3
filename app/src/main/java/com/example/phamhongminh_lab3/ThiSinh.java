package com.example.phamhongminh_lab3;

public class ThiSinh {
    private String HoTen, SoBaoDanh;
    private double DToan, DLy, DHoa;

    public ThiSinh() {
    }

    public ThiSinh(String hoTen, String soBaoDanh, double DToan, double DLy, double DHoa) {
        HoTen = hoTen;
        SoBaoDanh = soBaoDanh;
        this.DToan = DToan;
        this.DLy = DLy;
        this.DHoa = DHoa;
    }

    public String getHoTen() {
        return HoTen;
    }

    public void setHoTen(String hoTen) {
        HoTen = hoTen;
    }

    public String getSoBaoDanh() {
        return SoBaoDanh;
    }

    public void setSoBaoDanh(String soBaoDanh) {
        SoBaoDanh = soBaoDanh;
    }

    public double getDToan() {
        return DToan;
    }

    public void setDToan(double DToan) {
        this.DToan = DToan;
    }

    public double getDLy() {
        return DLy;
    }

    public void setDLy(double DLy) {
        this.DLy = DLy;
    }

    public double getDHoa() {
        return DHoa;
    }

    public void setDHoa(double DHoa) {
        this.DHoa = DHoa;
    }

    public double Tong(){
        return DLy+DLy+DHoa;
    }
    public double TrungBinh()
    {
        return (DLy+DLy+DHoa)/3;
    }}

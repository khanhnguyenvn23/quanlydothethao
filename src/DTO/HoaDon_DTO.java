package DTO;

import java.time.LocalDateTime;

public class HoaDon_DTO {
    private int maHD, maKH, maNV, maMGG;
    private double tongTien;
    private LocalDateTime ngayLap;

    public HoaDon_DTO() {
    }

    public HoaDon_DTO(int maHD, int maKH, int maNV, LocalDateTime ngayLap, double tongTien, int giamGia) {
        this.maHD = maHD;
        this.maKH = maKH;
        this.ngayLap = ngayLap;
        this.tongTien = tongTien;
        this.maMGG = giamGia;
    }

    public int getMaHD() {
        return maHD;
    }

    public LocalDateTime getNgayLap() {
        return ngayLap;
    }

    public double getTongTien() {
        return tongTien;
    }

    public int getmaNV() {
        return maNV;
    }
    public int getmaKH() {
        return maKH;
    }

    public void setMaHD(int maHD) {
        this.maHD = maHD;
    }

    public void setNgayLap(LocalDateTime ngayLap) {
        this.ngayLap = ngayLap;
    }

    public void setTongTien(double tongTien) {
        this.tongTien = tongTien;
    }

}
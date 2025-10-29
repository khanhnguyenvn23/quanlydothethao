package DTO;

public class ChiTietHoaDon_DTO {
    private int maHD, maSP, soLuong;
    private int maSize;
    private double donGia, thanhTien;

    public ChiTietHoaDon_DTO() {
    }

    public ChiTietHoaDon_DTO(int maSP, int soLuong, int maSize, double donGia,
            double thanhTien) {
        this.maSP = maSP;
        this.maSize = maSize;
        this.soLuong = soLuong;
        this.donGia = donGia;
        this.thanhTien = thanhTien;
    }

    public ChiTietHoaDon_DTO(int maHD, int maSP, int soLuong, int maSize, double donGia,
            double thanhTien) {
        this.maHD = maHD;
        this.maSP = maSP;
        this.maSize = maSize;
        this.soLuong = soLuong;
        this.donGia = donGia;
        this.thanhTien = thanhTien;
    }

    public ChiTietHoaDon_DTO(int maHD, int maSP, int soLuong, int maSize, double donGia) {
        this.maHD = maHD;
        this.maSP = maSP;
        this.maSize = maSize;
        this.soLuong = soLuong;
        this.donGia = donGia;
        this.thanhTien = soLuong * donGia;
    }

    // getter
    public int getMaHD() {
        return maHD;
    }

    public int getMaSP() {
        return maSP;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public double getDonGia() {
        return donGia;
    }

    public double getThanhTien() {
        return thanhTien;
    }

    // setter
    public void setMaHD(int maHD) {
        this.maHD = maHD;
    }

    public void setMaSP(int maSP) {
        this.maSP = maSP;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public void setDonGia(double donGia) {
        this.donGia = donGia;
    }

    public void setThanhTien(double thanhTien) {
        this.thanhTien = thanhTien;
    }

}

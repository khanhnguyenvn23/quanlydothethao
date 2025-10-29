package DTO;

public class ChiTietPhieuNhap_DTO {
    private int maPN, maSP, soLuongNhap;
    private double donGiaNhap, thanhTien;
    private int maSize;

    // constructor
    public ChiTietPhieuNhap_DTO() {

    }

    public ChiTietPhieuNhap_DTO(int maSP, int soLuongNhap, double donGiaNhap, double thanhTien,
            int maSize) {
        this.maSP = maSP;
        this.soLuongNhap = soLuongNhap;
        this.maSize = maSize;
        this.donGiaNhap = donGiaNhap;
        this.thanhTien = thanhTien;
    }

    public ChiTietPhieuNhap_DTO(int maPN, int maSP, int soLuongNhap, double donGiaNhap, double thanhTien,
            int maSize) {
        this.maPN = maPN;
        this.maSP = maSP;
        this.soLuongNhap = soLuongNhap;
        this.maSize = maSize;
        this.donGiaNhap = donGiaNhap;
        this.thanhTien = thanhTien;
    }

    public ChiTietPhieuNhap_DTO(int maPN, int maSP, int soLuongNhap, double donGiaNhap, int maSize) {
        this.maPN = maPN;
        this.maSP = maSP;
        this.soLuongNhap = soLuongNhap;
        this.maSize = maSize;
        this.donGiaNhap = donGiaNhap;
        this.thanhTien = donGiaNhap * soLuongNhap;
    }

    // getter
    public int getMaPN() {
        return maPN;
    }

    // public int getMaSP() {
    // return maSP;
    // }

    public int getSoLuongNhap() {
        return soLuongNhap;
    }

    public double getDonGianHap() {
        return donGiaNhap;
    }

    public double getThanhTien() {
        return thanhTien;
    }

    // public String getMaSize() {
    // return maSize;
    // }

    // setter
    public void setMaPN(int maPN) {
        this.maPN = maPN;
    }

    // public void setMaSP(int maSP) {
    // this.maSP = maSP;
    // }

    public void setSoLuongNhap(int soLuongNhap) {
        this.soLuongNhap = soLuongNhap;
    }

    public void setDonGiaNhap(double donGiaNhap) {
        this.donGiaNhap = donGiaNhap;
    }

    public void setThanhTien(double thanhTien) {
        this.thanhTien = thanhTien;
    }

    // public void setMaSize(String maSize) {
    // this.maSize = maSize;
    // }
    @Override
    public String toString() {
        return "ChiTietPhieuNhap [maPN = " + maPN + ", maSP = " + maSP + ", soLuong = "
                + soLuongNhap + ", maSize = " + maSize + ", donGia = " + donGiaNhap + ", thanhTien = " + thanhTien
                + "]";
    }
}
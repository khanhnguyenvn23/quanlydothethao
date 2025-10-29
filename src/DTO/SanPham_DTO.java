package DTO;

import java.util.Arrays;

public class SanPham_DTO {
    private int maSP, maNCC, maDM, maCN, soLuongTon, size;
    private String tenSP, mauSac;
    private double gia;
    private byte[] hinhAnh;

    public SanPham_DTO() {
    }

    public SanPham_DTO(int maSP, int maDM, int maNCC, String tenSP, int size, String mauSac, double gia,
            int soLuongTon, int maCN, byte[] hinhAnh) {
        this.maSP = maSP;
        this.maDM = maDM;
        this.maNCC = maNCC;
        this.tenSP = tenSP;
        this.size = size;
        this.mauSac = mauSac;
        this.gia = gia;
        this.soLuongTon = soLuongTon;
        this.maCN = maCN;
        this.hinhAnh = hinhAnh;
    }

    public int getSoLuongTon() {
        return soLuongTon;
    }

    public int getMaSP() {
        return maSP;
    }

    public String getTenSP() {
        return tenSP;
    }

    public String getMauSac() {
        return mauSac;
    }

    public double getGia() {
        return gia;
    }

    public byte[] getHinhAnh() {
        return hinhAnh;
    }

    public void setSoLuongTon(int soLuongTon) {
        this.soLuongTon = soLuongTon;
    }

    public void setMaSP(int maSP) {
        this.maSP = maSP;
    }

    public void setTenSP(String tenSP) {
        this.tenSP = tenSP;
    }

    public void setMauSac(String mauSac) {
        this.mauSac = mauSac;
    }

    public void setGia(double gia) {
        this.gia = gia;
    }

    public void setHinhAnh(byte[] hinhAnh) {
        this.hinhAnh = hinhAnh;
    }

    @Override
    public String toString() {
        return "SanPhamDTO [maSP=" + maSP + ", maLoai=" + maDM + ", tenSP=" + tenSP
                + ", price=" + gia + ", tenHinh=" + Arrays.toString(hinhAnh)
                + "]";
    }

}
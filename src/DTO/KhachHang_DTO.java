package DTO;

import java.time.LocalDate;

public class KhachHang_DTO {
    private int maKH;
    private String hoTen, email, diaChi,SDT;
    private LocalDate ngaySinh;

    public KhachHang_DTO() {
    }

    public KhachHang_DTO(String tenKH) {
        this.hoTen = tenKH;
    }

    public KhachHang_DTO(int maKH, String hoTen, LocalDate ngaySinh, String SDT, String email, String diaChi) {
        this.maKH = maKH;
        this.hoTen = hoTen;
        this.ngaySinh = ngaySinh;
        this.SDT = SDT;
        this.email = email;
        this.diaChi = diaChi;
    }

    public int getMaKH() {
        return maKH;
    }

    public String getHoTen() {
        return hoTen;
    }

    public String getSDT() {
        return SDT;
    }

    public LocalDate getNgaySinh() {
        return ngaySinh;
    }

    public String getEmail() {
        return email;
    }

    public void setMaKH(int maKH) {
        this.maKH = maKH;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public void setNgaySinh(LocalDate ngaySinh) {
        this.ngaySinh = ngaySinh;
    }

    public void setSDT(String SDT) {
        this.SDT = SDT;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return maKH + " " + hoTen + " " + SDT;
    }
}
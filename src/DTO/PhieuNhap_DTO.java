package DTO;

import java.time.LocalDateTime;

public class PhieuNhap_DTO {
    private int maPN, maNV, maNCC;
    private LocalDateTime ngayNhap;
    private double tongTien;

    public PhieuNhap_DTO() {
    }

    public PhieuNhap_DTO(int maPN) {
        this.maPN = maPN;
        this.maNV = 0;
        this.maNCC = 0;
        this.ngayNhap = LocalDateTime.now();
        this.tongTien = 0.0;
    }

    public PhieuNhap_DTO(int maPN, int maNV, int maNCC, LocalDateTime ngayNhap, double tongTien) {
        this.maPN = maPN;
        this.maNV = maNV;
        this.maNCC = maNCC;
        this.ngayNhap = ngayNhap;
        this.tongTien = tongTien;
    }

    public int getMaPN() {
        return maPN;
    }

    public LocalDateTime getNgayNhap() {
        return ngayNhap;
    }

    public double getTongTien() {
        return tongTien;
    }

    public void setMaPN(int maPN) {
        this.maPN = maPN;
    }
    public int getmaNV(){
        return maNV;
    }
     public int getmaNCC(){
        return maNCC;
    }
    public void setNgayNhap(LocalDateTime ngayNhap) {
        this.ngayNhap = ngayNhap;
    }

    public void setTongTien(double tongTien) {
        this.tongTien = tongTien;
    }

    @Override
    public String toString() {
        return "phieunhap_DTO [MAPN=" + maPN + ", MANV=" + maNV + ", ngay=" + ngayNhap + ", tongtien=" + tongTien
                + ", MANCC=" + maNCC + "]";
    }
}
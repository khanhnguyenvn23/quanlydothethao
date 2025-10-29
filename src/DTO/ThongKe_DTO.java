package DTO;

public class ThongKe_DTO {
    private int maSP, maDM, soLuong;
    private String tenSP;
    private double ThanhTien;

    public ThongKe_DTO() {
    }

    public ThongKe_DTO(int maSP, int maDM, String tenSP, int soLuong, double thanhTien) {
        this.maSP = maSP;
        this.maDM = maDM;
        this.tenSP = tenSP;
        this.soLuong = soLuong;
        this.ThanhTien = thanhTien;
    }
}

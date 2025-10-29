package DTO;

public class NCC_DTO {
    private int maNCC;
    private String tenNCC;
    private String diaChi;
    private int SDT;
    private boolean trangThai;

    public NCC_DTO() {
    }

    public NCC_DTO(int maNCC) {
        this.maNCC = maNCC;
        this.tenNCC = "";
        this.diaChi = "";
        this.SDT = 0000000000;
        this.trangThai = true;
    }

    public NCC_DTO(String tenNCC) {
        this.maNCC = 0;
        this.tenNCC = tenNCC;
        this.diaChi = "";
        this.SDT = 0000000000;
        this.trangThai = true;
    }

    public NCC_DTO(String tenNCC, int SDT) {
        this.maNCC = 0;
        this.tenNCC = tenNCC;
        this.diaChi = "";
        this.SDT = SDT;
        this.trangThai = true;
    }

    public NCC_DTO(int maNCC, String tenNCC, int SDT, String diaChi, boolean trangThai) {
        this.maNCC = maNCC;
        this.tenNCC = tenNCC;
        this.diaChi = diaChi;
        this.SDT = SDT;
        this.trangThai = trangThai;
    }

    public int getMaNCC() {
        return maNCC;
    }

    public String getTenNCC() {
        return tenNCC;
    }

    public void setMaNCC(int maNCC) {
        this.maNCC = maNCC;
    }

    public void setTenNCC(String tenNCC) {
        this.tenNCC = tenNCC;
    }

}
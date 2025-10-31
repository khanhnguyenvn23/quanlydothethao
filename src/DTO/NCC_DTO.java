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

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public String getSDT() {
        return String.valueOf(SDT);
    }

    public void setSDT(String sdt) {
        try {
            this.SDT = Integer.parseInt(sdt);
        } catch (NumberFormatException e) {
            this.SDT = 0;
        }
    }

    public boolean isTrangThai() {
        return trangThai;
    }

    public void setTrangThai(boolean trangThai) {
        this.trangThai = trangThai;
    }

    // Constructor với String SDT để phù hợp với DAO
    public NCC_DTO(int maNCC, String tenNCC, String diaChi, String sdt) {
        this.maNCC = maNCC;
        this.tenNCC = tenNCC;
        this.diaChi = diaChi;
        this.setSDT(sdt);
        this.trangThai = true;
    }
@Override
public String toString() {
    return tenNCC; // hiển thị tên
}
}
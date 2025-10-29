package DTO;

public class DanhMuc_DTO {
    private int maDM, soLuong;
    private String tenDM;
    private boolean trangThai;

    public DanhMuc_DTO(String tenDM) {
        this.maDM = 0;
        this.tenDM = tenDM;
        this.trangThai = true;
        this.soLuong = 0;
    }

    public DanhMuc_DTO(int maDM, String tenDM) {
        this.maDM = maDM;
        this.tenDM = tenDM;
        this.trangThai = true;
        this.soLuong = 0;
    }

    public DanhMuc_DTO(int maDM, String tenDM, boolean trangThai, int soLuong) {
        this.maDM = maDM;
        this.tenDM = tenDM;
        this.trangThai = trangThai;
        this.soLuong = soLuong;
    }

    public int getMaDM() {
        return maDM;
    }

    public String getTenDM() {
        return tenDM;
    }

    public boolean getTrangThai() {
        return trangThai;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setMaDM(int maDM) {
        this.maDM = maDM;
    }

    public void setTenDM(String tenDM) {
        this.tenDM = tenDM;
    }

    public void setTrangThai(boolean trangThai) {
        this.trangThai = trangThai;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    @Override
    public String toString() {
        return maDM + " " + tenDM + " " + ((trangThai == false) ? "Ngừng bán" : soLuong);
    }
}
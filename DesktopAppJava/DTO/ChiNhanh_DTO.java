package DTO;

public class ChiNhanh_DTO {
    private int maCN, SDT;
    private String tenCN, diaChi;

    public ChiNhanh_DTO() {
    }

    public ChiNhanh_DTO(int maCN, String tenCN, int SDT, String diaChi) {
        this.maCN = maCN;
        this.tenCN = tenCN;
        this.SDT = SDT;
        this.diaChi = diaChi;
    }

    public int getMaCN() {
        return maCN;
    }

    public int getSDT() {
        return SDT;
    }

    public String getTenCN() {
        return tenCN;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setMaCN(int maCN) {
        this.maCN = maCN;
    }

    public void setSDT(int SDT) {
        this.SDT = SDT;
    }

    public void setTenCN(String tenCN) {
        this.tenCN = tenCN;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }
}
package DTO;

public class NhanVien_DTO {
    private int maNV, SDT, maCN, maTK;
    private String hoTen, chucVu, diaChi;

    public NhanVien_DTO() {
    }

    public NhanVien_DTO(int maNV) {
        this.maNV = maNV;
        this.hoTen = "";
        this.SDT = 0000000000;
        this.diaChi = "";
        this.chucVu = "";
        this.maCN = 0;
        this.maTK = 0;
    }

    public NhanVien_DTO(String tenNV) {
        this.maNV = 0;
        this.hoTen = tenNV;
        this.SDT = 0000000000;
        this.diaChi = "";
        this.chucVu = "";
        this.maCN = 0;
        this.maTK = 0;
    }

    public NhanVien_DTO(int maNV, String tenNV) {
        this.maNV = maNV;
        this.hoTen = tenNV;
        this.SDT = 0000000000;
        this.diaChi = "";
        this.chucVu = "";
        this.maCN = 0;
        this.maTK = 0;
    }

    public NhanVien_DTO(int maNV, String tenNV, int SDT, String diaChi, String chucVu, int maCN, int maTK) {
        this.maNV = maNV;
        this.hoTen = tenNV;
        this.SDT = SDT;
        this.diaChi = diaChi;
        this.chucVu = chucVu;
        this.maCN = maCN;
        this.maTK = maTK;
    }

    public int getMaNV() {
        return maNV;
    }

    public int getMaCN() {
        return maCN;
    }

    public int getMaTK() {
        return maTK;
    }

    public String getHoTen() {
        return hoTen;
    }

    public String getChucVu() {
        return chucVu;
    }

    public void setMaNV(int maNV) {
        this.maNV = maNV;
    }

    public void setMaCN(int maCN) {
        this.maCN = maCN;
    }

    public void setMaTK(int maTK) {
        this.maTK = maTK;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public void setChucVu(String chucVu) {
        this.chucVu = chucVu;
    }

}
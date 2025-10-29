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

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public int getMaChiNhanh() {
        return maCN;
    }

    public void setMaChiNhanh(int maChiNhanh) {
        this.maCN = maChiNhanh;
    }

    public int getMaTaiKhoan() {
        return maTK;
    }

    public void setMaTaiKhoan(int maTaiKhoan) {
        this.maTK = maTaiKhoan;
    }

    // Constructor phù hợp với DAO
    public NhanVien_DTO(int maNV, String hoTen, String chucVu, String diaChi, String sdt, int maChiNhanh, int maTaiKhoan) {
        this.maNV = maNV;
        this.hoTen = hoTen;
        this.chucVu = chucVu;
        this.diaChi = diaChi;
        this.setSDT(sdt);
        this.maCN = maChiNhanh;
        this.maTK = maTaiKhoan;
    }

    @Override
    public String toString() {
        return "NhanVien_DTO{" +
                "maNV=" + maNV +
                ", hoTen='" + hoTen + '\'' +
                ", chucVu='" + chucVu + '\'' +
                ", diaChi='" + diaChi + '\'' +
                ", SDT=" + SDT +
                ", maChiNhanh=" + maCN +
                ", maTaiKhoan=" + maTK +
                '}';
    }
}
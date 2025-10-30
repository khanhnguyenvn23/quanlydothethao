package DTO;

public class SanPham_DTO {
    private int maSP, maNCC, maDM, size, soLuongTon;
    private String tenSP, mauSac, hinhAnh; // hinhAnh là đường dẫn
    private double gia;

    public SanPham_DTO() {}

    public SanPham_DTO(int maSP, int maDM, int maNCC, String tenSP, int size,
                       String mauSac, double gia, int soLuongTon, String hinhAnh) {
        this.maSP = maSP;
        this.maDM = maDM;
        this.maNCC = maNCC;
        this.tenSP = tenSP;
        this.size = size;
        this.mauSac = mauSac;
        this.gia = gia;
        this.soLuongTon = soLuongTon;
        this.hinhAnh = hinhAnh;
    }

    // Getter và Setter
    public int getMaSP() { return maSP; }
    public String getTenSP() { return tenSP; }
    public String getMauSac() { return mauSac; }
    public double getGia() { return gia; }
    public int getSoLuongTon() { return soLuongTon; }
    public String getHinhAnh() { return hinhAnh; }
    public int getMaNCC() { return maNCC; }
    public int getMaDM() { return maDM; }
    public int getSize() { return size; }

    public void setMaSP(int maSP) { this.maSP = maSP; }
    public void setTenSP(String tenSP) { this.tenSP = tenSP; }
    public void setMauSac(String mauSac) { this.mauSac = mauSac; }
    public void setGia(double gia) { this.gia = gia; }
    public void setSoLuongTon(int soLuongTon) { this.soLuongTon = soLuongTon; }
    public void setHinhAnh(String hinhAnh) { this.hinhAnh = hinhAnh; }
    public void setMaNCC(int maNCC) { this.maNCC = maNCC; }
    public void setMaDM(int maDM) { this.maDM = maDM; }
    public void setSize(int size) { this.size = size; }

    @Override
    public String toString() {
        return maSP + " - " + tenSP + " - " + gia;
    }
}

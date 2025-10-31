package DTO;

public class DanhMuc_DTO {
    private int maDM;
    private String tenDM;
    private String mota;
    private boolean trangThai;

    public DanhMuc_DTO() {
    }

    public DanhMuc_DTO(int maDM, String tenDM, String mota, boolean trangThai) {
        this.maDM = maDM;
        this.tenDM = tenDM;
        this.mota = mota;
        this.trangThai = trangThai;
    }


    public int getMaDM() {
        return this.maDM;
    }

    public void setMaDM(int maDM) {
        this.maDM = maDM;
    }

    public String getTenDM() {
        return this.tenDM;
    }

    public void setTenDM(String tenDM) {
        this.tenDM = tenDM;
    }

    public String getMota() {
        return this.mota;
    }

    public void setMota(String mota) {
        this.mota = mota;
    }

    public boolean isTrangThai() {
        return this.trangThai;
    }

    public boolean getTrangThai() {
        return this.trangThai;
    }

    public void setTrangThai(boolean trangThai) {
        this.trangThai = trangThai;
    }
   
    
}
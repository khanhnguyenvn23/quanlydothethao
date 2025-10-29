package DTO;

import java.time.LocalDateTime;

public class TaiKhoan_DTO {
    private int maTK, maQuyen;
    private String tenTK, matKhau;
    private LocalDateTime ngayTao;
    private boolean trangThai;

    public TaiKhoan_DTO() {
    }

    public TaiKhoan_DTO(int maTK, String tenTK, String matKhau, LocalDateTime ngayTao, int maQuyen, boolean trangThai) {
        this.maTK = maTK;
        this.tenTK = tenTK;
        this.matKhau = matKhau;
        this.ngayTao = ngayTao;
        this.maQuyen = maQuyen;
        this.trangThai = trangThai;
    }

    public int getMaTK() {
        return maTK;
    }

    public String getTenTK() {
        return tenTK;
    }

    public LocalDateTime getNgayTao() {
        return ngayTao;
    }

    public String getMatKhau() {
        return matKhau;
    }

    public void setMaTK(int maTK) {
        this.maTK = maTK;
    }

    public void setTenTK(String tenTK) {
        this.tenTK = tenTK;
    }

    public void setNgayTao(LocalDateTime ngayTao) {
        this.ngayTao = ngayTao;
    }

    public void setMatKhau(String matKhau) {
        this.matKhau = matKhau;
    }

}
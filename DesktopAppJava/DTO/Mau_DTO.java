package DTO;

public class Mau_DTO {
    private int maMau;
    private String tenMau;

    public Mau_DTO() {

    }

    public Mau_DTO(int maMau, String tenMau) {
        this.maMau = maMau;
        this.tenMau = tenMau;
    }

    public int getMaMau() {
        return maMau;
    }

    public String getTenMau() {
        return tenMau;
    }

    public void setMaMau(int maMau) {
        this.maMau = maMau;
    }

    public void setTenMau(String tenMau) {
        this.tenMau = tenMau;
    }

}
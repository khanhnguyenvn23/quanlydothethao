package DTO;

public class ChucNang_DTO {
    private int maCN;
    private String tenCN;

    // constructor
    public ChucNang_DTO() {

    }

    public ChucNang_DTO(int maCN, String tenCN) {
        this.maCN = maCN;
        this.tenCN = tenCN;
    }

    public ChucNang_DTO(String s) {
        String[] arr_s = s.split(",");
        maCN = Integer.parseInt(arr_s[0]);
        tenCN = arr_s[1];
    }

    // getter/setter
    public int getMaCN() {
        return maCN;
    }

    public String getTenCN() {
        return tenCN;
    }

    public void setMaCN(int maCN) {
        this.maCN = maCN;
    }

    public void setTenCN(String tenCN) {
        this.tenCN = tenCN;
    }

    @Override
    public String toString() {
        return maCN + "," + tenCN;
    }

}
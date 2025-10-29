package DTO;

public class NhomQuyen_DTO {
    private int maQuyen;
    private String tenQuyen;

    public NhomQuyen_DTO() {
    }

    public NhomQuyen_DTO(int maQ, String tenQ) {
        this.maQuyen = maQ;
        this.tenQuyen = tenQ;
    }

    public NhomQuyen_DTO(String s) {
        String[] newS = s.split(",");
        maQuyen = Integer.parseInt(newS[0]);
        tenQuyen = newS[1];
    }

    public int getMaQuyen() {
        return maQuyen;
    }

    public String getTenQuyen() {
        return tenQuyen;
    }

    public void setTenQuyen(String tenQ) {
        this.tenQuyen = tenQ;
    }

    public void setMaQuyen(int maQ) {
        this.maQuyen = maQ;
    }

    @Override
    public String toString() {
        return maQuyen + "," + tenQuyen;
    }
}
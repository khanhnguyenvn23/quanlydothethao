package DTO;

public class ChiTietNhomQuyen_DTO {
    private int maQuyen, maChucNang;
    private String hanhDong;

    public ChiTietNhomQuyen_DTO(int q, int cn, String hd) {
        this.maQuyen = q;
        this.maChucNang = cn;
        this.hanhDong = hd;
    }

    public ChiTietNhomQuyen_DTO(String s) {
        String[] arr_s = s.split(",");
        maQuyen = Integer.parseInt(arr_s[0]);
        maChucNang = Integer.parseInt(arr_s[1]);
        hanhDong = arr_s[2];
    }

    public String getHanhDong() {
        return hanhDong;
    }

    public void setHanhDong(String hanhDong) {
        this.hanhDong = hanhDong;
    }

    @Override
    public String toString() {
        return maQuyen + "," + maChucNang + "," + hanhDong;
    }
}

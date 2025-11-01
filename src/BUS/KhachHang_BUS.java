package BUS;

import DAO.*;
import DTO.*;
public class KhachHang_BUS {
    private KhachHang_DAO khachHang_DAO = new KhachHang_DAO();

    public String getTenKHbyId(int maKH){
        KhachHang_DAO khachHang_DAO = new KhachHang_DAO();
        KhachHang_DTO kh = khachHang_DAO.getKHbyId(maKH);
        return (kh != null) ? kh.getHoTen() : "";
    }

    public KhachHang_DTO getKHById(int maKH){
        KhachHang_DAO khachHang_DAO = new KhachHang_DAO();
        KhachHang_DTO kh = khachHang_DAO.getKHbyId(maKH);
        return kh;
    }
}

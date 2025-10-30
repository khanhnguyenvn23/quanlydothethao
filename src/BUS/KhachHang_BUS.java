package BUS;

import java.util.ArrayList;
import java.util.List;
import DAO.*;
import DTO.*;
public class KhachHang_BUS {
    private KhachHang_DAO khachHang_DAO = new KhachHang_DAO();

    public String getTenKHbyId(int maKH){
        KhachHang_DAO khachHang_DAO = new KhachHang_DAO();
        KhachHang_DTO kh = khachHang_DAO.getKHbyId(maKH);
        return (kh != null) ? kh.getHoTen() : "";
    }
}

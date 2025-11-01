package BUS;

import DAO.ChiTietPhieuNhap_DAO;
import DTO.ChiTietPhieuNhap_DTO;
import GUI.ChiTietPhieuNhap;

import java.util.ArrayList;

public class ChiTietPhieuNhap_BUS {
    private ChiTietPhieuNhap_DAO chiTietPhieuNhapDAO;
    private ArrayList<ChiTietPhieuNhap_DTO> listChiTietPhieuNhap;
    
    public ChiTietPhieuNhap_BUS() {
        chiTietPhieuNhapDAO = new ChiTietPhieuNhap_DAO();
        listChiTietPhieuNhap = new ArrayList<>();
    }
    
     public boolean addChiTietPhieuNhap(ChiTietPhieuNhap_DTO ctpn){
        return chiTietPhieuNhapDAO.addChiTietPhieuNhap(ctpn);
     }

    // Lấy tất cả chi tiết phiếu nhập
    public ArrayList<ChiTietPhieuNhap_DTO> getAllChiTietPhieuNhap() {
        listChiTietPhieuNhap = chiTietPhieuNhapDAO.getAllChiTietPhieuNhap();
        return listChiTietPhieuNhap;
    }
    
    // Lấy chi tiết phiếu nhập theo mã phiếu nhập
    public ArrayList<ChiTietPhieuNhap_DTO> getChiTietByMaPhieuNhap(int maPhieuNhap) {
        if (maPhieuNhap <= 0) {
            System.out.println("Mã phiếu nhập không hợp lệ!");
            return new ArrayList<>();
        }
        return chiTietPhieuNhapDAO.getChiTietByMaPhieuNhap(maPhieuNhap);
    }
    
   
   
    
   
    
  
}
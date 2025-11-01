package BUS;

import DAO.SanPham_DAO;
import DTO.SanPham_DTO;
import java.util.ArrayList;

public class SanPham_BUS {
    private SanPham_DAO sanPhamDAO;
    private ArrayList<SanPham_DTO> listSanPham;
    
    public SanPham_BUS() {
        sanPhamDAO = new SanPham_DAO();
        listSanPham = new ArrayList<>();
    }
    
    public ArrayList<SanPham_DTO> getAllSanPham() {
        listSanPham = sanPhamDAO.getAllSanPham();
        return listSanPham;
    }
    
    public SanPham_DTO getSanPhamById(int maSP) {
        return sanPhamDAO.getSanPhamById(maSP);
    }
    
    public ArrayList<SanPham_DTO> searchSanPham(String keyword) {
        ArrayList<SanPham_DTO> result = new ArrayList<>();
        
        if (listSanPham.isEmpty()) {
            listSanPham = getAllSanPham();
        }
        
        for (SanPham_DTO sp : listSanPham) {
            if (sp.getTenSP().toLowerCase().contains(keyword.toLowerCase()) ||
                sp.getMauSac().toLowerCase().contains(keyword.toLowerCase())) {
                result.add(sp);
            }
        }
        
        return result;
    }
    
    public ArrayList<SanPham_DTO> filterByPrice(double minPrice, double maxPrice) {
        ArrayList<SanPham_DTO> result = new ArrayList<>();
        
        if (listSanPham.isEmpty()) {
            listSanPham = getAllSanPham();
        }
        
        for (SanPham_DTO sp : listSanPham) {
            if (sp.getGia() >= minPrice && sp.getGia() <= maxPrice) {
                result.add(sp);
            }
        }
        
        return result;
    }

    // Cập nhật số lượng tồn kho
    public boolean updateSoLuongTon(int maSP, int soLuongMoi) {
      
        
        boolean result = sanPhamDAO.updateSoLuongTon(maSP, soLuongMoi);
        if (result) {
            System.out.println("Cập nhật tồn kho thành công cho sản phẩm ID: " + maSP);
            // Refresh danh sách sản phẩm
            listSanPham = getAllSanPham();
        } else {
            System.out.println("Cập nhật tồn kho thất bại cho sản phẩm ID: " + maSP);
        }
        
        return result;
    }


    
    // Lấy thống kê tồn kho

    public static void main(String[] args) {
        SanPham_BUS spbusss=new SanPham_BUS();
        
        spbusss.updateSoLuongTon(6, -15);
    }
}
package BUS;

import DAO.ChiTietPhieuNhap_DAO;
import DTO.ChiTietPhieuNhap_DTO;
import java.util.ArrayList;

public class ChiTietPhieuNhap_BUS {
    private ChiTietPhieuNhap_DAO chiTietPhieuNhapDAO;
    private ArrayList<ChiTietPhieuNhap_DTO> listChiTietPhieuNhap;
    
    public ChiTietPhieuNhap_BUS() {
        chiTietPhieuNhapDAO = new ChiTietPhieuNhap_DAO();
        listChiTietPhieuNhap = new ArrayList<>();
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
    
    // Thêm chi tiết phiếu nhập
    public boolean addChiTietPhieuNhap(ChiTietPhieuNhap_DTO chiTiet) {
        // Validate dữ liệu
        if (!validateChiTietPhieuNhap(chiTiet)) {
            return false;
        }
        
        // Kiểm tra chi tiết đã tồn tại chưa
        if (chiTietPhieuNhapDAO.isChiTietExists(chiTiet.getMaPN(), chiTiet.getMaSP())) {
            System.out.println("Chi tiết phiếu nhập đã tồn tại!");
            return false;
        }
        
        // Tính thành tiền
        double thanhTien = chiTiet.getSoLuongNhap() * chiTiet.getDonGianHap();
        chiTiet.setThanhTien(thanhTien);
        
        boolean result = chiTietPhieuNhapDAO.addChiTietPhieuNhap(chiTiet);
        if (result) {
            System.out.println("Thêm chi tiết phiếu nhập thành công!");
        } else {
            System.out.println("Thêm chi tiết phiếu nhập thất bại!");
        }
        
        return result;
    }
    
    // Cập nhật chi tiết phiếu nhập
    public boolean updateChiTietPhieuNhap(ChiTietPhieuNhap_DTO chiTiet) {
        // Validate dữ liệu
        if (!validateChiTietPhieuNhap(chiTiet)) {
            return false;
        }
        
        // Kiểm tra chi tiết có tồn tại không
        if (!chiTietPhieuNhapDAO.isChiTietExists(chiTiet.getMaPN(), chiTiet.getMaSP())) {
            System.out.println("Chi tiết phiếu nhập không tồn tại!");
            return false;
        }
        
        // Tính lại thành tiền
        double thanhTien = chiTiet.getSoLuongNhap() * chiTiet.getDonGianHap();
        chiTiet.setThanhTien(thanhTien);
        
        boolean result = chiTietPhieuNhapDAO.updateChiTietPhieuNhap(chiTiet);
        if (result) {
            System.out.println("Cập nhật chi tiết phiếu nhập thành công!");
        } else {
            System.out.println("Cập nhật chi tiết phiếu nhập thất bại!");
        }
        
        return result;
    }
    
    // Xóa chi tiết phiếu nhập
    public boolean deleteChiTietPhieuNhap(int maPhieuNhap, int maSP) {
        if (maPhieuNhap <= 0 || maSP <= 0) {
            System.out.println("Mã phiếu nhập hoặc mã sản phẩm không hợp lệ!");
            return false;
        }
        
        // Kiểm tra chi tiết có tồn tại không
        if (!chiTietPhieuNhapDAO.isChiTietExists(maPhieuNhap, maSP)) {
            System.out.println("Chi tiết phiếu nhập không tồn tại!");
            return false;
        }
        
        boolean result = chiTietPhieuNhapDAO.deleteChiTietPhieuNhap(maPhieuNhap, maSP);
        if (result) {
            System.out.println("Xóa chi tiết phiếu nhập thành công!");
        } else {
            System.out.println("Xóa chi tiết phiếu nhập thất bại!");
        }
        
        return result;
    }
    
    // Xóa tất cả chi tiết của một phiếu nhập
    public boolean deleteAllChiTietByMaPhieuNhap(int maPhieuNhap) {
        if (maPhieuNhap <= 0) {
            System.out.println("Mã phiếu nhập không hợp lệ!");
            return false;
        }
        
        boolean result = chiTietPhieuNhapDAO.deleteAllChiTietByMaPhieuNhap(maPhieuNhap);
        if (result) {
            System.out.println("Xóa tất cả chi tiết phiếu nhập thành công!");
        } else {
            System.out.println("Xóa tất cả chi tiết phiếu nhập thất bại!");
        }
        
        return result;
    }
    
    // Tính tổng tiền của một phiếu nhập
    public double getTongTienByMaPhieuNhap(int maPhieuNhap) {
        if (maPhieuNhap <= 0) {
            System.out.println("Mã phiếu nhập không hợp lệ!");
            return 0;
        }
        
        return chiTietPhieuNhapDAO.getTongTienByMaPhieuNhap(maPhieuNhap);
    }
    
    // Thêm nhiều chi tiết phiếu nhập cùng lúc
    public boolean addMultipleChiTietPhieuNhap(ArrayList<ChiTietPhieuNhap_DTO> listChiTiet) {
        if (listChiTiet == null || listChiTiet.isEmpty()) {
            System.out.println("Danh sách chi tiết phiếu nhập trống!");
            return false;
        }
        
        boolean allSuccess = true;
        for (ChiTietPhieuNhap_DTO chiTiet : listChiTiet) {
            if (!addChiTietPhieuNhap(chiTiet)) {
                allSuccess = false;
                System.out.println("Lỗi khi thêm chi tiết: " + chiTiet.toString());
            }
        }
        
        return allSuccess;
    }
    
    // Tìm kiếm chi tiết phiếu nhập theo mã sản phẩm
    public ArrayList<ChiTietPhieuNhap_DTO> searchByMaSanPham(int maSP) {
        ArrayList<ChiTietPhieuNhap_DTO> result = new ArrayList<>();
        
        if (listChiTietPhieuNhap.isEmpty()) {
            listChiTietPhieuNhap = getAllChiTietPhieuNhap();
        }
        
        for (ChiTietPhieuNhap_DTO ct : listChiTietPhieuNhap) {
            if (ct.getMaSP() == maSP) {
                result.add(ct);
            }
        }
        
        return result;
    }
    
    // Lọc chi tiết phiếu nhập theo khoảng giá
    public ArrayList<ChiTietPhieuNhap_DTO> filterByPriceRange(double minPrice, double maxPrice) {
        ArrayList<ChiTietPhieuNhap_DTO> result = new ArrayList<>();
        
        if (listChiTietPhieuNhap.isEmpty()) {
            listChiTietPhieuNhap = getAllChiTietPhieuNhap();
        }
        
        for (ChiTietPhieuNhap_DTO ct : listChiTietPhieuNhap) {
            if (ct.getDonGianHap() >= minPrice && ct.getDonGianHap() <= maxPrice) {
                result.add(ct);
            }
        }
        
        return result;
    }
    
    // Validate dữ liệu chi tiết phiếu nhập
    private boolean validateChiTietPhieuNhap(ChiTietPhieuNhap_DTO chiTiet) {
        if (chiTiet == null) {
            System.out.println("Chi tiết phiếu nhập không được null!");
            return false;
        }
        
        if (chiTiet.getMaPN() <= 0) {
            System.out.println("Mã phiếu nhập phải lớn hơn 0!");
            return false;
        }
        
        if (chiTiet.getMaSP() <= 0) {
            System.out.println("Mã sản phẩm phải lớn hơn 0!");
            return false;
        }
        
        if (chiTiet.getSoLuongNhap() <= 0) {
            System.out.println("Số lượng nhập phải lớn hơn 0!");
            return false;
        }
        
        if (chiTiet.getDonGianHap() <= 0) {
            System.out.println("Đơn giá nhập phải lớn hơn 0!");
            return false;
        }
        
        return true;
    }
    
    // Kiểm tra chi tiết phiếu nhập có tồn tại không
    public boolean isChiTietExists(int maPhieuNhap, int maSP) {
        return chiTietPhieuNhapDAO.isChiTietExists(maPhieuNhap, maSP);
    }
}
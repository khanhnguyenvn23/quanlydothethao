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

    // Tăng số lượng tồn kho (nhập hàng)
    public boolean nhapHang(int maSP, int soLuongNhap) {
     
        // Kiểm tra sản phẩm có tồn tại không
        SanPham_DTO sp = getSanPhamById(maSP);
        if (sp == null) {
            System.out.println("Không tìm thấy sản phẩm với ID: " + maSP);
            return false;
        }
        
        boolean result = sanPhamDAO.tangSoLuongTon(maSP, soLuongNhap);
        if (result) {
            System.out.println("Nhập hàng thành công: " + soLuongNhap + " sản phẩm " + sp.getTenSP());
            // Refresh danh sách sản phẩm
            listSanPham = getAllSanPham();
        } else {
            System.out.println("Nhập hàng thất bại cho sản phẩm: " + sp.getTenSP());
        }
        
        return result;
    }

    // Giảm số lượng tồn kho (bán hàng)
    public boolean banHang(int maSP, int soLuongBan) {
        if (maSP <= 0) {
            System.out.println("Mã sản phẩm không hợp lệ!");
            return false;
        }
        
        if (soLuongBan <= 0) {
            System.out.println("Số lượng bán phải lớn hơn 0!");
            return false;
        }
        
        // Kiểm tra sản phẩm có tồn tại không
        SanPham_DTO sp = getSanPhamById(maSP);
        if (sp == null) {
            System.out.println("Không tìm thấy sản phẩm với ID: " + maSP);
            return false;
        }
        
        // Kiểm tra tồn kho đủ không
        int soLuongHienTai = sanPhamDAO.getSoLuongTon(maSP);
        if (soLuongHienTai < soLuongBan) {
            System.out.println("Không đủ hàng trong kho! Tồn kho hiện tại: " + soLuongHienTai + 
                             ", yêu cầu: " + soLuongBan);
            return false;
        }
        
        boolean result = sanPhamDAO.giamSoLuongTon(maSP, soLuongBan);
        if (result) {
            System.out.println("Bán hàng thành công: " + soLuongBan + " sản phẩm " + sp.getTenSP());
            // Refresh danh sách sản phẩm
            listSanPham = getAllSanPham();
        } else {
            System.out.println("Bán hàng thất bại cho sản phẩm: " + sp.getTenSP());
        }
        
        return result;
    }

    // Kiểm tra tồn kho
    public int kiemTraTonKho(int maSP) {
        if (maSP <= 0) {
            System.out.println("Mã sản phẩm không hợp lệ!");
            return -1;
        }
        
        return sanPhamDAO.getSoLuongTon(maSP);
    }

    // Nhập hàng cho nhiều sản phẩm
    public boolean nhapHangNhieuSanPham(ArrayList<Integer> listMaSP, ArrayList<Integer> listSoLuongNhap) {
        if (listMaSP == null || listSoLuongNhap == null) {
            System.out.println("Danh sách không được null!");
            return false;
        }
        
        if (listMaSP.size() != listSoLuongNhap.size()) {
            System.out.println("Số lượng mã sản phẩm và số lượng nhập không khớp!");
            return false;
        }
        
        // Validate từng sản phẩm
        for (int i = 0; i < listMaSP.size(); i++) {
            if (listMaSP.get(i) <= 0 || listSoLuongNhap.get(i) <= 0) {
                System.out.println("Dữ liệu không hợp lệ tại vị trí " + i);
                return false;
            }
        }
        
        // Thực hiện nhập hàng từng sản phẩm
        boolean allSuccess = true;
        for (int i = 0; i < listMaSP.size(); i++) {
            if (!nhapHang(listMaSP.get(i), listSoLuongNhap.get(i))) {
                allSuccess = false;
            }
        }
        
        return allSuccess;
    }


   

    // Kiểm tra sản phẩm có đủ hàng để bán không
    public boolean kiemTraDuHang(int maSP, int soLuongCanBan) {
        if (maSP <= 0 || soLuongCanBan <= 0) {
            return false;
        }
        
        int soLuongTon = kiemTraTonKho(maSP);
        return soLuongTon >= soLuongCanBan;
    }

    // Lấy thống kê tồn kho
    public String getThongKeTonKho() {
        ArrayList<SanPham_DTO> allProducts = getAllSanPham();
        
        int tongSanPham = allProducts.size();
        int sanPhamHetHang = 0;
        int sanPhamSapHetHang = 0;
        int tongSoLuongTon = 0;
        
        for (SanPham_DTO sp : allProducts) {
            tongSoLuongTon += sp.getSoLuongTon();
            
            if (sp.getSoLuongTon() == 0) {
                sanPhamHetHang++;
            } else if (sp.getSoLuongTon() <= 5) { // Sắp hết hàng nếu <= 5
                sanPhamSapHetHang++;
            }
        }
        
        return String.format(
            "=== THỐNG KÊ TỒN KHO ===\n" +
            "Tổng số sản phẩm: %d\n" +
            "Tổng số lượng tồn: %d\n" +
            "Sản phẩm hết hàng: %d\n" +
            "Sản phẩm sắp hết hàng: %d\n" +
            "Sản phẩm còn đủ hàng: %d",
            tongSanPham, tongSoLuongTon, sanPhamHetHang, 
            sanPhamSapHetHang, tongSanPham - sanPhamHetHang - sanPhamSapHetHang
        );
    }

    public static void main(String[] args) {
        SanPham_BUS spbusss=new SanPham_BUS();
        
        spbusss.updateSoLuongTon(6, 15);
    }
}
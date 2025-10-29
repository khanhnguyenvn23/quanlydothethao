package BUS;

import DAO.NhanVien_DAO;
import DTO.NhanVien_DTO;
import java.util.ArrayList;

public class NhanVien_BUS {
    
    private NhanVien_DAO nvDAO;
    private ArrayList<NhanVien_DTO> listNV;
    
    public NhanVien_BUS() {
        nvDAO = new NhanVien_DAO();
        listNV = new ArrayList<>();
    }
    
    // Lấy danh sách tất cả nhân viên
    public ArrayList<NhanVien_DTO> getListNV() {
        listNV = nvDAO.selectAll();
        return listNV;
    }
    
    // Thêm nhân viên mới
    public boolean addNV(NhanVien_DTO nv) {
        // Kiểm tra dữ liệu đầu vào
        if (!validateNV(nv)) {
            return false;
        }
        
        boolean result = nvDAO.insert(nv);
        if (result) {
            listNV.add(nv);
        }
        return result;
    }
    
    // Cập nhật nhân viên
    public boolean updateNV(NhanVien_DTO nv) {
        // Kiểm tra dữ liệu đầu vào
        if (!validateNV(nv)) {
            return false;
        }
        
        boolean result = nvDAO.update(nv);
        if (result) {
            // Cập nhật trong danh sách
            for (int i = 0; i < listNV.size(); i++) {
                if (listNV.get(i).getMaNV() == nv.getMaNV()) {
                    listNV.set(i, nv);
                    break;
                }
            }
        }
        return result;
    }
    
    // Xóa nhân viên
    public boolean deleteNV(int maNV) {
        // Kiểm tra xem nhân viên có đang được sử dụng không
        if (checkNVInUse(maNV)) {
            return false;
        }
        
        boolean result = nvDAO.delete(maNV);
        if (result) {
            // Xóa khỏi danh sách
            listNV.removeIf(nv -> nv.getMaNV() == maNV);
        }
        return result;
    }
    
    // Tìm kiếm nhân viên theo tên
    public ArrayList<NhanVien_DTO> searchNV(String hoTen) {
        if (hoTen == null || hoTen.trim().isEmpty()) {
            return getListNV();
        }
        return nvDAO.searchByName(hoTen.trim());
    }
    
    // Lấy nhân viên theo mã
    public NhanVien_DTO getNVById(int maNV) {
        return nvDAO.selectById(maNV);
    }
    
    // Lấy tên nhân viên theo mã
    public String getTenNVById(int maNV) {
        NhanVien_DTO nv = nvDAO.selectById(maNV);
        return (nv != null) ? nv.getHoTen() : "";
    }
    
    // Lấy danh sách nhân viên theo chi nhánh
    public ArrayList<NhanVien_DTO> getNVByChiNhanh(int maChiNhanh) {
        return nvDAO.selectByChiNhanh(maChiNhanh);
    }
    
    // Lấy danh sách tên nhân viên cho ComboBox
    public ArrayList<String> getListTenNV() {
        ArrayList<String> listTen = new ArrayList<>();
        listTen.add("Tất cả");
        
        ArrayList<NhanVien_DTO> list = getListNV();
        for (NhanVien_DTO nv : list) {
            listTen.add(nv.getHoTen());
        }
        
        return listTen;
    }
    
    // Lấy danh sách nhân viên theo chức vụ
    public ArrayList<NhanVien_DTO> getNVByChucVu(String chucVu) {
        ArrayList<NhanVien_DTO> result = new ArrayList<>();
        ArrayList<NhanVien_DTO> list = getListNV();
        
        for (NhanVien_DTO nv : list) {
            if (nv.getChucVu().equalsIgnoreCase(chucVu)) {
                result.add(nv);
            }
        }
        
        return result;
    }
    
    // Kiểm tra dữ liệu đầu vào
    private boolean validateNV(NhanVien_DTO nv) {
        if (nv == null) {
            return false;
        }
        
        // Kiểm tra họ tên không được rỗng
        if (nv.getHoTen() == null || nv.getHoTen().trim().isEmpty()) {
            return false;
        }
        
        // Kiểm tra độ dài họ tên
        if (nv.getHoTen().trim().length() > 55) {
            return false;
        }
        
        // Kiểm tra chức vụ
        if (nv.getChucVu() == null || nv.getChucVu().trim().isEmpty()) {
            return false;
        }
        
        if (nv.getChucVu().trim().length() > 55) {
            return false;
        }
        
        // Kiểm tra địa chỉ
        if (nv.getDiaChi() != null && nv.getDiaChi().length() > 255) {
            return false;
        }
        
        // Kiểm tra số điện thoại
        if (nv.getSDT() != null && !nv.getSDT().trim().isEmpty()) {
            if (!nv.getSDT().matches("\\d{10,11}")) {
                return false;
            }
        }
        
        // Kiểm tra mã chi nhánh và tài khoản
        if (nv.getMaChiNhanh() <= 0 || nv.getMaTaiKhoan() <= 0) {
            return false;
        }
        
        return true;
    }
    
    // Kiểm tra nhân viên có đang được sử dụng không
    private boolean checkNVInUse(int maNV) {
        // TODO: Kiểm tra trong bảng HoaDon, PhieuNhap, etc.
        // Tạm thời return false
        return false;
    }
    
    // Lấy số lượng nhân viên
    public int getCount() {
        return getListNV().size();
    }
    
    // Lấy số lượng nhân viên theo chi nhánh
    public int getCountByChiNhanh(int maChiNhanh) {
        return getNVByChiNhanh(maChiNhanh).size();
    }
    
    // Lấy số lượng nhân viên theo chức vụ
    public int getCountByChucVu(String chucVu) {
        return getNVByChucVu(chucVu).size();
    }
    
    // Refresh danh sách
    public void refreshList() {
        listNV = nvDAO.selectAll();
    }
    
    // Kiểm tra nhân viên có tồn tại không
    public boolean isExist(int maNV) {
        return getNVById(maNV) != null;
    }
    
    // Lấy danh sách chức vụ duy nhất
    public ArrayList<String> getListChucVu() {
        ArrayList<String> listChucVu = new ArrayList<>();
        ArrayList<NhanVien_DTO> list = getListNV();
        
        for (NhanVien_DTO nv : list) {
            String chucVu = nv.getChucVu();
            if (!listChucVu.contains(chucVu)) {
                listChucVu.add(chucVu);
            }
        }
        
        return listChucVu;
    }
}
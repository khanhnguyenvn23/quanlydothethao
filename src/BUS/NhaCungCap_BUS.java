package BUS;

import DAO.NhaCungCap_DAO;
import DTO.NCC_DTO;
import java.util.ArrayList;

public class NhaCungCap_BUS {
    
    private NhaCungCap_DAO nccDAO;
    private ArrayList<NCC_DTO> listNCC;
    
    public NhaCungCap_BUS() {
        nccDAO = new NhaCungCap_DAO();
        listNCC = new ArrayList<>();
    }

    
    public ArrayList<NCC_DTO> selectAll() { 
    listNCC = nccDAO.selectAll();
    return listNCC;
}
    
    // Lấy danh sách tất cả nhà cung cấp
    public ArrayList<NCC_DTO> getListNCC() {
        listNCC = nccDAO.selectAll();
        return listNCC;
    }
    
    // Thêm nhà cung cấp mới
    public boolean addNCC(NCC_DTO ncc) {
        // Kiểm tra dữ liệu đầu vào
        if (!validateNCC(ncc)) {
            return false;
        }
        
        
        // Kiểm tra trùng tên
        if (checkDuplicateName(ncc.getTenNCC(), -1)) {
            return false;
        }
        
        boolean result = nccDAO.insert(ncc);
        if (result) {
            listNCC.add(ncc);
        }
        return result;
    }
    
    // Cập nhật nhà cung cấp
    public boolean updateNCC(NCC_DTO ncc) {
        // Kiểm tra dữ liệu đầu vào
        if (!validateNCC(ncc)) {
            return false;
        }
        
        // Kiểm tra trùng tên (trừ chính nó)
        if (checkDuplicateName(ncc.getTenNCC(), ncc.getMaNCC())) {
            return false;
        }
        
        boolean result = nccDAO.update(ncc);
        if (result) {
            // Cập nhật trong danh sách
            for (int i = 0; i < listNCC.size(); i++) {
                if (listNCC.get(i).getMaNCC() == ncc.getMaNCC()) {
                    listNCC.set(i, ncc);
                    break;
                }
            }
        }
        return result;
    }
    
    // Xóa nhà cung cấp
    public boolean deleteNCC(int maNCC) {
        // Kiểm tra xem nhà cung cấp có đang được sử dụng không
        if (checkNCCInUse(maNCC)) {
            return false;
        }
        
        boolean result = nccDAO.delete(maNCC);
        if (result) {
            // Xóa khỏi danh sách
            listNCC.removeIf(ncc -> ncc.getMaNCC() == maNCC);
        }
        return result;
    }
    
    // Tìm kiếm nhà cung cấp theo tên
    public ArrayList<NCC_DTO> searchNCC(String tenNCC) {
        if (tenNCC == null || tenNCC.trim().isEmpty()) {
            return getListNCC();
        }
        return nccDAO.searchByName(tenNCC.trim());
    }
    
    // Lấy nhà cung cấp theo mã
    public NCC_DTO getNCCById(int maNCC) {
        return nccDAO.selectById(maNCC);
    }
    
    // Lấy tên nhà cung cấp theo mã
    public String getTenNCCById(int maNCC) {
        NCC_DTO ncc = nccDAO.selectById(maNCC);
        return (ncc != null) ? ncc.getTenNCC() : "";
    }
    
    // Lấy danh sách tên nhà cung cấp cho ComboBox
    public ArrayList<String> getListTenNCC() {
        ArrayList<String> listTen = new ArrayList<>();
        listTen.add("Tất cả");
        
        ArrayList<NCC_DTO> list = getListNCC();
        for (NCC_DTO ncc : list) {
            listTen.add(ncc.getTenNCC());
        }
        
        return listTen;
    }
    
    // Kiểm tra dữ liệu đầu vào
    private boolean validateNCC(NCC_DTO ncc) {
        if (ncc == null) {
            return false;
        }
        
        // Kiểm tra tên không được rỗng
        if (ncc.getTenNCC() == null || ncc.getTenNCC().trim().isEmpty()) {
            return false;
        }
        
        // Kiểm tra độ dài tên
        if (ncc.getTenNCC().trim().length() > 55) {
            return false;
        }
        
        // Kiểm tra địa chỉ
        if (ncc.getDiaChi() != null && ncc.getDiaChi().length() > 255) {
            return false;
        }
        
        // Kiểm tra số điện thoại
        if (ncc.getSDT() != null && !ncc.getSDT().trim().isEmpty()) {
            if (!ncc.getSDT().matches("\\d{10,11}")) {
                return false;
            }
        }
        
        return true;
    }
    
    // Kiểm tra trùng tên
    private boolean checkDuplicateName(String tenNCC, int excludeId) {
        ArrayList<NCC_DTO> list = nccDAO.selectAll();
        for (NCC_DTO ncc : list) {
            if (ncc.getMaNCC() != excludeId && 
                ncc.getTenNCC().trim().equalsIgnoreCase(tenNCC.trim())) {
                return true;
            }
        }
        return false;
    }
    
    // Kiểm tra nhà cung cấp có đang được sử dụng không
    private boolean checkNCCInUse(int maNCC) {
        // TODO: Kiểm tra trong bảng SanPham và PhieuNhap
        // Tạm thời return false
        return false;
    }
    
    // Lấy số lượng nhà cung cấp
    public int getCount() {
        return getListNCC().size();
    }
    
    // Refresh danh sách
    public void refreshList() {
        listNCC = nccDAO.selectAll();
    }
}
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
    
}
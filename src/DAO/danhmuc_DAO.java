package DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import DTO.DanhMuc_DTO;
import java.sql.Connection;

public class danhmuc_DAO {
    public List<DanhMuc_DTO> selectAll(){
    List<DanhMuc_DTO> danhSachDanhMuc = new ArrayList<>();
    String sql = "SELECT * FROM DanhMucSanPham WHERE TrangThai =1";
        try (PreparedStatement stmt =ConnectDatabase.getconection().prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            
            while (rs.next()) {
                DanhMuc_DTO dm = new DanhMuc_DTO();
                dm.setMaDM(rs.getInt("MaDM"));
                dm.setTenDM(rs.getString("TenDanhMuc"));
                dm.setMota(rs.getString("mota"));
                danhSachDanhMuc.add(dm);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Lỗi khi lấy danh sách danh mục", e);
        }
        
        return danhSachDanhMuc;
    } 
    public boolean insert(DanhMuc_DTO dm) {
        boolean result = false;
        try {
            Connection con = ConnectDatabase.getconection();
            PreparedStatement pre = con.prepareCall("INSERT INTO DanhMucSanPham (TenDanhMuc,TrangThai,mota) VALUES (?,1,?)");
            pre.setString(1,dm.getTenDM());
   
            pre.setString(2,dm.getMota());

            if (pre.executeUpdate() >= 1) {
                result = true;
            }
            ConnectDatabase.closeconection(con);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public boolean update(DanhMuc_DTO dm) {
        boolean result = false;
        try {
            Connection con = ConnectDatabase.getconection();
            PreparedStatement pre = con.prepareCall("UPDATE DanhMucSanPham SET TenDanhMuc =?, mota=? WHERE MaDM = ?");
            pre.setString(1,dm.getTenDM());
            pre.setString(2,dm.getMota());
            pre.setInt(3,dm.getMaDM());
           

            if (pre.executeUpdate() >= 1) {
                result = true;
            }
            ConnectDatabase.closeconection(con);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
    public boolean delete(int id) {
        boolean result = false;
        try {
            Connection con = ConnectDatabase.getconection();
            PreparedStatement pre = con.prepareCall("UPDATE DanhMucSanPham SET trangthai = 0 WHERE MaDM = ?");
            pre.setInt(1, id);

            if (pre.executeUpdate() >= 1) {
                result = true;
            }
            ConnectDatabase.closeconection(con);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
    public List<DanhMuc_DTO> search(String keyword) {
    List<DanhMuc_DTO> danhSachDanhMuc = new ArrayList<>();
    String sql = "SELECT * FROM DanhMucSanPham " +
                 "WHERE (MaDM LIKE ? OR TenDanhMuc LIKE ? OR mota LIKE ?) AND trangthai = 1";
    
    try (Connection conn = ConnectDatabase.getconection();
         PreparedStatement stmt = conn.prepareStatement(sql)) {
        
        // Thêm ký tự % vào từ khóa tìm kiếm
        String searchPattern = "%" + keyword + "%";
        stmt.setString(1, searchPattern);
        stmt.setString(2, searchPattern);
        stmt.setString(3, searchPattern);
        
        try (ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                DanhMuc_DTO dm = new DanhMuc_DTO();
                dm.setMaDM(rs.getInt("MaDM"));
                dm.setTenDM(rs.getString("TenDanhMuc"));
                dm.setMota(rs.getString("mota"));
                danhSachDanhMuc.add(dm);
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
        throw new RuntimeException("Lỗi khi tìm kiếm danh mục", e);
    }
    
    return danhSachDanhMuc;
}
}

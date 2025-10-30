package DAO;

import DTO.NCC_DTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class NhaCungCap_DAO {
    
    // Lấy ID tiếp theo
    public int selectNextId() {
        int id = -1;
        try {
            Connection con = ConnectDatabase.getconection();
            Statement stm = con.createStatement();
            ResultSet rs = stm.executeQuery("SELECT IDENT_CURRENT('NhaCungCap') + IDENT_INCR('NhaCungCap') AS NextID");
            rs.next();
            id = rs.getInt(1);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return id;
    }
    
    // Thêm nhà cung cấp mới
    public boolean insert(NCC_DTO ncc) {
        boolean check = false;
        String sql = "INSERT INTO NhaCungCap (TenNCC, DiaChi, SDT, TrangThaiPhu) VALUES (?, ?, ?, ?)";
        
        try (Connection con = ConnectDatabase.getconection();
             PreparedStatement pre = con.prepareStatement(sql)) {
            
            pre.setString(1, ncc.getTenNCC());
            pre.setString(2, ncc.getDiaChi());
            pre.setInt(3, Integer.parseInt(ncc.getSDT()));
            pre.setBoolean(4, true);
            
            check = pre.executeUpdate() > 0;
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return check;
    }
    
    // Cập nhật nhà cung cấp
    public boolean update(NCC_DTO ncc) {
        boolean check = false;
        String sql = "UPDATE NhaCungCap SET TenNCC = ?, DiaChi = ?, SDT = ? WHERE MaNCC = ?";
        
        try (Connection con = ConnectDatabase.getconection();
             PreparedStatement pre = con.prepareStatement(sql)) {
            
            pre.setString(1, ncc.getTenNCC());
            pre.setString(2, ncc.getDiaChi());
            pre.setInt(3, Integer.parseInt(ncc.getSDT()));
            pre.setInt(4, ncc.getMaNCC());
            
            check = pre.executeUpdate() > 0;
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return check;
    }
    
    // Xóa nhà cung cấp (soft delete)
    public boolean delete(int maNCC) {
        boolean check = false;
        String sql = "UPDATE NhaCungCap SET TrangThaiPhu = 0 WHERE MaNCC = ?";
        
        try (Connection con = ConnectDatabase.getconection();
             PreparedStatement pre = con.prepareStatement(sql)) {
            
            pre.setInt(1, maNCC);
           check = pre.executeUpdate() > 0;
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return check;
    }
    
    // Lấy tất cả nhà cung cấp
    public ArrayList<NCC_DTO> selectAll() {
        ArrayList<NCC_DTO> list = new ArrayList<>();
        String sql = "SELECT MaNCC, TenNCC, DiaChi, SDT FROM NhaCungCap WHERE TrangThaiPhu = 1";
        
        try (Connection con = ConnectDatabase.getconection();
             PreparedStatement pre = con.prepareStatement(sql);
             ResultSet rs = pre.executeQuery()) {
            
            while (rs.next()) {
                int maNCC = rs.getInt("MaNCC");
                String tenNCC = rs.getString("TenNCC");
                String diaChi = rs.getString("DiaChi");
                String sdt = String.valueOf(rs.getInt("SDT"));
                
                NCC_DTO ncc = new NCC_DTO(maNCC, tenNCC, diaChi, sdt);
                list.add(ncc);
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return list;
    }
    
    // Lấy nhà cung cấp theo mã
    public NCC_DTO selectById(int maNCC) {
        NCC_DTO ncc = null;
        String sql = "SELECT MaNCC, TenNCC, DiaChi, SDT FROM NhaCungCap WHERE MaNCC = ? AND TrangThaiPhu = 1";
        
        try (Connection con = ConnectDatabase.getconection();
             PreparedStatement pre = con.prepareStatement(sql)) {
            
            pre.setInt(1, maNCC);
            ResultSet rs = pre.executeQuery();
            
            if (rs.next()) {
                String tenNCC = rs.getString("TenNCC");
                String diaChi = rs.getString("DiaChi");
                String sdt = String.valueOf(rs.getInt("SDT"));
                
                ncc = new NCC_DTO(maNCC, tenNCC, diaChi, sdt);
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return ncc;
    }
    
    // Tìm kiếm nhà cung cấp theo tên
    public ArrayList<NCC_DTO> searchByName(String tenNCC) {
        ArrayList<NCC_DTO> list = new ArrayList<>();
        String sql = "SELECT MaNCC, TenNCC, DiaChi, SDT FROM NhaCungCap WHERE TenNCC LIKE ? AND TrangThaiPhu = 1";
        
        try (Connection con = ConnectDatabase.getconection();
             PreparedStatement pre = con.prepareStatement(sql)) {
            
            pre.setString(1, "%" + tenNCC + "%");
            ResultSet rs = pre.executeQuery();
            
            while (rs.next()) {
                int maNCC = rs.getInt("MaNCC");
                String ten = rs.getString("TenNCC");
                String diaChi = rs.getString("DiaChi");
                String sdt = String.valueOf(rs.getInt("SDT"));
                
                NCC_DTO ncc = new NCC_DTO(maNCC, ten, diaChi, sdt);
                list.add(ncc);
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return list;
    }
}
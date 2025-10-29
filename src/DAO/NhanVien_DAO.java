package DAO;

import DTO.NhanVien_DTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class NhanVien_DAO {
    
    // Lấy ID tiếp theo
    public int selectNextId() {
        int id = -1;
        try {
            Connection con = ConnectDatabase.getconection();
            Statement stm = con.createStatement();
            ResultSet rs = stm.executeQuery("SELECT IDENT_CURRENT('NhanVien') + IDENT_INCR('NhanVien') AS NextID");
            rs.next();
            id = rs.getInt(1);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return id;
    }
    
    // Thêm nhân viên mới
    public boolean insert(NhanVien_DTO nv) {
        boolean check = false;
        String sql = "INSERT INTO NhanVien (HoTen, ChucVu, DiaChi, SDT, MaChiNhanh, MaTaiKhoan, TrangThaiPhu) VALUES (?, ?, ?, ?, ?, ?, ?)";
        
        try (Connection con = ConnectDatabase.getconection();
             PreparedStatement pre = con.prepareStatement(sql)) {
            
            pre.setString(1, nv.getHoTen());
            pre.setString(2, nv.getChucVu());
            pre.setString(3, nv.getDiaChi());
            pre.setInt(4, Integer.parseInt(nv.getSDT()));
            pre.setInt(5, nv.getMaChiNhanh());
            pre.setInt(6, nv.getMaTaiKhoan());
            pre.setBoolean(7, true);
            
            check = pre.executeUpdate() > 0;
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return check;
    }
    
    // Cập nhật nhân viên
    public boolean update(NhanVien_DTO nv) {
        boolean check = false;
        String sql = "UPDATE NhanVien SET HoTen = ?, ChucVu = ?, DiaChi = ?, SDT = ?, MaChiNhanh = ?, MaTaiKhoan = ? WHERE MaNV = ?";
        
        try (Connection con = ConnectDatabase.getconection();
             PreparedStatement pre = con.prepareStatement(sql)) {
            
            pre.setString(1, nv.getHoTen());
            pre.setString(2, nv.getChucVu());
            pre.setString(3, nv.getDiaChi());
            pre.setInt(4, Integer.parseInt(nv.getSDT()));
            pre.setInt(5, nv.getMaChiNhanh());
            pre.setInt(6, nv.getMaTaiKhoan());
            pre.setInt(7, nv.getMaNV());
            
            check = pre.executeUpdate() > 0;
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return check;
    }
    
    // Xóa nhân viên (soft delete)
    public boolean delete(int maNV) {
        boolean check = false;
        String sql = "UPDATE NhanVien SET TrangThaiPhu = 0 WHERE MaNV = ?";
        
        try (Connection con = ConnectDatabase.getconection();
             PreparedStatement pre = con.prepareStatement(sql)) {
            
            pre.setInt(1, maNV);
            check = pre.executeUpdate() > 0;
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return check;
    }
    
    // Lấy tất cả nhân viên
    public ArrayList<NhanVien_DTO> selectAll() {
        ArrayList<NhanVien_DTO> list = new ArrayList<>();
        String sql = "SELECT MaNV, HoTen, ChucVu, DiaChi, SDT, MaChiNhanh, MaTaiKhoan FROM NhanVien WHERE TrangThaiPhu = 1";
        
        try (Connection con = ConnectDatabase.getconection();
             PreparedStatement pre = con.prepareStatement(sql);
             ResultSet rs = pre.executeQuery()) {
            
            while (rs.next()) {
                int maNV = rs.getInt("MaNV");
                String hoTen = rs.getString("HoTen");
                String chucVu = rs.getString("ChucVu");
                String diaChi = rs.getString("DiaChi");
                String sdt = String.valueOf(rs.getInt("SDT"));
                int maChiNhanh = rs.getInt("MaChiNhanh");
                int maTaiKhoan = rs.getInt("MaTaiKhoan");
                
                NhanVien_DTO nv = new NhanVien_DTO(maNV, hoTen, chucVu, diaChi, sdt, maChiNhanh, maTaiKhoan);
                list.add(nv);
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return list;
    }
    
    // Lấy nhân viên theo mã
    public NhanVien_DTO selectById(int maNV) {
        NhanVien_DTO nv = null;
        String sql = "SELECT MaNV, HoTen, ChucVu, DiaChi, SDT, MaChiNhanh, MaTaiKhoan FROM NhanVien WHERE MaNV = ? AND TrangThaiPhu = 1";
        
        try (Connection con = ConnectDatabase.getconection();
             PreparedStatement pre = con.prepareStatement(sql)) {
            
            pre.setInt(1, maNV);
            ResultSet rs = pre.executeQuery();
            
            if (rs.next()) {
                String hoTen = rs.getString("HoTen");
                String chucVu = rs.getString("ChucVu");
                String diaChi = rs.getString("DiaChi");
                String sdt = String.valueOf(rs.getInt("SDT"));
                int maChiNhanh = rs.getInt("MaChiNhanh");
                int maTaiKhoan = rs.getInt("MaTaiKhoan");
                
                nv = new NhanVien_DTO(maNV, hoTen, chucVu, diaChi, sdt, maChiNhanh, maTaiKhoan);
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return nv;
    }
    
    // Tìm kiếm nhân viên theo tên
    public ArrayList<NhanVien_DTO> searchByName(String hoTen) {
        ArrayList<NhanVien_DTO> list = new ArrayList<>();
        String sql = "SELECT MaNV, HoTen, ChucVu, DiaChi, SDT, MaChiNhanh, MaTaiKhoan FROM NhanVien WHERE HoTen LIKE ? AND TrangThaiPhu = 1";
        
        try (Connection con = ConnectDatabase.getconection();
             PreparedStatement pre = con.prepareStatement(sql)) {
            
            pre.setString(1, "%" + hoTen + "%");
            ResultSet rs = pre.executeQuery();
            
            while (rs.next()) {
                int maNV = rs.getInt("MaNV");
                String ten = rs.getString("HoTen");
                String chucVu = rs.getString("ChucVu");
                String diaChi = rs.getString("DiaChi");
                String sdt = String.valueOf(rs.getInt("SDT"));
                int maChiNhanh = rs.getInt("MaChiNhanh");
                int maTaiKhoan = rs.getInt("MaTaiKhoan");
                
                NhanVien_DTO nv = new NhanVien_DTO(maNV, ten, chucVu, diaChi, sdt, maChiNhanh, maTaiKhoan);
                list.add(nv);
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return list;
    }
    
    // Lấy nhân viên theo chi nhánh
    public ArrayList<NhanVien_DTO> selectByChiNhanh(int maChiNhanh) {
        ArrayList<NhanVien_DTO> list = new ArrayList<>();
        String sql = "SELECT MaNV, HoTen, ChucVu, DiaChi, SDT, MaChiNhanh, MaTaiKhoan FROM NhanVien WHERE MaChiNhanh = ? AND TrangThaiPhu = 1";
        
        try (Connection con = ConnectDatabase.getconection();
             PreparedStatement pre = con.prepareStatement(sql)) {
            
            pre.setInt(1, maChiNhanh);
            ResultSet rs = pre.executeQuery();
            
            while (rs.next()) {
                int maNV = rs.getInt("MaNV");
                String hoTen = rs.getString("HoTen");
                String chucVu = rs.getString("ChucVu");
                String diaChi = rs.getString("DiaChi");
                String sdt = String.valueOf(rs.getInt("SDT"));
                int maTaiKhoan = rs.getInt("MaTaiKhoan");
                
                NhanVien_DTO nv = new NhanVien_DTO(maNV, hoTen, chucVu, diaChi, sdt, maChiNhanh, maTaiKhoan);
                list.add(nv);
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return list;
    }
}
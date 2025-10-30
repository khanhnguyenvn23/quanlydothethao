package DAO;

import DTO.ChiTietPhieuNhap_DTO;
import java.sql.*;
import java.util.ArrayList;

public class ChiTietPhieuNhap_DAO {
    
    // Lấy tất cả chi tiết phiếu nhập theo mã phiếu nhập
    public ArrayList<ChiTietPhieuNhap_DTO> getChiTietByMaPhieuNhap(int maPhieuNhap) {
        ArrayList<ChiTietPhieuNhap_DTO> list = new ArrayList<>();
        Connection con = ConnectDatabase.getconection();
        
        try {
            String sql = "SELECT ct.MaPhieuNhap, ct.MaSP, ct.SoLuongNhap, ct.DonGiaNhap, ct.ThanhTien, " +
                        "sp.TenSP, sp.MaSize " +
                        "FROM ChiTietPhieuNhap ct " +
                        "LEFT JOIN SanPham sp ON ct.MaSP = sp.MaSP " +
                        "WHERE ct.MaPhieuNhap = ? AND ct.TrangThaiPhu = 1";
            
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1, maPhieuNhap);
            ResultSet rs = pst.executeQuery();
            
            while (rs.next()) {
                int maPN = rs.getInt("MaPhieuNhap");
                int maSP = rs.getInt("MaSP");
                int soLuongNhap = rs.getInt("SoLuongNhap");
                double donGiaNhap = rs.getDouble("DonGiaNhap");
                double thanhTien = rs.getDouble("ThanhTien");
                int maSize = rs.getInt("MaSize");
                
                ChiTietPhieuNhap_DTO ct = new ChiTietPhieuNhap_DTO(maPN, maSP, soLuongNhap, donGiaNhap, thanhTien, maSize);
                list.add(ct);
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectDatabase.closeconection(con);
        }
        
        return list;
    }
    
    // Lấy tất cả chi tiết phiếu nhập
    public ArrayList<ChiTietPhieuNhap_DTO> getAllChiTietPhieuNhap() {
        ArrayList<ChiTietPhieuNhap_DTO> list = new ArrayList<>();
        Connection con = ConnectDatabase.getconection();
        
        try {
            String sql = "SELECT ct.MaPhieuNhap, ct.MaSP, ct.SoLuongNhap, ct.DonGiaNhap, ct.ThanhTien, " +
                        "sp.MaSize " +
                        "FROM ChiTietPhieuNhap ct " +
                        "LEFT JOIN SanPham sp ON ct.MaSP = sp.MaSP " +
                        "WHERE ct.TrangThaiPhu = 1 " +
                        "ORDER BY ct.MaPhieuNhap, ct.MaSP";
            
            PreparedStatement pst = con.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            
            while (rs.next()) {
                int maPN = rs.getInt("MaPhieuNhap");
                int maSP = rs.getInt("MaSP");
                int soLuongNhap = rs.getInt("SoLuongNhap");
                double donGiaNhap = rs.getDouble("DonGiaNhap");
                double thanhTien = rs.getDouble("ThanhTien");
                int maSize = rs.getInt("MaSize");
                
                ChiTietPhieuNhap_DTO ct = new ChiTietPhieuNhap_DTO(maPN, maSP, soLuongNhap, donGiaNhap, thanhTien, maSize);
                list.add(ct);
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectDatabase.closeconection(con);
        }
        
        return list;
    }
    
    // Thêm chi tiết phiếu nhập
    public boolean addChiTietPhieuNhap(ChiTietPhieuNhap_DTO chiTiet) {
        Connection con = ConnectDatabase.getconection();
        boolean result = false;
        
        try {
            String sql = "INSERT INTO ChiTietPhieuNhap (MaPhieuNhap, MaSP, SoLuongNhap, DonGiaNhap, ThanhTien, TrangThaiPhu) " +
                        "VALUES (?, ?, ?, ?, ?, 1)";
            
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1, chiTiet.getMaPN());
            pst.setInt(2, chiTiet.getMaSP());
            pst.setInt(3, chiTiet.getSoLuongNhap());
            pst.setDouble(4, chiTiet.getDonGianHap());
            pst.setDouble(5, chiTiet.getThanhTien());
            
            int rowsAffected = pst.executeUpdate();
            result = rowsAffected > 0;
            
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectDatabase.closeconection(con);
        }
        
        return result;
    }
    
    // Cập nhật chi tiết phiếu nhập
    public boolean updateChiTietPhieuNhap(ChiTietPhieuNhap_DTO chiTiet) {
        Connection con = ConnectDatabase.getconection();
        boolean result = false;
        
        try {
            String sql = "UPDATE ChiTietPhieuNhap SET SoLuongNhap = ?, DonGiaNhap = ?, ThanhTien = ? " +
                        "WHERE MaPhieuNhap = ? AND MaSP = ? AND TrangThaiPhu = 1";
            
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1, chiTiet.getSoLuongNhap());
            pst.setDouble(2, chiTiet.getDonGianHap());
            pst.setDouble(3, chiTiet.getThanhTien());
            pst.setInt(4, chiTiet.getMaPN());
            pst.setInt(5, chiTiet.getMaSP());
            
            int rowsAffected = pst.executeUpdate();
            result = rowsAffected > 0;
            
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectDatabase.closeconection(con);
        }
        
        return result;
    }
    
    // Xóa chi tiết phiếu nhập (soft delete)
    public boolean deleteChiTietPhieuNhap(int maPhieuNhap, int maSP) {
        Connection con = ConnectDatabase.getconection();
        boolean result = false;
        
        try {
            String sql = "UPDATE ChiTietPhieuNhap SET TrangThaiPhu = 0 " +
                        "WHERE MaPhieuNhap = ? AND MaSP = ?";
            
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1, maPhieuNhap);
            pst.setInt(2, maSP);
            
            int rowsAffected = pst.executeUpdate();
            result = rowsAffected > 0;
            
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectDatabase.closeconection(con);
        }
        
        return result;
    }
    
    // Xóa tất cả chi tiết của một phiếu nhập
    public boolean deleteAllChiTietByMaPhieuNhap(int maPhieuNhap) {
        Connection con = ConnectDatabase.getconection();
        boolean result = false;
        
        try {
            String sql = "UPDATE ChiTietPhieuNhap SET TrangThaiPhu = 0 " +
                        "WHERE MaPhieuNhap = ?";
            
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1, maPhieuNhap);
            
            int rowsAffected = pst.executeUpdate();
            result = rowsAffected > 0;
            
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectDatabase.closeconection(con);
        }
        
        return result;
    }
    
    // Kiểm tra chi tiết phiếu nhập có tồn tại không
    public boolean isChiTietExists(int maPhieuNhap, int maSP) {
        Connection con = ConnectDatabase.getconection();
        boolean exists = false;
        
        try {
            String sql = "SELECT COUNT(*) FROM ChiTietPhieuNhap " +
                        "WHERE MaPhieuNhap = ? AND MaSP = ? AND TrangThaiPhu = 1";
            
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1, maPhieuNhap);
            pst.setInt(2, maSP);
            
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                exists = rs.getInt(1) > 0;
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectDatabase.closeconection(con);
        }
        
        return exists;
    }
    
    // Tính tổng tiền của một phiếu nhập
    public double getTongTienByMaPhieuNhap(int maPhieuNhap) {
        Connection con = ConnectDatabase.getconection();
        double tongTien = 0;
        
        try {
            String sql = "SELECT SUM(ThanhTien) as TongTien FROM ChiTietPhieuNhap " +
                        "WHERE MaPhieuNhap = ? AND TrangThaiPhu = 1";
            
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1, maPhieuNhap);
            
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                tongTien = rs.getDouble("TongTien");
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectDatabase.closeconection(con);
        }
        
        return tongTien;
    }
}
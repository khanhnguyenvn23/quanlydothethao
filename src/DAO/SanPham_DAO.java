package DAO;

import DTO.SanPham_DTO;
import java.sql.*;
import java.util.ArrayList;

public class SanPham_DAO {

    // Lấy tất cả sản phẩm
    public ArrayList<SanPham_DTO> getAllSanPham() {
        ArrayList<SanPham_DTO> list = new ArrayList<>();
        String sql = "SELECT sp.MaSP, sp.TenSP, sp.DonGia, sp.SoLuongTon, sp.MaDM, sp.MaNCC, " +
                     "sp.MaSize, m.TenMau AS MauSac, sp.HinhAnh AS LinkAnh " +
                     "FROM SanPham sp " +
                     "LEFT JOIN Mau m ON sp.MaMau = m.MaMau " +
                     "WHERE sp.TrangThaiPhu = 1";

        try (Connection con = ConnectDatabase.getconection();
             PreparedStatement pst = con.prepareStatement(sql);
             ResultSet rs = pst.executeQuery()) {

            while (rs.next()) {
                SanPham_DTO sp = new SanPham_DTO();
                sp.setMaSP(rs.getInt("MaSP"));
                sp.setTenSP(rs.getString("TenSP"));
                sp.setGia(rs.getDouble("DonGia"));
                sp.setSoLuongTon(rs.getInt("SoLuongTon"));
                sp.setMaDM(rs.getInt("MaDM"));
                sp.setMaNCC(rs.getInt("MaNCC"));
                sp.setSize(rs.getInt("MaSize"));
                sp.setMauSac(rs.getString("MauSac") != null ? rs.getString("MauSac") : "Không xác định");
                sp.setHinhAnh(rs.getString("LinkAnh"));
                list.add(sp);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }

    // Lấy sản phẩm theo ID
    public SanPham_DTO getSanPhamById(int maSP) {
        SanPham_DTO sp = null;
        String sql = "SELECT sp.MaSP, sp.TenSP, sp.DonGia, sp.SoLuongTon, sp.MaDM, sp.MaNCC, " +
                     "sp.MaSize, m.TenMau AS MauSac, sp.HinhAnh AS LinkAnh " +
                     "FROM SanPham sp " +
                     "LEFT JOIN Mau m ON sp.MaMau = m.MaMau " +
                     "WHERE sp.MaSP = ? AND sp.TrangThaiPhu = 1";

        try (Connection con = ConnectDatabase.getconection();
             PreparedStatement pst = con.prepareStatement(sql)) {

            pst.setInt(1, maSP);
            try (ResultSet rs = pst.executeQuery()) {
                if (rs.next()) {
                    sp = new SanPham_DTO();
                    sp.setMaSP(rs.getInt("MaSP"));
                    sp.setTenSP(rs.getString("TenSP"));
                    sp.setGia(rs.getDouble("DonGia"));
                    sp.setSoLuongTon(rs.getInt("SoLuongTon"));
                    sp.setMaDM(rs.getInt("MaDM"));
                    sp.setMaNCC(rs.getInt("MaNCC"));
                    sp.setSize(rs.getInt("MaSize"));
                    sp.setMauSac(rs.getString("MauSac") != null ? rs.getString("MauSac") : "Không xác định");
                    sp.setHinhAnh(rs.getString("LinkAnh"));
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return sp;
    }

    // Cập nhật số lượng tồn kho
    public boolean updateSoLuongTon(int maSP, int soluong) {
        String sql = "UPDATE SanPham SET SoLuongTon = ? WHERE MaSP = ? AND TrangThaiPhu = 1";
        
        try (Connection con = ConnectDatabase.getconection();
             PreparedStatement pst = con.prepareStatement(sql)) {
            PreparedStatement pre2=con.prepareCall("select soluongton from sanpham where masp=?");
             pre2.setInt(1,maSP);
           
             ResultSet rs=pre2.executeQuery();
              rs.next();
            int sl=rs.getInt(1);
            sl+=soluong;
             pst.setInt(1, sl);
            pst.setInt(2, maSP);
            int rowsAffected = pst.executeUpdate();
            return rowsAffected > 0;
            
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Tăng số lượng tồn kho (dùng khi nhập hàng)
    public boolean tangSoLuongTon(int maSP, int soLuongTang) {
        String sql = "UPDATE SanPham SET SoLuongTon = SoLuongTon + ? WHERE MaSP = ? AND TrangThaiPhu = 1";
        
        try (Connection con = ConnectDatabase.getconection();
             PreparedStatement pst = con.prepareStatement(sql)) {
            
            pst.setInt(1, soLuongTang);
            pst.setInt(2, maSP);
            
            int rowsAffected = pst.executeUpdate();
            return rowsAffected > 0;
            
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Giảm số lượng tồn kho (dùng khi bán hàng)
    public boolean giamSoLuongTon(int maSP, int soLuongGiam) {
        String sql = "UPDATE SanPham SET SoLuongTon = SoLuongTon - ? " +
                    "WHERE MaSP = ? AND SoLuongTon >= ? AND TrangThaiPhu = 1";
        
        try (Connection con = ConnectDatabase.getconection();
             PreparedStatement pst = con.prepareStatement(sql)) {
            
            pst.setInt(1, soLuongGiam);
            pst.setInt(2, maSP);
            pst.setInt(3, soLuongGiam); // Đảm bảo không âm
            
            int rowsAffected = pst.executeUpdate();
            return rowsAffected > 0;
            
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Kiểm tra số lượng tồn kho hiện tại
    public int getSoLuongTon(int maSP) {
        String sql = "SELECT SoLuongTon FROM SanPham WHERE MaSP = ? AND TrangThaiPhu = 1";
        
        try (Connection con = ConnectDatabase.getconection();
             PreparedStatement pst = con.prepareStatement(sql)) {
            
            pst.setInt(1, maSP);
            
            try (ResultSet rs = pst.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("SoLuongTon");
                }
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return -1; // Trả về -1 nếu không tìm thấy hoặc có lỗi
    }

}

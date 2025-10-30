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
}

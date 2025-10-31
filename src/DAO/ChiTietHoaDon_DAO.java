package DAO;

import DTO.ChiTietHoaDon_DTO;
import java.sql.*;
import java.util.ArrayList;

public class ChiTietHoaDon_DAO {

    // Lấy danh sách tất cả chi tiết hóa đơn
    public ArrayList<ChiTietHoaDon_DTO> getAll() {
        ArrayList<ChiTietHoaDon_DTO> list = new ArrayList<>();
        String sql = "SELECT MaHD, MaSP, SoLuong, DonGia, ThanhTien FROM ChiTietHoaDon";

        try (Connection con = ConnectDatabase.getconection();
             PreparedStatement pre = con.prepareStatement(sql);
             ResultSet rs = pre.executeQuery()) {

            while (rs.next()) {
                ChiTietHoaDon_DTO cthd = new ChiTietHoaDon_DTO(
                    rs.getInt("MaHD"),
                    rs.getInt("MaSP"),
                    rs.getInt("SoLuong"),
                    rs.getDouble("DonGia"),
                    rs.getDouble("ThanhTien")
                );
                list.add(cthd);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    // Lấy chi tiết hóa đơn theo mã hóa đơn
    public ArrayList<ChiTietHoaDon_DTO> getByMaHD(int maHD) {
        ArrayList<ChiTietHoaDon_DTO> list = new ArrayList<>();
        String sql = "SELECT * FROM ChiTietHoaDon WHERE MaHD = ?";

        try (Connection con = ConnectDatabase.getconection();
             PreparedStatement pre = con.prepareStatement(sql)) {

            pre.setInt(1, maHD);
            ResultSet rs = pre.executeQuery();

            while (rs.next()) {
                ChiTietHoaDon_DTO cthd = new ChiTietHoaDon_DTO(
                    rs.getInt("MaHD"),
                    rs.getInt("MaSP"),
                    rs.getInt("SoLuong"),
                    rs.getDouble("DonGia"),
                    rs.getDouble("ThanhTien")
                );
                list.add(cthd);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    // Thêm chi tiết hóa đơn
    public boolean insert(ChiTietHoaDon_DTO cthd) {
        String sql = "INSERT INTO ChiTietHoaDon (MaHD, MaSP, SoLuong, DonGia, ThanhTien) VALUES (?, ?, ?, ?, ?)";

        try (Connection con = ConnectDatabase.getconection();
             PreparedStatement pre = con.prepareStatement(sql)) {

            pre.setInt(1, cthd.getMaHD());
            pre.setInt(2, cthd.getMaSP());
            pre.setInt(3, cthd.getSoLuong());
            pre.setDouble(4, cthd.getDonGia());
            pre.setDouble(5, cthd.getThanhTien());

            return pre.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    // Cập nhật chi tiết hóa đơn (chắc chắn không dùng)
    public boolean update(ChiTietHoaDon_DTO cthd) {
        String sql = "UPDATE ChiTietHoaDon SET SoLuong = ?, DonGia = ?, ThanhTien = ? WHERE MaHD = ? AND MaSP = ?";

        try (Connection con = ConnectDatabase.getconection();
             PreparedStatement pre = con.prepareStatement(sql)) {

            pre.setInt(1, cthd.getSoLuong());
            pre.setDouble(2, cthd.getDonGia());
            pre.setDouble(3, cthd.getThanhTien());
            pre.setInt(4, cthd.getMaHD());
            pre.setInt(5, cthd.getMaSP());

            return pre.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    // Xóa chi tiết hóa đơn
    public boolean delete(int maHD, int maSP) {
        String sql = "DELETE FROM ChiTietHoaDon WHERE MaHD = ? AND MaSP = ?";

        try (Connection con = ConnectDatabase.getconection();
             PreparedStatement pre = con.prepareStatement(sql)) {

            pre.setInt(1, maHD);
            pre.setInt(2, maSP);

            return pre.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }
}

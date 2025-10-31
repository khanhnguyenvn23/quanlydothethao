package DAO;
import DTO.HoaDon_DTO;
import DTO.SanPham_DTO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.time.LocalDateTime;
import java.sql.Timestamp;

public class HoaDon_DAO {
    //Lấy tất cả hoá đơn
    public ArrayList<HoaDon_DTO> getAllHoaDon(){
        ArrayList<HoaDon_DTO> result = new ArrayList<>();
        String sql = "SELECT MaHD, MaKH, MaNV, TongTien, NgayLap FROM HoaDon WHERE TrangThaiPhu = 1";

        try(Connection con = ConnectDatabase.getconection();
         PreparedStatement pre = con.prepareStatement(sql);
         ResultSet rs = pre.executeQuery()) {
            while(rs.next()){
                int maHD = rs.getInt("MaHD");
                int maKH = rs.getInt("MaKH");
                int maNv = rs.getInt("MaNV");
                double tongtien = rs.getDouble("TongTien");
                LocalDateTime day = rs.getObject("NgayLap", LocalDateTime.class);
                HoaDon_DTO hd = new HoaDon_DTO(maHD,maKH,maNv,day,tongtien);
                result.add(hd);
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    // ==================== HÀM TÌM KIẾM ====================

    // Tìm theo mã hóa đơn
    public ArrayList<HoaDon_DTO> searchByMaHD(int maHD) {
        ArrayList<HoaDon_DTO> result = new ArrayList<>();
        String sql = "SELECT * FROM HoaDon WHERE MaHD = ? AND TrangThaiPhu = 1";

        try (Connection con = ConnectDatabase.getconection();
             PreparedStatement pre = con.prepareStatement(sql)) {
            pre.setInt(1, maHD);
            ResultSet rs = pre.executeQuery();

            while (rs.next()) {
                int maKH = rs.getInt("MaKH");
                int maNV = rs.getInt("MaNV");
                double tongTien = rs.getDouble("TongTien");
                LocalDateTime ngayLap = rs.getTimestamp("NgayLap").toLocalDateTime();

                result.add(new HoaDon_DTO(maHD, maKH, maNV, ngayLap, tongTien));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    // Tìm theo tên nhân viên
    public ArrayList<HoaDon_DTO> searchByTenNhanVien(String tenNV) {
        ArrayList<HoaDon_DTO> result = new ArrayList<>();
        String sql = """
            SELECT hd.*
            FROM HoaDon hd
            JOIN NhanVien nv ON hd.MaNV = nv.MaNV
            WHERE nv.HoTen LIKE ? AND hd.TrangThaiPhu = 1
        """;

        try (Connection con = ConnectDatabase.getconection();
             PreparedStatement pre = con.prepareStatement(sql)) {
            pre.setString(1, "%" + tenNV + "%");
            ResultSet rs = pre.executeQuery();

            while (rs.next()) {
                int maHD = rs.getInt("MaHD");
                int maKH = rs.getInt("MaKH");
                int maNV = rs.getInt("MaNV");
                double tongTien = rs.getDouble("TongTien");
                LocalDateTime ngayLap = rs.getTimestamp("NgayLap").toLocalDateTime();

                result.add(new HoaDon_DTO(maHD, maKH, maNV, ngayLap, tongTien));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    // Tìm theo tên khách hàng
    public ArrayList<HoaDon_DTO> searchByTenKhachHang(String tenKH) {
        ArrayList<HoaDon_DTO> result = new ArrayList<>();
        String sql = """
            SELECT hd.*
            FROM HoaDon hd
            JOIN KhachHang kh ON hd.MaKH = kh.MaKH
            WHERE kh.HoTen LIKE ? AND hd.TrangThaiPhu = 1
        """;

        try (Connection con = ConnectDatabase.getconection();
             PreparedStatement pre = con.prepareStatement(sql)) {
            pre.setString(1, "%" + tenKH + "%");
            ResultSet rs = pre.executeQuery();

            while (rs.next()) {
                int maHD = rs.getInt("MaHD");
                int maKH = rs.getInt("MaKH");
                int maNV = rs.getInt("MaNV");
                double tongTien = rs.getDouble("TongTien");
                LocalDateTime ngayLap = rs.getTimestamp("NgayLap").toLocalDateTime();

                result.add(new HoaDon_DTO(maHD, maKH, maNV, ngayLap, tongTien));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    // Lọc theo khoảng ngày
    public ArrayList<HoaDon_DTO> filterByDate(LocalDateTime from, LocalDateTime to) {
        ArrayList<HoaDon_DTO> result = new ArrayList<>();
        String sql = """
            SELECT * FROM HoaDon
            WHERE NgayLap BETWEEN ? AND ? AND TrangThaiPhu = 1
        """;

        try (Connection con = ConnectDatabase.getconection();
             PreparedStatement pre = con.prepareStatement(sql)) {

            pre.setTimestamp(1, Timestamp.valueOf(from));
            pre.setTimestamp(2, Timestamp.valueOf(to));

            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                result.add(mapResultToHoaDon(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    // Lọc theo khoảng tiền
    public ArrayList<HoaDon_DTO> filterByTotal(double min, double max) {
        ArrayList<HoaDon_DTO> result = new ArrayList<>();
        String sql = """
            SELECT * FROM HoaDon
            WHERE TongTien BETWEEN ? AND ? AND TrangThaiPhu = 1
        """;

        try (Connection con = ConnectDatabase.getconection();
             PreparedStatement pre = con.prepareStatement(sql)) {

            pre.setDouble(1, min);
            pre.setDouble(2, max);

            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                result.add(mapResultToHoaDon(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    // Lọc kết hợp cả ngày và tiền
    public ArrayList<HoaDon_DTO> filterByDateAndTotal(LocalDateTime from, LocalDateTime to, double min, double max) {
        ArrayList<HoaDon_DTO> result = new ArrayList<>();
        String sql = """
            SELECT * FROM HoaDon
            WHERE NgayLap BETWEEN ? AND ? 
              AND TongTien BETWEEN ? AND ? 
              AND TrangThaiPhu = 1
        """;

        try (Connection con = ConnectDatabase.getconection();
             PreparedStatement pre = con.prepareStatement(sql)) {

            pre.setTimestamp(1, Timestamp.valueOf(from));
            pre.setTimestamp(2, Timestamp.valueOf(to));
            pre.setDouble(3, min);
            pre.setDouble(4, max);

            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                result.add(mapResultToHoaDon(rs));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }
    
    // ====== HÀM MAP DỮ LIỆU ======
    private HoaDon_DTO mapResultToHoaDon(ResultSet rs) throws SQLException {
        int maHD = rs.getInt("MaHD");
        int maKH = rs.getInt("MaKH");
        int maNV = rs.getInt("MaNV");
        double tongTien = rs.getDouble("TongTien");
        LocalDateTime ngayLap = rs.getTimestamp("NgayLap").toLocalDateTime();

        return new HoaDon_DTO(maHD, maKH, maNV, ngayLap, tongTien);
    }


    


}

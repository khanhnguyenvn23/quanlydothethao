package DAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;

import DTO.*;

public class KhachHang_DAO {

    //Lấy khách hàng từ id
    public KhachHang_DTO getKHbyId(int maKH){
        KhachHang_DTO result = null;
        String sql = "Select MaKH, HoTen, NgaySinh, SDT, Email, DiaChi from KhachHang Where MaKh = ? AND TrangThaiPhu = 1";
        try (Connection con = ConnectDatabase.getconection();
        PreparedStatement pre = con.prepareStatement(sql)){

            pre.setInt(1, maKH);
            ResultSet rs = pre.executeQuery();

           if (rs.next()) {
                int maKhachHang = rs.getInt("MaKH");
                String tenKhachHang = rs.getString("HoTen");
                LocalDate ngaySinh = rs.getDate("NgaySinh").toLocalDate();
                String sdt = rs.getString("SDT");
                String email = rs.getString("Email");
                String diaChi = rs.getString("DiaChi");

            result = new KhachHang_DTO(maKhachHang, tenKhachHang, ngaySinh, sdt, email, diaChi);
        }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}

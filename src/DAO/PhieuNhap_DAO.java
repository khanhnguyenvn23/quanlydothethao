/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;
import DTO.PhieuNhap_DTO;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.time.LocalDateTime;
/**
 *
 * @author khanhnguyen
 */
public class PhieuNhap_DAO {
    public int selectnextid() {
        int id = -1;
        try {
            Connection con = ConnectDatabase.getconection();
            Statement stm = con.createStatement();
            ResultSet rs = stm.executeQuery("SELECT IDENT_CURRENT('phieunhap') + IDENT_INCR('phieunhap') AS NextID");
            rs.next();
            id = rs.getInt(1);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return id;
    }
    
    public boolean insert(PhieuNhap_DTO p) {
    boolean check = false;
    String sql = "INSERT INTO PhieuNhap (NgayNhap, MaNCC, TongTien, TrangThaiPhu) VALUES (?, ?, ?, ?)";

    try (Connection con = ConnectDatabase.getconection();
         PreparedStatement pre = con.prepareStatement(sql)) {

       
        pre.setTimestamp(1, java.sql.Timestamp.valueOf(p.getNgayNhap()));
        pre.setInt(2, p.getmaNCC());
        pre.setDouble(3, p.getTongTien());
        pre.setBoolean(4, true);

        // 3️⃣ Thực thi câu lệnh
        check = pre.executeUpdate() > 0;

    } catch (Exception e) {
        e.printStackTrace();
    }

    return check;
}
public boolean delete(int mp) {
    boolean check = false;
    String sql = "UPDATE phieunhap SET TrangThaiPhu = 0 WHERE MaPhieuNhap = ?";

    try (Connection con = ConnectDatabase.getconection();
         PreparedStatement pre = con.prepareStatement(sql)) {

        pre.setInt(1, mp);
        int rowsAffected = pre.executeUpdate();

        check = (rowsAffected > 0);

    } catch (Exception e) {
        e.printStackTrace();
    }

    return check;
}
public ArrayList<PhieuNhap_DTO> selectAll() {
    ArrayList<PhieuNhap_DTO> list = new ArrayList<>();
    String sql = "SELECT MaPhieuNhap, idnv,NgayNhap, MaNCC, TongTien FROM PhieuNhap WHERE TrangThaiPhu = 1";

    try (Connection con = ConnectDatabase.getconection();
         PreparedStatement pre = con.prepareStatement(sql);
         ResultSet rs = pre.executeQuery()) {

        while (rs.next()) {
            int maPN = rs.getInt("MaPhieuNhap");
            LocalDateTime ngayNhap = rs.getTimestamp("NgayNhap").toLocalDateTime();
            int maNCC = rs.getInt("MaNCC");
            double tongTien = rs.getDouble("TongTien");
             int idnv =rs.getInt("idnv");
            PhieuNhap_DTO pn = new PhieuNhap_DTO(maPN, idnv , maNCC, ngayNhap, tongTien);
            list.add(pn);
        }

    } catch (Exception e) {
        e.printStackTrace();
    }

    return list;
}




}

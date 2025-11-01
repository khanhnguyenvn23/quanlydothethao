package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDateTime;
import DTO.*;

import DTO.ChiNhanh_DTO;
import DTO.HoaDon_DTO;

public class ChiNhanh_DAO {

    //Lấy chi nhánh từ id
    public ChiNhanh_DTO getCNById(int id){
        ChiNhanh_DTO result = null;
        String sql = "SELECT * FROM ChiNhanh WHERE MaChiNhanh = ? AND TrangThaiPhu = 1";

        try (Connection con = ConnectDatabase.getconection();
            PreparedStatement pre = con.prepareStatement(sql)) {
            pre.setInt(1, id); 

            try (ResultSet rs = pre.executeQuery()) {
                if (rs.next()) {
                    int maCN = rs.getInt("MaChiNhanh");
                    String tenCN = rs.getString("TenChiNhanh");
                    String diaChi = rs.getString("DiaChi");
                    String sdt = rs.getString("SDT");

                    result = new ChiNhanh_DTO(maCN, tenCN, sdt, diaChi);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }
}

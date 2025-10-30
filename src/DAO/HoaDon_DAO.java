package DAO;
import DTO.HoaDon_DTO;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.time.LocalDateTime;
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

}

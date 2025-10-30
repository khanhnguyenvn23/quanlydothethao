package BUS;
import DTO.HoaDon_DTO;
import DAO.HoaDon_DAO;
import java.util.ArrayList;
import java.util.List;
public class HoaDon_BUS {
    private HoaDon_DAO hoaDon_DAO = new HoaDon_DAO();

    //Lấy danh sách hoá đơn
    public ArrayList<HoaDon_DTO> getAllHoaDon() {
        return hoaDon_DAO.getAllHoaDon();
    }
    //
}

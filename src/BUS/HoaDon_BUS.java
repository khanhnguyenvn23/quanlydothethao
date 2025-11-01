package BUS;
import DTO.HoaDon_DTO;
import DAO.HoaDon_DAO;
import java.util.ArrayList;
import java.time.LocalDateTime;
public class HoaDon_BUS {
    private HoaDon_DAO hoaDon_DAO = new HoaDon_DAO();

    //Lấy danh sách hoá đơn
    public ArrayList<HoaDon_DTO> getAllHoaDon() {
        return hoaDon_DAO.getAllHoaDon();
    }

    //Tìm kiếm theo mã hoá đơn
    public ArrayList<HoaDon_DTO> searchByMaHD(int maHD) {
        return hoaDon_DAO.searchByMaHD(maHD);
    }

    //Tìm kiếm theo tên nhân viên
    public ArrayList<HoaDon_DTO> searchByTenNhanVien(String tenNV) {
        return hoaDon_DAO.searchByTenNhanVien(tenNV);
    }

    //Lấy hoá đơn từ id
    public HoaDon_DTO getHoaDonById(int maHD){
        HoaDon_DAO hdDAO = new HoaDon_DAO();
        return  hdDAO.getHoaDonById(maHD);
    }

    //Tìm kiếm theo tên khách hàng
    public ArrayList<HoaDon_DTO> searchByTenKhachHang(String tenKH) {
        return hoaDon_DAO.searchByTenKhachHang(tenKH);
    }

    public ArrayList<HoaDon_DTO> filterByDate(LocalDateTime from, LocalDateTime to) {
        return hoaDon_DAO.filterByDate(from, to);
    }

    public ArrayList<HoaDon_DTO> filterByTotal(double min, double max) {
        return hoaDon_DAO.filterByTotal(min, max);
    }

    public ArrayList<HoaDon_DTO> filterByDateAndTotal(LocalDateTime from, LocalDateTime to, double min, double max) {
        return hoaDon_DAO.filterByDateAndTotal(from, to, min, max);
    }
}

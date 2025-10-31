package BUS;

import DAO.ChiTietHoaDon_DAO;
import DTO.ChiTietHoaDon_DTO;
import java.util.ArrayList;

public class ChiTietHoaDon_BUS {
    private ChiTietHoaDon_DAO dao = new ChiTietHoaDon_DAO();

    //Lấy tất cả chi tiết hoá đơn (thường không dùng)
    public ArrayList<ChiTietHoaDon_DTO> getAll() {
        return dao.getAll();
    }

    //Lấy chi tiết hoá đơn từ mã hoá đơn
    public ArrayList<ChiTietHoaDon_DTO> getByMaHD(int maHD) {
        return dao.getByMaHD(maHD);
    }

    //Thêm chi tiết hoá đơn
    public boolean insert(ChiTietHoaDon_DTO cthd) {
        return dao.insert(cthd);
    }

    //Sữa chi tiết hoá đơn (chắc chắn không dùng)
    public boolean update(ChiTietHoaDon_DTO cthd) {
        return dao.update(cthd);
    }

    //Xoá chi tiết hoá đơn (chắc chắn không dùng)
    public boolean delete(int maHD, int maSP) {
        return dao.delete(maHD, maSP);
    }
}

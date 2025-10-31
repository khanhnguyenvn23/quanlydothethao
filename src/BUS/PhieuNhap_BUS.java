package BUS;
import DTO.PhieuNhap_DTO;
import DAO.PhieuNhap_DAO;
import java.util.ArrayList;
public class PhieuNhap_BUS {
    public PhieuNhap_DAO pndao= new PhieuNhap_DAO();

    
    public int selectNextid(){
        return pndao.selectnextid();
    }
   
    public boolean insert(PhieuNhap_DTO p){
        return pndao.insert(p);
    }


    public ArrayList<PhieuNhap_DTO> selectall() {
        return pndao.selectAll();
    }
     public boolean delete(int mp) {
        return pndao.delete(mp);
    }
    
public static void main(String[] args) {
        PhieuNhap_BUS pnbus = new PhieuNhap_BUS();

        // Ví dụ: thử xoá phiếu nhập có id = 2
        boolean kq = pnbus.delete(0);

        if (kq) {
            System.out.println("Đã xoá phiếu nhập có mã 2 thành công.");
        } else {
            System.out.println("Xoá thất bại hoặc không tìm thấy mã 2.");
        }

        // Hoặc in thử danh sách phiếu nhập
        for (PhieuNhap_DTO pn : pnbus.selectall()) {
            System.out.println(pn.getMaPN() + " - " + pn.getmaNCC() + " - " + pn.getmaNV());
        }
}
}

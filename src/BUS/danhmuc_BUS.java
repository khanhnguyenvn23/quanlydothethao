package BUS;

import java.util.ArrayList;
import java.util.List;

import DAO.danhmuc_DAO;
import DTO.DanhMuc_DTO;

public class danhmuc_BUS {
        public List<DanhMuc_DTO> selectAll(){
         try {
             return new danhmuc_DAO().selectAll();
         } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
         }
        }
 public boolean insert(DanhMuc_DTO dm){
     return new danhmuc_DAO().insert(dm);
 }
 public boolean update(DanhMuc_DTO dm){
     return new danhmuc_DAO().update(dm);
 }
 public boolean delete(int MaDM){
     return new danhmuc_DAO().delete(MaDM);
 }
 public List<DanhMuc_DTO> search(String tukhoa){
         try {
             return new danhmuc_DAO().search(tukhoa);
         } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
         }
        }
}

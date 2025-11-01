package BUS;

import DAO.*;
import DTO.*;

public class ChiNhanh_BUS {
    private ChiNhanh_DTO cnDTO = new ChiNhanh_DTO();
    ChiNhanh_DAO cnDAO = new ChiNhanh_DAO();

    // Lấy chi nhánh dựa trên mã

    public ChiNhanh_DTO getCNById(int id){
        cnDTO = cnDAO.getCNById(id);
        return cnDTO;
    }
    
}

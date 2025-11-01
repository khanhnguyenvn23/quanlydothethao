package GUI;
import javax.swing.*;
import java.awt.*;

import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumnModel;

import BUS.ChiTietPhieuNhap_BUS;
import BUS.SanPham_BUS;
import DTO.ChiTietPhieuNhap_DTO;
public class ChiTietPhieuNhap extends JFrame {
       JTable jt;
       DefaultTableModel model= new DefaultTableModel();
      JLabel tieude, tongtien;
      int idpn;
   
   public ChiTietPhieuNhap(int idpn){
       this.idpn=idpn;
       initcomponent();
   }

   public void initcomponent(){
    this.setSize(900,300);
    this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    this.setLocationRelativeTo(null);

    JPanel jp = new JPanel(new BorderLayout());
     // Tiêu đề
    JLabel lblTieuDe = new JLabel("Chi tiết phiếu nhập", JLabel.CENTER);
    lblTieuDe.setFont(new Font("Arial", Font.BOLD, 24));
    lblTieuDe.setForeground(Color.BLACK);
    lblTieuDe.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
    jp.add(lblTieuDe, BorderLayout.NORTH);

    
     model = new DefaultTableModel() {
        @Override
        public boolean isCellEditable(int row, int column) {
            return false;
        }
    };
       jt = new JTable(model);

    model.addColumn("Mã phiếu nhập");
    model.addColumn("Tên sản phẩm");
    model.addColumn("Số lượng");
    model.addColumn("Đơn giá");
   

// Set width các cột
    TableColumnModel columnModel = jt.getColumnModel();
    columnModel.getColumn(0).setPreferredWidth(50);   // id
    columnModel.getColumn(1).setPreferredWidth(300);  // Tên sản phẩm
    columnModel.getColumn(2).setPreferredWidth(100);  // Số lượng
    columnModel.getColumn(3).setPreferredWidth(100);  // Đơn giá

    // Căn giữa dữ liệu trong bảng
    DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
    centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
    for (int i = 0; i < jt.getColumnCount(); i++) {
        jt.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
    }
 // Tùy chỉnh header bảng
    JTableHeader header = jt.getTableHeader();
    header.setBackground(new Color(63, 81, 181)); // Màu xanh đậm
    header.setForeground(Color.WHITE);            // Chữ trắng
    header.setFont(new Font("Arial", Font.BOLD, 13));
    header.setReorderingAllowed(false);
    JScrollPane j = new JScrollPane(jt);
    jp.add(j, BorderLayout.CENTER);
    this.add(jp);

   this.setVisible(true);
      // Load dữ liệu
    loaddatatotable();

   }
   

    public void loaddatatotable() {
        ChiTietPhieuNhap_BUS ct = new ChiTietPhieuNhap_BUS();
        SanPham_BUS spbus = new SanPham_BUS();
        String[] data = new String[4];
        for (ChiTietPhieuNhap_DTO i : ct.getChiTietByMaPhieuNhap(this.idpn)) {
            data[0] = String.valueOf(this.idpn);
            data[1] = spbus.getSanPhamById(i.getMaSP()).getTenSP();
            data[2] = String.valueOf(i.getSoLuongNhap());
              data[3] = String.format("%.0f", i.getDonGianHap()); 
            model.addRow(data);
        }
    }
   public static void main(String args[]){
   ChiTietPhieuNhap ctpn= new ChiTietPhieuNhap(3);
   }
}

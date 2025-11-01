package GUI;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import BUS.SanPham_BUS;
import DAO.NhaCungCap_DAO;
import BUS.NhaCungCap_BUS;
import BUS.PhieuNhap_BUS;
import BUS.NhanVien_BUS;
import BUS.ChiTietPhieuNhap_BUS;
import DTO.NCC_DTO;
import DTO.PhieuNhap_DTO;
import DTO.SanPham_DTO;
import DTO.ChiTietPhieuNhap_DTO;
public class ThemPhieuNhap extends JFrame {
    int idnv ;
   
    JButton themsp,btnXoaSP, btnLuuPhieu;
    JTextField soluong,gianhaphang,idphieu,tennhanvien,masp;
    JComboBox<NCC_DTO>nhacungcap; 
    JTable sp, listsp;
    DefaultTableModel model1, model2;
    NhaCungCap_BUS nccbus= new NhaCungCap_BUS();
    SanPham_BUS spbus=new SanPham_BUS();
    PhieuNhap_BUS pnbus=new PhieuNhap_BUS();
    NhanVien_BUS nvbus= new NhanVien_BUS();
    ChiTietPhieuNhap_BUS ctpnbus= new ChiTietPhieuNhap_BUS();
   public ThemPhieuNhap(int idnv){
    this.idnv=idnv;
       this.setSize(1000,800);
       this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setLocationRelativeTo(null);
        //dùng grrid layout cho frame cha;
        this.setLayout(new GridLayout(2,1));

        //tạo layout cho phần trên gồm một bảng sản phẩm và một form thông tin bằng grid layout

         JPanel toppanel= new JPanel();
         toppanel.setLayout(new GridLayout(1,2,0,0));
         toppanel.setPreferredSize(new Dimension(1000, 400));

         JPanel lefttop= new JPanel();
          JPanel righttop= new JPanel();
         
        righttop.setBackground(new Color(255, 255, 255));
         toppanel.add(lefttop);
           toppanel.add(righttop);
        // PHẦN DƯỚI: chỉ 1 panel
        // để làm chi tiết list sp
        JPanel bottomPanel = new JPanel();
        bottomPanel.setBackground(Color.LIGHT_GRAY);
        this.add(toppanel);
        this.add(bottomPanel);
// add bảng sản phẩm vào panel
   lefttop.setLayout(new BorderLayout());
   lefttop.setBorder(BorderFactory.createTitledBorder("Danh sách sản phẩm"));
   //add form thông tin vào bên phải 
   righttop.setLayout(new BorderLayout());


// Panel con để combobox sát trái
JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
nhacungcap = new JComboBox<>();
nhacungcap.setPreferredSize(new Dimension(120, 25)); // set size mong muốn
topPanel.add(nhacungcap);

// Thêm panel con vào BorderLayout.NORTH hoặc CENTER
righttop.add(topPanel, BorderLayout.NORTH);
// Panel con chứa form (sử dụng GridBagLayout)
JPanel formPanel = new JPanel();
formPanel.setLayout(new GridBagLayout());
formPanel.setBorder(new EmptyBorder(0, 0, 0, 0)); // optional

GridBagConstraints gbc = new GridBagConstraints();
gbc.insets = new Insets(30, 10, 10, 10); // khoảng cách giữa các hàng
gbc.fill = GridBagConstraints.HORIZONTAL;
gbc.anchor = GridBagConstraints.WEST;

// Hàng 1 - Mã Phiếu
gbc.gridx = 0; gbc.gridy = 0;
formPanel.add(new JLabel("Mã Phiếu"), gbc);

gbc.gridx = 1; gbc.gridy = 0;
idphieu = new JTextField(15);
idphieu.setEditable(false);
formPanel.add(idphieu, gbc);

// Hàng 2 - Nhân viên nhập
gbc.gridx = 0; gbc.gridy = 1;
formPanel.add(new JLabel("Nhân viên nhập"), gbc);

gbc.gridx = 1; gbc.gridy = 1;
tennhanvien = new JTextField(15);
tennhanvien.setEditable(false);
formPanel.add(tennhanvien, gbc);

// Hàng 3 - Số lượng thêm
gbc.gridx = 0; gbc.gridy = 2;
formPanel.add(new JLabel("Số lượng thêm"), gbc);

gbc.gridx = 1; gbc.gridy = 2;
soluong = new JTextField(15);
formPanel.add(soluong, gbc);

// Hàng 4 - Giá nhập
gbc.gridx = 0; gbc.gridy = 3;
formPanel.add(new JLabel("Giá nhập"), gbc);

gbc.gridx = 1; gbc.gridy = 3;
gianhaphang = new JTextField(15);
formPanel.add(gianhaphang, gbc);
gbc.gridx = 0;
gbc.gridy = 4;
gbc.gridwidth = 2;
gbc.fill = GridBagConstraints.NONE; // không giãn ngang
gbc.insets = new Insets(20, 10, 10, 10);
gbc.ipadx = 20; // tăng chiều ngang bên trong
gbc.ipady = 5;  // tăng chiều cao bên trong
gbc.anchor = GridBagConstraints.CENTER; // căn giữa ô

JButton btnLuu = new JButton("Lưu");
formPanel.add(btnLuu, gbc);
// righttop.setBackground(new Color(200, 230, 255));

// topPanel.setBackground(new Color(200, 230, 255));
// formPanel.setBackground(new Color(200, 230, 255));
// Thêm panel form vào righttop
righttop.add(formPanel, BorderLayout.CENTER);
loaddatatoformloc();



//==== bảng sản phẩm
   model1 = new DefaultTableModel() {
    @Override
    public boolean isCellEditable(int row, int column) {
        return false; // không cho chỉnh sửa
    }
};

        model1.addColumn("Mã SP");
        model1.addColumn("Tên SP");
        model1.addColumn("size");
        model1.addColumn("màu");
        model1.addColumn("nhà cung cấp");
         sp = new JTable(model1);
        sp.setFillsViewportHeight(true); // chiếm trọn chiều cao
        sp.getTableHeader().setReorderingAllowed(false);
         JScrollPane scrollPane = new JScrollPane(sp);
        lefttop.add(scrollPane, BorderLayout.CENTER);
           loadsanphamtotable();

 // ==== BẢNG DƯỚI (CHI TIẾT NHẬP) ====
 bottomPanel.setLayout(new BorderLayout());
  model2 = new DefaultTableModel() {
    @Override
    public boolean isCellEditable(int row, int column) {
        return false;
    }
};


        model2.addColumn("Mã SP");
        model2.addColumn("Tên SP");
        model2.addColumn("Số lượng");
        model2.addColumn("Giá nhập");
        model2.addColumn("Tổng tiền");
        model2.addColumn("nhà cung cấp");
        listsp = new JTable(model2);
        listsp.getTableHeader().setReorderingAllowed(false);
        bottomPanel.add(new JScrollPane(listsp), BorderLayout.CENTER);

        JPanel btnPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 10));
        btnXoaSP = new JButton("Xóa sản phẩm");
        btnLuuPhieu = new JButton("Lưu phiếu nhập");
        btnPanel.add(btnXoaSP);
        btnPanel.add(btnLuuPhieu);
        bottomPanel.add(btnPanel, BorderLayout.NORTH);


//sự kiện cho các nút
nhacungcap.addActionListener(e -> {
    NCC_DTO selected = (NCC_DTO) nhacungcap.getSelectedItem();
    if (selected != null) {
        if (selected.getMaNCC() == 0) { // chọn "Tất cả"
            loadsanphamtotable();
        } else {
            loadsanphamtotable(selected.getMaNCC());
        }
    }
});
btnXoaSP.addActionListener(e->{
     int selecttablemodel2=listsp.getSelectedRow();
     if(selecttablemodel2==-1){
        JOptionPane.showMessageDialog(this,"mời nhập sản phẩm cần xóa khỏi bảng chi tiết");
        return ;
     }
     model2.removeRow(selecttablemodel2);
     
});



btnLuu.addActionListener(e -> {
    int selectedRow = sp.getSelectedRow();
    if (selectedRow == -1) {
        JOptionPane.showMessageDialog(this, "Vui lòng chọn sản phẩm cần nhập!");
        return;
    }

    String maSP = model1.getValueAt(selectedRow, 0).toString();
    String tenSP = model1.getValueAt(selectedRow, 1).toString();
    String tenNCC = model1.getValueAt(selectedRow, 4).toString();

    if (soluong.getText().isEmpty() || gianhaphang.getText().isEmpty()) {
        JOptionPane.showMessageDialog(this, "Vui lòng nhập số lượng và giá nhập!");
        return;
    }

    try {
        int sl = Integer.parseInt(soluong.getText());
        double gia = Double.parseDouble(gianhaphang.getText());
        double tong = sl * gia;

        // Kiểm tra nhà cung cấp
        if (model2.getRowCount() > 0) {
            String nccCu = model2.getValueAt(0, 5).toString();
            if (!nccCu.equals(tenNCC)) {
                JOptionPane.showMessageDialog(this, "Không thể nhập sản phẩm từ hai nhà cung cấp khác nhau!");
                return;
            }
        }

        // Thêm sản phẩm vào bảng chi tiết
       String giaStr = String.format("%.0f", gia);
   String tongStr = String.format("%.0f", tong);
     model2.addRow(new Object[]{maSP, tenSP, sl, giaStr, tongStr, tenNCC});
for (int i = 0; i < nhacungcap.getItemCount(); i++) {
    NCC_DTO ncc = nhacungcap.getItemAt(i);
    if (ncc.getTenNCC().equals(tenNCC)) {
        nhacungcap.setSelectedIndex(i);
        break;
    }
}
        // Xóa form sau khi thêm
        soluong.setText("");
        gianhaphang.setText("");
    } catch (NumberFormatException ex) {
        JOptionPane.showMessageDialog(this, "Số lượng hoặc giá nhập không hợp lệ!");
    }
});

// nút lưu phiếu nhập 
btnLuuPhieu.addActionListener(e -> {
    if (model2.getRowCount() == 0) {
        JOptionPane.showMessageDialog(this, "Chưa có sản phẩm nào trong phiếu!");
        return;
    }
     
    int idncc=nccbus.layIdtheotenncc(model2.getValueAt(0,5).toString());
    

    double tongTien = 0;
    for (int i = 0; i < model2.getRowCount(); i++) {
        tongTien += Double.parseDouble(model2.getValueAt(i, 4).toString());
    }

 

       int maPN = Integer.parseInt(idphieu.getText());
     PhieuNhap_DTO pn = new PhieuNhap_DTO(
        maPN,
        idnv,
        idncc,
        java.time.LocalDateTime.now(),
        tongTien
    );

       if (pnbus.insert(pn)) {
        for (int i = 0; i < model2.getRowCount(); i++) {
            int maSP = Integer.parseInt(model2.getValueAt(i, 0).toString());
            int soLuong = Integer.parseInt(model2.getValueAt(i, 2).toString());
            double giaNhap = Double.parseDouble(model2.getValueAt(i, 3).toString());
            double tong = Double.parseDouble(model2.getValueAt(i, 4).toString());
            ctpnbus.addChiTietPhieuNhap(new ChiTietPhieuNhap_DTO(maPN, maSP, soLuong, giaNhap, tong,spbus.getSanPhamById(maSP).getSize()));
        }

        JOptionPane.showMessageDialog(this, "Thêm phiếu nhập thành công!");
        model2.setRowCount(0);
        idphieu.setText(String.valueOf(pnbus.selectNextid()));
    } else {
        JOptionPane.showMessageDialog(this, "Lưu phiếu nhập thất bại!");
    }
});







this.setVisible(true);

}

public void loaddatatoformloc(){
    int idpnnext=pnbus.selectNextid();
    idphieu.setText(String.valueOf(idpnnext));
    nhacungcap.addItem(new NCC_DTO(0, "Tất cả", "", "")); // giả sử NCC_DTO có constructor (id, tên, sđt, địa chỉ)
 for(NCC_DTO i: nccbus.getListNCC()){
    nhacungcap.addItem(i);
 }
  tennhanvien.setText(nvbus.getTenNVById(this.idnv));


}
// Lọc sản phẩm theo mã nhà cung cấp
public void loadsanphamtotable(int maNCC) {
    model1.setRowCount(0); // xóa dữ liệu cũ
    for (SanPham_DTO i : spbus.getAllSanPham()) {
        if (i.getMaNCC() == maNCC) { // chỉ lấy sản phẩm thuộc NCC này
            String[] data = new String[5];
            data[0] = String.valueOf(i.getMaSP());
            data[1] = i.getTenSP();
            data[2] = String.valueOf(i.getSize());
            data[3] = i.getMauSac();
            data[4] = nccbus.getTenNCCById(i.getMaNCC());
            model1.addRow(data);
        }
    }
}

   public void loadsanphamtotable(){
      model1.setRowCount(0); 
    for(SanPham_DTO i:spbus.getAllSanPham()){
        String[]data = new String[5];
        data[0]=String.valueOf(i.getMaSP());
        data[1]=i.getTenSP();
        data[2]=String.valueOf(i.getSize());
        data[3]=i.getMauSac();
        data[4]=nccbus.getTenNCCById(i.getMaNCC());
        model1.addRow(data);
        
    }

   }


public static void main(String[] args) {
    new ThemPhieuNhap(3);
}

}
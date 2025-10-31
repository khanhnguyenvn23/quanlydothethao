/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI;
import com.toedter.calendar.JDateChooser;

import BUS.ChiTietPhieuNhap_BUS;
import BUS.NhaCungCap_BUS;
import BUS.NhanVien_BUS;
import BUS.PhieuNhap_BUS;
import BUS.SanPham_BUS;
import DTO.ChiTietPhieuNhap_DTO;
import DTO.PhieuNhap_DTO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.net.URL;
import java.time.format.DateTimeFormatter;
import java.time.LocalDate;
import java.time.ZoneId;

public class PhieuNhap extends JPanel {
   
 JDateChooser fromDateChooser = new JDateChooser();
JDateChooser toDateChooser = new JDateChooser();
JTextField minAmountField = new JTextField();
JTextField maxAmountField = new JTextField();
JComboBox<String> cbNV = new JComboBox<>();
JComboBox<String> cbNCC = new JComboBox<>();

 PhieuNhap_BUS pnbus = new PhieuNhap_BUS();
 NhanVien_BUS nvbus = new NhanVien_BUS();
 NhaCungCap_BUS nccbus = new NhaCungCap_BUS();
 SanPham_BUS spbus =new SanPham_BUS();
 ChiTietPhieuNhap_BUS ctpnbus= new ChiTietPhieuNhap_BUS();
 
 DefaultTableModel model = new DefaultTableModel() {
    @Override
    public boolean isCellEditable(int row, int column) {
        return false; // tất cả các ô đều không chỉnh sửa được
    }
};
   JTable table = new JTable(model);

 
    public PhieuNhap() {
        // Sử dụng BorderLayout với khoảng cách 10 pixel
        setLayout(new BorderLayout(10, 10));

        // --------- PHẦN NÚT CHỨC NĂNG (TOP) -----------
        JPanel P = new JPanel(new BorderLayout());
        JPanel P1 = new JPanel(new FlowLayout(FlowLayout.LEFT));
        
        // Nút Thêm
        ImageIcon addIcon = resizeimg(new ImageIcon(getClass().getResource("/icon/them.png")));
        JButton btnthem = createIconButton("Thêm", addIcon);
        btnthem.setOpaque(false);
        btnthem.setFocusPainted(false);
        btnthem.setBorderPainted(false);
        
        // Thêm sự kiện cho nút Thêm
        btnthem.addActionListener(e -> {
          
        });
        
        P1.add(btnthem);

        // Nút Chi tiết
        ImageIcon chitieticon = resizeimg(new ImageIcon(getClass().getResource("/icon/chitiet.png")));
        JButton btnchitiet = createIconButton("Chi tiêt", chitieticon);
        btnchitiet.setOpaque(false);
        btnchitiet.setFocusPainted(false);
        btnchitiet.setBorderPainted(false);
        P1.add(btnchitiet);

        // Nút Hủy phiếu
        ImageIcon huyphieuicon = resizeimg(new ImageIcon(getClass().getResource("/icon/huyphieu.png")));
        JButton btnhuyphieu = createIconButton("Hủy phiếu", huyphieuicon);
        btnhuyphieu.setOpaque(false);
        btnhuyphieu.setFocusPainted(false);
        btnhuyphieu.setBorderPainted(false);
        P1.add(btnhuyphieu);

        // Nút Xuất Excel
       

        // Nút tìm kiếm
        ImageIcon lmcon = resizeimg(new ImageIcon(getClass().getResource("/icon/loupe.png")));
        JButton btnlm = createIconButton("tìm kiếm", lmcon);
        btnlm.setOpaque(false);
        btnlm.setFocusPainted(false);
        btnlm.setVerticalTextPosition(SwingConstants.CENTER);
        btnlm.setHorizontalTextPosition(SwingConstants.RIGHT);

        // Panel chứa công cụ tìm kiếm (bên phải của thanh chức năng)
        JPanel P2 = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        String[] cb = {"Tất Cả", "Mã phiếu nhập", "Nhà cung cấp", "Nhân viên nhập"};
        JComboBox<String> pl = new JComboBox<>(cb);
        pl.setPreferredSize(new Dimension(100, 40));
        JTextField tf = new JTextField(20);
        tf.setText("Nhập nội dung tìm kiếm...");
        tf.setForeground(Color.GRAY);
// Thêm FocusListener để xử lý placeholder
tf.addFocusListener(new java.awt.event.FocusAdapter() {
    @Override
    public void focusGained(java.awt.event.FocusEvent e) {
        if (tf.getText().equals("Nhập nội dung tìm kiếm...")) {
            tf.setText("");
            tf.setForeground(Color.BLACK);
        }
    }

    @Override
    public void focusLost(java.awt.event.FocusEvent e) {
        if (tf.getText().isEmpty()) {
            tf.setText("Nhập nội dung tìm kiếm...");
            tf.setForeground(Color.GRAY);
        }
    }
});
//khóa cột bảng
 table.getTableHeader().setReorderingAllowed(false);


 
   //=====sự kiện nút xem chi tiết======
   btnchitiet.addActionListener(e->{
    if(table.getSelectedRow()==-1){
     JOptionPane.showMessageDialog(null, "Vui lòng chọn phiếu muốn xem chi tiết");
     return; 
    }
    int id = Integer.parseInt(model.getValueAt(table.getSelectedRow(), 0).toString());
     new ChiTietPhieuNhap(id);
   });



//===sự kiện nút hủy phiếu===
     btnhuyphieu.addActionListener(e->{
      if(table.getSelectedRow()==-1){
        JOptionPane.showMessageDialog(null,"vui lòng chọn phiếu muốn hủy");
        return;
      }
        int result = JOptionPane.showConfirmDialog(null, "Bạn có chắc chắn muốn hủy không?", "Xác nhận", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
      if (result == JOptionPane.YES_OPTION) {
       // lấy mã id phiếu để hủy
      int idpnxoa=Integer.parseInt(model.getValueAt(table.getSelectedRow(),0 ).toString());
    
        for(ChiTietPhieuNhap_DTO i: ctpnbus.getChiTietByMaPhieuNhap(idpnxoa)){
            spbus.updateSoLuongTon(i.getMaSP(),-(i.getSoLuongNhap()));
        }
             pnbus.delete(idpnxoa);
     loaddatatotable();
     JOptionPane.showMessageDialog(null, "hủy thành công, số lượng sản phẩm đã cập nhật");
    }
     });

   // ====== SỰ KIỆN NÚT TÌM KIẾM ======
btnlm.addActionListener(e -> {
    String selected = (String) pl.getSelectedItem(); // Lấy lựa chọn trong combobox
    String keyword = tf.getText().trim();            // Lấy nội dung người dùng nhập

    // Nếu chọn "Tất Cả" thì hiển thị lại toàn bộ
    if (selected.equals("Tất Cả")) {
        tf.setText("");
        loaddatatotable();
        return;
    }

    // Kiểm tra nhập liệu rỗng (ngoại trừ "Tất Cả")
    if (keyword.equals("") || keyword.equals("Nhập nội dung tìm kiếm...")) {
        JOptionPane.showMessageDialog(null, "Vui lòng nhập nội dung tìm kiếm!");
        return;
    }

    // Xóa dữ liệu cũ
    model.setRowCount(0);

    // Lấy dữ liệu từ BUS
 

    // Lặp qua danh sách phiếu nhập
    for (PhieuNhap_DTO i : pnbus.selectall()) {
        boolean match = false;

        switch (selected) {
            case "Mã phiếu nhập":
                String maPN = "PN" + String.format("%03d", i.getMaPN());
                match = maPN.toLowerCase().contains(keyword.toLowerCase());
                break;

            case "Nhà cung cấp":
                String tenNCC = nccbus.getTenNCCById(i.getmaNCC());
                match = tenNCC.toLowerCase().contains(keyword.toLowerCase());
                break;

            case "Nhân viên nhập":
                String tenNV = nvbus.getTenNVById(i.getmaNV());
                match = tenNV.toLowerCase().contains(keyword.toLowerCase());
                break;
        }

        // Nếu khớp điều kiện → thêm vào bảng
        if (match) {
            String tenNCC = nccbus.getTenNCCById(i.getmaNCC());
            String tenNV = nvbus.getTenNVById(i.getmaNV());
            
            model.addRow(new Object[]{
                "" + String.format("%d", i.getMaPN()),
                tenNCC.isEmpty() ? "N/A" : tenNCC,
                tenNV.isEmpty() ? "Chưa có thông tin" : tenNV,
                i.getNgayNhap().format(java.time.format.DateTimeFormatter.ofPattern("dd/MM/yyyy")),
                String.format("%,.0f VND", i.getTongTien())
            });
        }
    }

    // Nếu không có kết quả
    if (model.getRowCount() == 0) {
        JOptionPane.showMessageDialog(null, "Không tìm thấy kết quả phù hợp!");
    }
});


       

        tf.setPreferredSize(new Dimension(100, 40));
        P2.add(pl);
        P2.add(tf);
        P2.add(btnlm);

        // Ghép hai panel con vào panel P
        P.add(P1, BorderLayout.WEST);
        P.add(P2, BorderLayout.EAST);

        // Thêm panel chứa các nút chức năng vào phần NORTH của giao diện
        add(P, BorderLayout.NORTH);

        // --------- PHẦN GIAO DIỆN CHÍNH -----------
        // Tạo một panel trung tâm để chứa cả bộ lọc tìm kiếm và khu vực hiển thị nội dung
        JPanel centerPanel = new JPanel(new BorderLayout(10, 10));

        // Thêm bộ lọc tìm kiếm vào phần WEST
        JPanel filterPanel = createLeftFilterPanel();
        centerPanel.add(filterPanel, BorderLayout.WEST);

        // Ví dụ: Thêm bảng dữ liệu vào phần CENTER (bạn có thể thay bảng mẫu này bằng bảng của bạn)
        
        model.addColumn("Mã phiếu nhập");
        model.addColumn("Nhà cung cấp");
        model.addColumn("Nhân viên nhập");
        model.addColumn("Ngày");
        model.addColumn("Tổng tiền");

        // Thêm một số dòng mẫu (các dòng này chỉ để minh họa)
      

        
        JScrollPane scrollPane = new JScrollPane(table);
        centerPanel.add(scrollPane, BorderLayout.CENTER);

        // Thêm centerPanel vào phần CENTER của giao diện chính
        add(centerPanel, BorderLayout.CENTER);
             loaddatatotable();
        setVisible(true);
    }

    // Phương thức thay đổi kích thước icon
    public ImageIcon resizeimg(ImageIcon img) {
        Image tmp = img.getImage();
        Image tmp2 = tmp.getScaledInstance(30, 30, Image.SCALE_SMOOTH);
        return new ImageIcon(tmp2);
    }

    // Tạo nút có icon và text
    private JButton createIconButton(String text, ImageIcon icon) {
        JButton button = new JButton(text);
        if (icon != null) {
            button.setIcon(icon);
        }
        button.setVerticalTextPosition(SwingConstants.BOTTOM);
        button.setHorizontalTextPosition(SwingConstants.CENTER);
        return button;
    }

    // Phương thức tạo bộ lọc tìm kiếm ở bên trái giao diện
   private JPanel createLeftFilterPanel() {
    JPanel leftPanel = new JPanel(new GridBagLayout());
    leftPanel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createTitledBorder("Bộ lọc tìm kiếm"),
            new EmptyBorder(5, 5, 5, 10)
    ));

    GridBagConstraints gbc = new GridBagConstraints();
    gbc.fill = GridBagConstraints.HORIZONTAL;
    gbc.insets = new Insets(5, 5, 5, 5);
    gbc.anchor = GridBagConstraints.WEST;
    gbc.gridx = 0;

    // --- Nhà cung cấp ---
    gbc.gridy = 0;
    leftPanel.add(new JLabel("Nhà cung cấp:"), gbc);
    gbc.gridy = 1;
  

// Tạo combobox và load dữ liệu từ BUS

cbNCC.addItem("Tất cả");
NhaCungCap_BUS nccbus = new NhaCungCap_BUS();
for (var ncc : nccbus.selectAll()) {
    cbNCC.addItem(ncc.getTenNCC());
}
leftPanel.add(cbNCC, gbc);

// --- Nhân viên nhập ---
gbc.gridy = 2;
leftPanel.add(new JLabel("Nhân viên nhập:"), gbc);
gbc.gridy = 3;

// Tạo combobox và load dữ liệu từ BUS

cbNV.addItem("Tất cả");
NhanVien_BUS nvbus = new NhanVien_BUS();
for (var nv : nvbus.getListNV()) {
    cbNV.addItem(nv.getHoTen());
}
leftPanel.add(cbNV, gbc);
  


gbc.fill = GridBagConstraints.HORIZONTAL;
gbc.gridx = 0;

// --- Từ ngày ---
gbc.gridy = 4;
leftPanel.add(new JLabel("Từ ngày:"), gbc);
gbc.gridy = 5;
leftPanel.add(fromDateChooser, gbc);

// --- Đến ngày ---
gbc.gridy = 6;
leftPanel.add(new JLabel("Đến ngày:"), gbc);
gbc.gridy = 7;
leftPanel.add(toDateChooser, gbc);

// --- Từ số tiền ---
gbc.gridy = 8;
leftPanel.add(new JLabel("Từ số tiền (VND):"), gbc);
gbc.gridy = 9;
leftPanel.add(minAmountField, gbc);

// --- Đến số tiền ---
gbc.gridy = 10;
leftPanel.add(new JLabel("Đến số tiền (VND):"), gbc);
gbc.gridy = 11;
leftPanel.add(maxAmountField, gbc);

    // --- Nút tìm kiếm nâng cao ---
    gbc.gridy = 12;
    gbc.fill = GridBagConstraints.NONE;
    gbc.anchor = GridBagConstraints.CENTER;
    ImageIcon timkiem2 = resizeimg(new ImageIcon(getClass().getResource("/icon/loupe.png")));
    JButton btntimkiemnangcao = createIconButton("Tìm kiếm nâng cao", timkiem2);
    btntimkiemnangcao.setOpaque(false);
    btntimkiemnangcao.setFocusPainted(false);
    btntimkiemnangcao.setBorderPainted(false);
    leftPanel.add(btntimkiemnangcao, gbc);

    // --- Dòng trống cuối cùng đẩy nhẹ mọi thứ lên ---
    gbc.gridy = 13;
    gbc.weighty = 1.0;
    gbc.fill = GridBagConstraints.VERTICAL;
    leftPanel.add(Box.createVerticalStrut(10), gbc);

    // Giới hạn chiều rộng panel
    leftPanel.setPreferredSize(new Dimension(220, 0));

btntimkiemnangcao.addActionListener(e -> {
    model.setRowCount(0); // Xóa dữ liệu cũ

    String nccSelected = (String) cbNCC.getSelectedItem();
    String nvSelected = (String) cbNV.getSelectedItem();
    java.util.Date fromDate = fromDateChooser.getDate();
    java.util.Date toDate = toDateChooser.getDate();
    String minAmountText = minAmountField.getText().trim();
    String maxAmountText = maxAmountField.getText().trim();

    double minAmount = 0, maxAmount = Double.MAX_VALUE;
    try {
        if (!minAmountText.isEmpty()) minAmount = Double.parseDouble(minAmountText);
        if (!maxAmountText.isEmpty()) maxAmount = Double.parseDouble(maxAmountText);
    } catch (NumberFormatException ex) {
        JOptionPane.showMessageDialog(null, "Số tiền không hợp lệ!");
        return;
    }

   
    java.time.format.DateTimeFormatter dtf = java.time.format.DateTimeFormatter.ofPattern("dd/MM/yyyy");

    for (PhieuNhap_DTO i : pnbus.selectall()) {
        boolean match = true;

        // Lọc theo Nhà cung cấp
        if (!nccSelected.equals("Tất cả")) {
            String tenNCC = nccbus.getTenNCCById(i.getmaNCC());
            if (!tenNCC.equals(nccSelected)) {
                match = false;
            }
        }

        // Lọc theo Nhân viên
        if (!nvSelected.equals("Tất cả")) {
            String tenNV = nvbus.getTenNVById(i.getmaNV());
            if (!tenNV.equals(nvSelected)) {
                match = false;
            }
        }

        // Lọc theo ngày
        if (fromDate != null || toDate != null) {
            java.time.LocalDate from = (fromDate != null) ? fromDate.toInstant().atZone(java.time.ZoneId.systemDefault()).toLocalDate() : null;
            java.time.LocalDate to = (toDate != null) ? toDate.toInstant().atZone(java.time.ZoneId.systemDefault()).toLocalDate() : null;
            java.time.LocalDate ngayNhap = i.getNgayNhap().toLocalDate();

            if (from != null && ngayNhap.isBefore(from)) {
                match = false;
            }
            if (to != null && ngayNhap.isAfter(to)) {
                match = false;
            }
        }

        // Lọc theo tổng tiền
        double total = i.getTongTien();
        if (total < minAmount || total > maxAmount) {
            match = false;
        }

        if (match) {
            String tenNCC = nccbus.getTenNCCById(i.getmaNCC());
            String tenNV = nvbus.getTenNVById(i.getmaNV());
            
            model.addRow(new Object[]{
                 String.format("%d", i.getMaPN()),
                tenNCC.isEmpty() ? "N/A" : tenNCC,
                tenNV.isEmpty() ? "Chưa có thông tin" : tenNV,
                i.getNgayNhap().format(dtf),
                String.format("%,.0f VND", total)
            });
        }
    }

    if (model.getRowCount() == 0) {
        JOptionPane.showMessageDialog(null, "Không tìm thấy kết quả phù hợp!");
    }
});






    return leftPanel;
}


    // Phương thức tải icon từ đường dẫn và thay đổi kích thước (20x20)
    private ImageIcon loadIcon(String path) {
        URL imgURL = getClass().getResource(path);
        if (imgURL != null) {
            ImageIcon icon = new ImageIcon(imgURL);
            Image image = icon.getImage();
            Image scaledImage = image.getScaledInstance(20, 20, Image.SCALE_SMOOTH);
            return new ImageIcon(scaledImage);
        } else {
            System.err.println("Không thể tải icon: " + path);
            return null;
        }
    }

public void loaddatatotable(){
     
      model.setRowCount(0);
        PhieuNhap_BUS pnbus = new PhieuNhap_BUS();
        NhanVien_BUS nvbus = new NhanVien_BUS();
        NhaCungCap_BUS nccbus = new NhaCungCap_BUS();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        for (PhieuNhap_DTO i : pnbus.selectall()) {
            String[] data = new String[5];
            data[0] = String.valueOf(i.getMaPN());
            data[2] = nvbus.getTenNVById(i.getmaNV());
            data[1] = nccbus.getTenNCCById(i.getmaNCC());
            data[3] = i.getNgayNhap().format(dtf);
            data[4] = String.format("%,.0f VND", i.getTongTien());
            model.addRow(data);
        }

}




    public static void main(String args[]){
        JFrame frame = new JFrame("Test Phiếu Nhập");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setSize(1000, 700);
    frame.add(new PhieuNhap());
    frame.setLocationRelativeTo(null);
    frame.setVisible(true);
    }
}
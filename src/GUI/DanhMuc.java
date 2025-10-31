package GUI;
import DTO.DanhMuc_DTO;
import java.awt.*;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import BUS.danhmuc_BUS;

public class DanhMuc extends JPanel {
    private JFrame parentFrame;
    DefaultTableModel model = new DefaultTableModel();

    public DanhMuc() {
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
        P1.add(btnthem);
        btnthem.addActionListener(e -> showAddCategoryDialog());

        ImageIcon suaicon = resizeimg(new ImageIcon(getClass().getResource("/icon/sua.png")));
        JButton btnsua = createIconButton("Sửa",suaicon);
        btnsua.setOpaque(false);
        btnsua.setFocusPainted(false);
        btnsua.setBorderPainted(false);
        P1.add(btnsua);

        ImageIcon xoaIcon = resizeimg(new ImageIcon(getClass().getResource("/icon/xoa.png")));
        JButton btnxoa = createIconButton("Xóa", xoaIcon);
        btnxoa.setOpaque(false);
        btnxoa.setFocusPainted(false);
        btnxoa.setBorderPainted(false);
        P1.add(btnxoa);
        
        // Nút Chi tiết
        ImageIcon lamoiIcon = resizeimg(new ImageIcon(getClass().getResource("/icon/lammoi.png")));
        JButton btnlammoi = createIconButton("Làm mới",lamoiIcon);
        btnlammoi.setOpaque(false);
        btnlammoi.setFocusPainted(false);
        btnlammoi.setBorderPainted(false);
        P1.add(btnlammoi);

        // Panel chứa công cụ tìm kiếm (bên phải của thanh chức năng)
        JPanel P2 = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        
        // TextField tìm kiếm
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

        // Nút tìm kiếm
        ImageIcon lmcon = resizeimg(new ImageIcon(getClass().getResource("/icon/loupe.png")));
        JButton btnlm = createIconButton("Tìm kiếm", lmcon);
        btnlm.setOpaque(false);
        btnlm.setFocusPainted(false);
        btnlm.setVerticalTextPosition(SwingConstants.CENTER);
        btnlm.setHorizontalTextPosition(SwingConstants.RIGHT);

        // Sự kiện tìm kiếm - gọi BUS để tìm kiếm
        btnlm.addActionListener(e -> {
            String keyword = tf.getText().trim();

            if (keyword.equals("") || keyword.equals("Nhập nội dung tìm kiếm...")) {
                JOptionPane.showMessageDialog(null, "Vui lòng nhập nội dung tìm kiếm!");
                return;
            }

            // Gọi BUS để tìm kiếm
            danhmuc_BUS categoryBUS = new danhmuc_BUS();
            ArrayList<DanhMuc_DTO> searchResults = (ArrayList<DanhMuc_DTO>) categoryBUS.search(keyword);
            
            // Cập nhật bảng với kết quả tìm kiếm
            model.setRowCount(0);
            for (DanhMuc_DTO category : searchResults) {
                String maDM = String.valueOf(category.getMaDM());
                String tenDM = category.getTenDM();
                String moTa = category.getMota();
                model.addRow(new Object[]{maDM, tenDM, moTa});
            }

            if (model.getRowCount() == 0) {
                JOptionPane.showMessageDialog(null, "Không tìm thấy kết quả phù hợp!");
                loadDataToTable();
            }
        });

        tf.setPreferredSize(new Dimension(200, 40));
        P2.add(tf);
        P2.add(btnlm);

        // Ghép hai panel con vào panel P
        P.add(P1, BorderLayout.WEST);
        P.add(P2, BorderLayout.EAST);

        // Thêm panel chứa các nút chức năng vào phần NORTH
        add(P, BorderLayout.NORTH);

        // --------- PHẦN BẢNG DỮ LIỆU -----------
        model.addColumn("Mã danh mục");
        model.addColumn("Tên danh mục");
        model.addColumn("Mô tả");

        JTable table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);
        
        // Sự kiện cho nút Sửa
        btnsua.addActionListener(e -> {
            int selectedRow = table.getSelectedRow();
            if (selectedRow == -1) {
                JOptionPane.showMessageDialog(this, "Vui lòng chọn một danh mục để sửa!", "Thông báo", JOptionPane.WARNING_MESSAGE);
                return;
            }

            // Lấy thông tin từ dòng được chọn
            String maDM = model.getValueAt(selectedRow, 0).toString();
            String tenDM = model.getValueAt(selectedRow, 1).toString();
            String moTa = model.getValueAt(selectedRow, 2).toString();

            // Hiển thị dialog sửa
            showEditCategoryDialog(maDM, tenDM, moTa);
        });
        
        // Sự kiện cho nút Làm mới
        btnlammoi.addActionListener(e -> {
            loadDataToTable();
            tf.setText("Nhập nội dung tìm kiếm...");
            tf.setForeground(Color.GRAY);
        });
        
        // Sự kiện cho nút Xóa
        btnxoa.addActionListener(e -> {
            int selectedRow = table.getSelectedRow();
            if (selectedRow == -1) {
                JOptionPane.showMessageDialog(this, "Vui lòng chọn một danh mục để xóa!", "Thông báo", JOptionPane.WARNING_MESSAGE);
                return;
            }

            // Lấy thông tin từ dòng được chọn
            int maDM = Integer.parseInt(model.getValueAt(selectedRow, 0).toString());
            
            int results = JOptionPane.showConfirmDialog(
                this,
                "Bạn có muốn xóa danh mục này không?",
                "Xác nhận xóa",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE
            );
            
            if (results == JOptionPane.YES_OPTION) {
                danhmuc_BUS categoryBUS = new danhmuc_BUS();
                boolean result = categoryBUS.delete(maDM);

                if (result) {
                    JOptionPane.showMessageDialog(
                        null,
                        "Xóa danh mục thành công!",
                        "Thành công",
                        JOptionPane.INFORMATION_MESSAGE
                    );
                    loadDataToTable();
                } else {
                    JOptionPane.showMessageDialog(
                        null,
                        "Xóa danh mục thất bại!",
                        "Thất bại",
                        JOptionPane.INFORMATION_MESSAGE
                    );
                }
            }
        });
        
        // Tải dữ liệu mẫu
        loadDataToTable();
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

    public void loadDataToTable() {
        model.setRowCount(0);
        ArrayList<DanhMuc_DTO> ds = (ArrayList<DanhMuc_DTO>) new danhmuc_BUS().selectAll();
        for (DanhMuc_DTO i : ds) {
            String maDM = String.valueOf(i.getMaDM());
            String tenDM = i.getTenDM();
            String moTa = i.getMota();
            model.addRow(new Object[]{maDM, tenDM, moTa});
        }
    }

    // Xử lý sự kiện nút thêm
    private void showAddCategoryDialog() {
        // Tạo JDialog
        JDialog dialog = new JDialog(parentFrame, "Thêm Danh Mục Mới", true);
        dialog.setLayout(new BorderLayout(10, 10));
        dialog.setSize(450, 250); // Điều chỉnh kích thước dialog
        dialog.setLocationRelativeTo(this);

        // Panel chứa các trường nhập liệu với GridBagLayout để căn chỉnh tốt hơn
        JPanel inputPanel = new JPanel(new GridBagLayout());
        inputPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5); // Khoảng cách giữa các component
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.WEST;

        JLabel lblTenDM = new JLabel("Tên Danh Mục:");
        JTextField tfTenDM = new JTextField(20); // Đặt số cột vừa phải
        tfTenDM.setPreferredSize(new Dimension(200, 25)); // Đặt kích thước ưu tiên

        JLabel lblMoTa = new JLabel("Mô Tả:");
        JTextField tfMoTa = new JTextField(20);
        tfMoTa.setPreferredSize(new Dimension(200, 25));

        // Thêm các component vào panel với GridBagConstraints
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 0.3;
        inputPanel.add(lblTenDM, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.weightx = 0.7;
        inputPanel.add(tfTenDM, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weightx = 0.3;
        inputPanel.add(lblMoTa, gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.weightx = 0.7;
        inputPanel.add(tfMoTa, gbc);

        // Panel chứa các nút
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        JButton btnSave = new JButton("Lưu");
        JButton btnCancel = new JButton("Hủy");

        // Đặt kích thước cho các nút
        btnSave.setPreferredSize(new Dimension(80, 30));
        btnCancel.setPreferredSize(new Dimension(80, 30));

        buttonPanel.add(btnSave);
        buttonPanel.add(btnCancel);

        // Thêm các panel vào dialog
        dialog.add(inputPanel, BorderLayout.CENTER);
        dialog.add(buttonPanel, BorderLayout.SOUTH);

        // Xử lý sự kiện nút Lưu (giữ nguyên)
        btnSave.addActionListener(e -> {
            String tenDM = tfTenDM.getText().trim();
            String moTa = tfMoTa.getText().trim();

            // Kiểm tra dữ liệu nhập
            if (tenDM.isEmpty()) {
                JOptionPane.showMessageDialog(dialog, "Vui lòng nhập tên danh mục!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Tạo đối tượng DanhMuc_DTO mới
            DanhMuc_DTO newCategory = new DanhMuc_DTO();
            newCategory.setTenDM(tenDM);
            newCategory.setMota(moTa);

            // Gọi BUS để thêm vào cơ sở dữ liệu
            danhmuc_BUS categoryBUS = new danhmuc_BUS();
            boolean result = categoryBUS.insert(newCategory);

            if (result) {
                JOptionPane.showMessageDialog(dialog, "Thêm danh mục thành công!", "Thành công", JOptionPane.INFORMATION_MESSAGE);
                dialog.dispose();
                loadDataToTable(); // Tải lại dữ liệu trong bảng
            } else {
                JOptionPane.showMessageDialog(dialog, "Thêm danh mục thất bại!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
        });

        // Xử lý sự kiện nút Hủy
        btnCancel.addActionListener(e -> dialog.dispose());

        dialog.setVisible(true);
    }

    // Xử lý sự kiện nút sửa - CHỈNH SỬA GIAO DIỆN
    private void showEditCategoryDialog(String maDM, String tenDM, String moTa) {
        // Tạo JDialog
        JDialog dialog = new JDialog(parentFrame, "Sửa Danh Mục", true);
        dialog.setLayout(new BorderLayout(10, 10));
        dialog.setSize(450, 300); // Điều chỉnh kích thước dialog
        dialog.setLocationRelativeTo(this);

        // Panel chứa các trường nhập liệu với GridBagLayout
        JPanel inputPanel = new JPanel(new GridBagLayout());
        inputPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.WEST;

        JLabel lblMaDM = new JLabel("Mã Danh Mục:");
        JTextField tfMaDM = new JTextField(maDM, 20);
        tfMaDM.setEditable(false); // Không cho sửa mã danh mục
        tfMaDM.setPreferredSize(new Dimension(200, 25));

        JLabel lblTenDM = new JLabel("Tên Danh Mục:");
        JTextField tfTenDM = new JTextField(tenDM, 20);
        tfTenDM.setPreferredSize(new Dimension(200, 25));

        JLabel lblMoTa = new JLabel("Mô Tả:");
        JTextField tfMoTa = new JTextField(moTa, 20);
        tfMoTa.setPreferredSize(new Dimension(200, 25));

        // Thêm các component vào panel
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 0.3;
        inputPanel.add(lblMaDM, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.weightx = 0.7;
        inputPanel.add(tfMaDM, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weightx = 0.3;
        inputPanel.add(lblTenDM, gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.weightx = 0.7;
        inputPanel.add(tfTenDM, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.weightx = 0.3;
        inputPanel.add(lblMoTa, gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.weightx = 0.7;
        inputPanel.add(tfMoTa, gbc);

        // Panel chứa các nút
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        JButton btnSave = new JButton("Lưu");
        JButton btnCancel = new JButton("Hủy");

        // Đặt kích thước cho các nút
        btnSave.setPreferredSize(new Dimension(80, 30));
        btnCancel.setPreferredSize(new Dimension(80, 30));

        buttonPanel.add(btnSave);
        buttonPanel.add(btnCancel);

        // Thêm các panel vào dialog
        dialog.add(inputPanel, BorderLayout.CENTER);
        dialog.add(buttonPanel, BorderLayout.SOUTH);

        // Xử lý sự kiện nút Lưu (giữ nguyên)
        btnSave.addActionListener(e -> {
            String newTenDM = tfTenDM.getText().trim();
            String newMoTa = tfMoTa.getText().trim();

            // Kiểm tra dữ liệu nhập
            if (newTenDM.isEmpty()) {
                JOptionPane.showMessageDialog(dialog, "Vui lòng nhập tên danh mục!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return;
            }

            try {
                // Tạo đối tượng DanhMuc_DTO mới
                DanhMuc_DTO category = new DanhMuc_DTO();
                category.setMaDM(Integer.parseInt(maDM));
                category.setTenDM(newTenDM);
                category.setMota(newMoTa);

                // Gọi BUS để cập nhật vào cơ sở dữ liệu
                danhmuc_BUS categoryBUS = new danhmuc_BUS();
                boolean result = categoryBUS.update(category);

                if (result) {
                    JOptionPane.showMessageDialog(dialog, "Sửa danh mục thành công!", "Thành công", JOptionPane.INFORMATION_MESSAGE);
                    dialog.dispose();
                    loadDataToTable(); // Tải lại dữ liệu trong bảng
                } else {
                    JOptionPane.showMessageDialog(dialog, "Sửa danh mục thất bại!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(dialog, "Lỗi: " + ex.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
                ex.printStackTrace();
            }
        });

        // Xử lý sự kiện nút Hủy
        btnCancel.addActionListener(e -> dialog.dispose());

        dialog.setVisible(true);
    }

    public static void main(String args[]) {
        JFrame frame = new JFrame("Quản lý Danh Mục");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.add(new DanhMuc());
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
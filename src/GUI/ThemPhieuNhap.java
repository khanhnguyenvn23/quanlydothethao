package GUI;

import BUS.NhaCungCap_BUS;
import BUS.NhanVien_BUS;
import BUS.PhieuNhap_BUS;
import DTO.PhieuNhap_DTO;
import DTO.NCC_DTO;
import DTO.NhanVien_DTO;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class ThemPhieuNhap extends JDialog {
    
    private JTextField txtMaPhieuNhap;
    private JComboBox<String> cbNhaCungCap;
    private JComboBox<String> cbNhanVien;
    private JTextField txtNgayNhap;
    private JTextField txtTongTien;
    private JButton btnThem, btnHuy;
    
    private PhieuNhap_BUS phieuNhapBUS;
    private NhaCungCap_BUS nhaCungCapBUS;
    private NhanVien_BUS nhanVienBUS;
    
    private boolean isSuccess = false;
    
    public ThemPhieuNhap(Frame parent) {
        super(parent, "Thêm Phiếu Nhập", true);
        
        // Khởi tạo BUS
        phieuNhapBUS = new PhieuNhap_BUS();
        nhaCungCapBUS = new NhaCungCap_BUS();
        nhanVienBUS = new NhanVien_BUS();
        
        initComponents();
        setupLayout();
        setupEvents();
        loadData();
        
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(500, 400);
        setLocationRelativeTo(parent);
    }
    
    private void initComponents() {
        // Mã phiếu nhập (tự động)
        txtMaPhieuNhap = new JTextField();
        txtMaPhieuNhap.setEditable(false);
        txtMaPhieuNhap.setBackground(Color.LIGHT_GRAY);
        
        // Nhà cung cấp
        cbNhaCungCap = new JComboBox<>();
        cbNhaCungCap.setPreferredSize(new Dimension(200, 30));
        
        // Nhân viên
        cbNhanVien = new JComboBox<>();
        cbNhanVien.setPreferredSize(new Dimension(200, 30));
        
        // Ngày nhập (tự động - ngày hiện tại)
        txtNgayNhap = new JTextField();
        txtNgayNhap.setEditable(false);
        txtNgayNhap.setBackground(Color.LIGHT_GRAY);
        
        // Tổng tiền
        txtTongTien = new JTextField();
        txtTongTien.setPreferredSize(new Dimension(200, 30));
        
        // Buttons
        btnThem = new JButton("Thêm");
        btnThem.setBackground(new Color(0, 123, 255));
        btnThem.setForeground(Color.WHITE);
        btnThem.setPreferredSize(new Dimension(100, 35));
        
        btnHuy = new JButton("Hủy");
        btnHuy.setBackground(new Color(108, 117, 125));
        btnHuy.setForeground(Color.WHITE);
        btnHuy.setPreferredSize(new Dimension(100, 35));
    }
    
    private void setupLayout() {
        setLayout(new BorderLayout());
        
        // Panel chính
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridBagLayout());
        mainPanel.setBorder(new EmptyBorder(20, 20, 20, 20));
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.anchor = GridBagConstraints.WEST;
        
        // Title
        JLabel titleLabel = new JLabel("THÊM PHIẾU NHẬP MỚI");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        titleLabel.setForeground(new Color(0, 123, 255));
        gbc.gridx = 0; gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        mainPanel.add(titleLabel, gbc);
        
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.WEST;
        
        // Mã phiếu nhập
        gbc.gridx = 0; gbc.gridy = 1;
        mainPanel.add(new JLabel("Mã phiếu nhập:"), gbc);
        gbc.gridx = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        mainPanel.add(txtMaPhieuNhap, gbc);
        
        // Nhà cung cấp
        gbc.gridx = 0; gbc.gridy = 2;
        gbc.fill = GridBagConstraints.NONE;
        mainPanel.add(new JLabel("Nhà cung cấp:"), gbc);
        gbc.gridx = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        mainPanel.add(cbNhaCungCap, gbc);
        
        // Nhân viên
        gbc.gridx = 0; gbc.gridy = 3;
        gbc.fill = GridBagConstraints.NONE;
        mainPanel.add(new JLabel("Nhân viên nhập:"), gbc);
        gbc.gridx = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        mainPanel.add(cbNhanVien, gbc);
        
        // Ngày nhập
        gbc.gridx = 0; gbc.gridy = 4;
        gbc.fill = GridBagConstraints.NONE;
        mainPanel.add(new JLabel("Ngày nhập:"), gbc);
        gbc.gridx = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        mainPanel.add(txtNgayNhap, gbc);
        
        // Tổng tiền
        gbc.gridx = 0; gbc.gridy = 5;
        gbc.fill = GridBagConstraints.NONE;
        mainPanel.add(new JLabel("Tổng tiền (VND):"), gbc);
        gbc.gridx = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        mainPanel.add(txtTongTien, gbc);
        
        // Panel buttons
        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.add(btnThem);
        buttonPanel.add(btnHuy);
        
        gbc.gridx = 0; gbc.gridy = 6;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.CENTER;
        mainPanel.add(buttonPanel, gbc);
        
        add(mainPanel, BorderLayout.CENTER);
    }
    
    private void setupEvents() {
        btnThem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                themPhieuNhap();
            }
        });
        
        btnHuy.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
    }
    
    private void loadData() {
        // Load mã phiếu nhập tiếp theo
        int nextId = phieuNhapBUS.selectNextid();
        txtMaPhieuNhap.setText("PN" + String.format("%03d", nextId));
        
        // Load ngày hiện tại
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        txtNgayNhap.setText(now.format(formatter));
        
        // Load danh sách nhà cung cấp
        cbNhaCungCap.removeAllItems();
        cbNhaCungCap.addItem("-- Chọn nhà cung cấp --");
        ArrayList<NCC_DTO> listNCC = nhaCungCapBUS.getListNCC();
        for (NCC_DTO ncc : listNCC) {
            cbNhaCungCap.addItem(ncc.getTenNCC());
        }
        
        // Load danh sách nhân viên
        cbNhanVien.removeAllItems();
        cbNhanVien.addItem("-- Chọn nhân viên --");
        ArrayList<NhanVien_DTO> listNV = nhanVienBUS.getListNV();
        for (NhanVien_DTO nv : listNV) {
            cbNhanVien.addItem(nv.getHoTen());
        }
    }
    
    private void themPhieuNhap() {
        try {
            // Validate dữ liệu
            if (!validateInput()) {
                return;
            }
            
            // Lấy thông tin từ form
            String tenNCC = (String) cbNhaCungCap.getSelectedItem();
            String tenNV = (String) cbNhanVien.getSelectedItem();
            double tongTien = Double.parseDouble(txtTongTien.getText().trim());
            
            // Tìm mã nhà cung cấp và nhân viên
            int maNCC = findMaNCC(tenNCC);
            int maNV = findMaNV(tenNV);
            
            if (maNCC == -1 || maNV == -1) {
                JOptionPane.showMessageDialog(this, "Không tìm thấy thông tin nhà cung cấp hoặc nhân viên!", 
                    "Lỗi", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            // Tạo DTO
            PhieuNhap_DTO phieuNhap = new PhieuNhap_DTO();
            phieuNhap.setmaNCC(maNCC);
            phieuNhap.setMaNV(maNV);
            phieuNhap.setNgayNhap(LocalDateTime.now());
            phieuNhap.setTongTien(tongTien);
            
            // Thêm vào database
            boolean result = phieuNhapBUS.insert(phieuNhap);
            
            if (result) {
                JOptionPane.showMessageDialog(this, "Thêm phiếu nhập thành công!", 
                    "Thành công", JOptionPane.INFORMATION_MESSAGE);
                isSuccess = true;
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Thêm phiếu nhập thất bại!", 
                    "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
            
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Tổng tiền phải là số!", 
                "Lỗi", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Có lỗi xảy ra: " + e.getMessage(), 
                "Lỗi", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }
    
    private boolean validateInput() {
        // Kiểm tra nhà cung cấp
        if (cbNhaCungCap.getSelectedIndex() == 0) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn nhà cung cấp!", 
                "Thông báo", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        
        // Kiểm tra nhân viên
        if (cbNhanVien.getSelectedIndex() == 0) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn nhân viên!", 
                "Thông báo", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        
        // Kiểm tra tổng tiền
        String tongTienText = txtTongTien.getText().trim();
        if (tongTienText.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập tổng tiền!", 
                "Thông báo", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        
        try {
            double tongTien = Double.parseDouble(tongTienText);
            if (tongTien <= 0) {
                JOptionPane.showMessageDialog(this, "Tổng tiền phải lớn hơn 0!", 
                    "Thông báo", JOptionPane.WARNING_MESSAGE);
                return false;
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Tổng tiền phải là số!", 
                "Thông báo", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        
        return true;
    }
    
    private int findMaNCC(String tenNCC) {
        ArrayList<NCC_DTO> listNCC = nhaCungCapBUS.getListNCC();
        for (NCC_DTO ncc : listNCC) {
            if (ncc.getTenNCC().equals(tenNCC)) {
                return ncc.getMaNCC();
            }
        }
        return -1;
    }
    
    private int findMaNV(String tenNV) {
        ArrayList<NhanVien_DTO> listNV = nhanVienBUS.getListNV();
        for (NhanVien_DTO nv : listNV) {
            if (nv.getHoTen().equals(tenNV)) {
                return nv.getMaNV();
            }
        }
        return -1;
    }
    
    public boolean isSuccess() {
        return isSuccess;
    }
}
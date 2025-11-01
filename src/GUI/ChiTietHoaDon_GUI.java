package GUI;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.table.*;
import java.awt.*;
import java.time.LocalDate;
import java.text.DecimalFormat;

public class ChiTietHoaDon_GUI extends JPanel{
    JTable table;
    JTextField txtCustomerName, txtCustomerPhone, txtCustomerAddress;
    JTextField txtStaffName, txtStaffPhone, txtBranch;
    JTextField txtDate, txtTotal;

    public ChiTietHoaDon_GUI() {
        setLayout(new BorderLayout(15, 15));
        setBackground(Color.WHITE);
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // ==== Panel thông tin chung ====
        JPanel infoPanel = new JPanel(new GridLayout(1, 2, 20, 0));
        infoPanel.setBackground(Color.WHITE);

        // ==== Thông tin khách hàng ====
        JPanel customerPanel = new JPanel(new GridBagLayout());
        customerPanel.setBackground(Color.WHITE);
        customerPanel.setBorder(new TitledBorder("Thông tin khách hàng"));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.WEST;

        txtCustomerName = new JTextField();
        txtCustomerPhone = new JTextField();
        txtCustomerAddress = new JTextField();

        JTextField[] customerFields = {txtCustomerName, txtCustomerPhone, txtCustomerAddress};
        for (JTextField f : customerFields) {
            f.setEditable(false);
            f.setFocusable(false);
            f.setBackground(Color.WHITE);
            f.setPreferredSize(new Dimension(230, 28));
        }

        gbc.gridx = 0; gbc.gridy = 0; customerPanel.add(new JLabel("Họ tên KH:"), gbc);
        gbc.gridx = 1; customerPanel.add(txtCustomerName, gbc);
        gbc.gridx = 0; gbc.gridy = 1; customerPanel.add(new JLabel("SĐT:"), gbc);
        gbc.gridx = 1; customerPanel.add(txtCustomerPhone, gbc);
        gbc.gridx = 0; gbc.gridy = 2; customerPanel.add(new JLabel("Địa chỉ:"), gbc);
        gbc.gridx = 1; customerPanel.add(txtCustomerAddress, gbc);

        // ==== Thông tin nhân viên ====
        JPanel staffPanel = new JPanel(new GridBagLayout());
        staffPanel.setBackground(Color.WHITE);
        staffPanel.setBorder(new TitledBorder("Thông tin nhân viên"));
        GridBagConstraints gbc2 = new GridBagConstraints();
        gbc2.insets = new Insets(5, 5, 5, 5);
        gbc2.anchor = GridBagConstraints.WEST;

        txtStaffName = new JTextField();
        txtStaffPhone = new JTextField();
        txtBranch = new JTextField();

        JTextField[] staffFields = {txtStaffName, txtStaffPhone, txtBranch};
        for (JTextField f : staffFields) {
            f.setEditable(false);
            f.setFocusable(false);
            f.setBackground(Color.WHITE);
            f.setPreferredSize(new Dimension(230, 28));
        }

        gbc2.gridx = 0; gbc2.gridy = 0; staffPanel.add(new JLabel("Họ tên NV:"), gbc2);
        gbc2.gridx = 1; staffPanel.add(txtStaffName, gbc2);
        gbc2.gridx = 0; gbc2.gridy = 1; staffPanel.add(new JLabel("SĐT:"), gbc2);
        gbc2.gridx = 1; staffPanel.add(txtStaffPhone, gbc2);
        gbc2.gridx = 0; gbc2.gridy = 2; staffPanel.add(new JLabel("Chi nhánh:"), gbc2);
        gbc2.gridx = 1; staffPanel.add(txtBranch, gbc2);

        infoPanel.add(customerPanel);
        infoPanel.add(staffPanel);
        add(infoPanel, BorderLayout.NORTH);

        // ==== Bảng sản phẩm ====
        String[] columns = {"Hình ảnh", "Tên sản phẩm", "Đơn giá (VND)", "Số lượng", "Thành tiền (VND)"};
        DefaultTableModel model = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int col) {
                return false;
            }
        };

        table = new JTable(model);
        table.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 14));
        table.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        table.getTableHeader().setBackground(new Color(41, 128, 185));
        table.getTableHeader().setForeground(Color.WHITE);
        table.setRowHeight(28);
        table.setBackground(Color.WHITE);
        table.setFillsViewportHeight(true);

        // Căn giữa toàn bộ ô
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        for (int i = 0; i < table.getColumnCount(); i++) {
            table.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }

        // Căn giữa tiêu đề cột
        ((DefaultTableCellRenderer) table.getTableHeader().getDefaultRenderer())
                .setHorizontalAlignment(SwingConstants.CENTER);

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.getViewport().setBackground(Color.WHITE);
        scrollPane.setBackground(Color.WHITE);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Danh sách sản phẩm"));
        add(scrollPane, BorderLayout.CENTER);

        // ==== Panel dưới cùng ====
        JPanel bottomPanel = new JPanel(new GridBagLayout());
        bottomPanel.setBackground(Color.WHITE);
        GridBagConstraints gbc3 = new GridBagConstraints();
        gbc3.insets = new Insets(5, 5, 5, 5);
        gbc3.anchor = GridBagConstraints.EAST;

        JLabel lblDate = new JLabel("Ngày lập:");
        txtDate = new JTextField(LocalDate.now().toString());
        txtDate.setEditable(false);
        txtDate.setFocusable(false);
        txtDate.setBackground(Color.WHITE);
        txtDate.setPreferredSize(new Dimension(120, 28));

        JLabel lblTotal = new JLabel("Tổng tiền:");
        txtTotal = new JTextField(new DecimalFormat("#,### VND").format(0));
        txtTotal.setEditable(false);
        txtTotal.setFocusable(false);
        txtTotal.setBackground(Color.WHITE);
        txtTotal.setPreferredSize(new Dimension(150, 28));
        txtTotal.setFont(new Font("Segoe UI", Font.BOLD, 14));
        txtTotal.setHorizontalAlignment(JTextField.RIGHT);

        JButton btnClose = new JButton("Đóng");
        btnClose.setBackground(new Color(52, 152, 219)); // xanh dương nhạt
        btnClose.setFocusPainted(false);
        btnClose.setBorder(BorderFactory.createEmptyBorder(8, 18, 8, 18));
        btnClose.setForeground(Color.WHITE);
        btnClose.addActionListener(e -> {
            SwingUtilities.getWindowAncestor(this).dispose();
        });

        gbc3.gridx = 0; gbc3.gridy = 0; bottomPanel.add(lblDate, gbc3);
        gbc3.gridx = 1; bottomPanel.add(txtDate, gbc3);
        gbc3.gridx = 2; bottomPanel.add(lblTotal, gbc3);
        gbc3.gridx = 3; bottomPanel.add(txtTotal, gbc3);
        gbc3.gridx = 4; bottomPanel.add(btnClose, gbc3);

        add(bottomPanel, BorderLayout.SOUTH);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Chi tiết hoá đơn");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(900, 600);
            frame.setLocationRelativeTo(null);
            frame.setContentPane(new ChiTietHoaDon_GUI());
            frame.setVisible(true);
        });
    }
}

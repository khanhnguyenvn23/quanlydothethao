package GUI;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;

public class GUI_HoaDon extends JFrame {

    private JTable table;
    private DefaultTableModel model;
    private JButton btnXemChiTiet;
    private JTextArea txtChiTiet;

    public GUI_HoaDon() {
        setTitle("Danh sách hoá đơn");
        setSize(750, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        // ---------------- BẢNG HOÁ ĐƠN ----------------
        String[] columns = {"Mã HD", "Khách hàng", "Ngày lập", "Tổng tiền"};
        Object[][] data = {
            {"HD001", "Nguyễn Văn A", "2025-10-01", "1,500,000"},
            {"HD002", "Trần Thị B", "2025-10-03", "2,200,000"},
            {"HD003", "Lê Văn C", "2025-10-05", "3,750,000"},
            {"HD004", "Phạm Thị D", "2025-10-07", "980,000"}
        };

        model = new DefaultTableModel(data, columns);
        table = new JTable(model);
        table.setRowHeight(28);
        table.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        table.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 14));
        table.getTableHeader().setBackground(new Color(41, 128, 185));
        table.getTableHeader().setForeground(Color.WHITE);
        table.setGridColor(new Color(230, 230, 230));

        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);

        // ---------------- KHU VỰC DƯỚI (NÚT + CHI TIẾT) ----------------
        JPanel bottomPanel = new JPanel(new BorderLayout());
        bottomPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Nút xem chi tiết
        btnXemChiTiet = new JButton("🔍 Xem chi tiết");
        btnXemChiTiet.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btnXemChiTiet.setBackground(new Color(52, 152, 219));
        btnXemChiTiet.setForeground(Color.WHITE);
        btnXemChiTiet.setFocusPainted(false);
        btnXemChiTiet.setBorder(BorderFactory.createEmptyBorder(8, 16, 8, 16));

        // Thêm sự kiện click
        btnXemChiTiet.addActionListener(e -> xemChiTiet());

        bottomPanel.add(btnXemChiTiet, BorderLayout.WEST);

        // Khu hiển thị chi tiết
        txtChiTiet = new JTextArea();
        txtChiTiet.setEditable(false);
        txtChiTiet.setFont(new Font("Consolas", Font.PLAIN, 14));
        txtChiTiet.setBorder(BorderFactory.createTitledBorder("Chi tiết hoá đơn"));
        txtChiTiet.setBackground(new Color(248, 249, 250));
        bottomPanel.add(new JScrollPane(txtChiTiet), BorderLayout.CENTER);

        add(bottomPanel, BorderLayout.SOUTH);
    }

    private void xemChiTiet() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn một hoá đơn để xem chi tiết!",
                                          "Thông báo", JOptionPane.WARNING_MESSAGE);
            return;
        }

        // Lấy dữ liệu từ bảng
        String maHD = model.getValueAt(selectedRow, 0).toString();
        String khachHang = model.getValueAt(selectedRow, 1).toString();
        String ngayLap = model.getValueAt(selectedRow, 2).toString();
        String tongTien = model.getValueAt(selectedRow, 3).toString();

        // Dữ liệu chi tiết giả lập
        String chiTiet = String.format("""
                🧾 Mã hoá đơn: %s
                👤 Khách hàng: %s
                📅 Ngày lập: %s
                💰 Tổng tiền: %s

                -----------------------------
                Sản phẩm:
                - Đồng hồ Citizen EcoDrive – 1,000,000
                - Dây da cao cấp – 500,000
                -----------------------------
                Cảm ơn quý khách đã mua hàng!
                """, maHD, khachHang, ngayLap, tongTien);

        txtChiTiet.setText(chiTiet);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new GUI_HoaDon().setVisible(true));
    }
}

package GUI;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import com.toedter.calendar.JDateChooser;
import GUI.GUITool.BorderTool;
import GUI.GUITool.PlaceholderTextField;
import java.awt.*;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;

import BUS.*;
import DTO.HoaDon_DTO;

public class HoaDon extends JPanel {

    // ======================= THUỘC TÍNH TOÀN CỤC =======================
    private JPanel topMenuPanel, leftMenuPanel, rightMenuPanel;
    private JComboBox<String> cbSearch;
    private JTextField tfSearch;
    private JButton btSearch, btChitiet, btnAdd;

    private JDateChooser dc_filterTimeFrom, dc_filterTimeTo;
    private JTextField tf_filterPriceFrom, tf_filterPriceTo;
    private JButton btnFilter, btnReset;

    private JTable table;
    private JScrollPane scpTable;

    // ======================= HÀM KHỞI TẠO =======================
    public HoaDon() {
        initComponents();
        setupLayout();
        loadHoaDon();
        addEvents();
    }

    // ======================= KHỞI TẠO GIAO DIỆN =======================
    private void initComponents() {
        setLayout(new BorderLayout());
        setBackground(Color.WHITE);

        // ---------------------- TOP MENU ----------------------
        topMenuPanel = new JPanel(new BorderLayout());
        topMenuPanel.setBackground(Color.WHITE);
        BorderTool.setPadding(topMenuPanel, 20, 10, 20, 20);

        // Tìm kiếm
        JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 15, 0));
        searchPanel.setBackground(Color.WHITE);

        cbSearch = new JComboBox<>(new String[]{"Mã hoá đơn", "Tên khách hàng","Tên nhân viên"});
        cbSearch.setFocusable(false);
        cbSearch.setPreferredSize(new Dimension(140, 30));
        cbSearch.setBackground(Color.WHITE);

        tfSearch = new PlaceholderTextField("Nhập nội dung tìm kiếm...");
        tfSearch.setPreferredSize(new Dimension(400, 30));

        btSearch = new JButton("Tìm kiếm");
        btSearch.setFocusPainted(false);

        searchPanel.add(cbSearch);
        searchPanel.add(tfSearch);
        searchPanel.add(btSearch);

        // Nút bên phải
        JPanel buttonPanelRight = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonPanelRight.setBackground(Color.WHITE);

        btChitiet = new JButton("Xem chi tiết");
        btnAdd = new JButton("Thêm +");

        for (JButton btn : new JButton[]{btChitiet, btnAdd}) {
            btn.setBackground(new Color(52, 152, 219));
            btn.setForeground(Color.WHITE);
            btn.setFocusPainted(false);
        }

        buttonPanelRight.add(btChitiet);
        buttonPanelRight.add(btnAdd);

        topMenuPanel.add(searchPanel, BorderLayout.CENTER);
        topMenuPanel.add(buttonPanelRight, BorderLayout.EAST);

        // ---------------------- LEFT MENU (BỘ LỌC) ----------------------
        leftMenuPanel = new JPanel();
        leftMenuPanel.setLayout(new BoxLayout(leftMenuPanel, BoxLayout.Y_AXIS));
        leftMenuPanel.setBackground(Color.WHITE);
        leftMenuPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createTitledBorder("Bộ lọc tìm kiếm"),
                new EmptyBorder(10, 10, 10, 10)
        ));
        leftMenuPanel.setPreferredSize(new Dimension(220, leftMenuPanel.getPreferredSize().height));

        Dimension fieldSize = new Dimension(Integer.MAX_VALUE, 25);

        // ==== Thời gian ====
        JLabel lb_filterTimeFrom = new JLabel("Từ ngày:");
        JLabel lb_filterTimeTo = new JLabel("Đến ngày:");

        dc_filterTimeFrom = new JDateChooser();
        dc_filterTimeTo = new JDateChooser();
        setupDateChooser(dc_filterTimeFrom, fieldSize);
        setupDateChooser(dc_filterTimeTo, fieldSize);

        // ==== Giá ====
        JLabel lb_filterPriceFrom = new JLabel("Từ số tiền (VNĐ):");
        JLabel lb_filterPriceTo = new JLabel("Đến số tiền (VNĐ):");

        tf_filterPriceFrom = new JTextField();
        tf_filterPriceTo = new JTextField();
        tf_filterPriceFrom.setMaximumSize(fieldSize);
        tf_filterPriceTo.setMaximumSize(fieldSize);

        // ==== Nút Lọc & Làm mới ====
        JPanel filterButtonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 0));
        filterButtonPanel.setBackground(Color.WHITE);

        btnFilter = new JButton("Lọc");
        btnReset = new JButton("Làm mới");

        for (JButton btn : new JButton[]{btnFilter, btnReset}) {
            btn.setFocusPainted(false);
            btn.setBackground(new Color(52, 152, 219));
            btn.setForeground(Color.WHITE);
        }

        filterButtonPanel.add(btnFilter);
        filterButtonPanel.add(btnReset);

        // ==== Thêm các thành phần vào Left Panel ====
        leftMenuPanel.add(lb_filterTimeFrom);
        leftMenuPanel.add(Box.createVerticalStrut(5));
        leftMenuPanel.add(dc_filterTimeFrom);
        leftMenuPanel.add(Box.createVerticalStrut(10));
        leftMenuPanel.add(lb_filterTimeTo);
        leftMenuPanel.add(Box.createVerticalStrut(5));
        leftMenuPanel.add(dc_filterTimeTo);
        leftMenuPanel.add(Box.createVerticalStrut(15));
        leftMenuPanel.add(lb_filterPriceFrom);
        leftMenuPanel.add(Box.createVerticalStrut(5));
        leftMenuPanel.add(tf_filterPriceFrom);
        leftMenuPanel.add(Box.createVerticalStrut(10));
        leftMenuPanel.add(lb_filterPriceTo);
        leftMenuPanel.add(Box.createVerticalStrut(5));
        leftMenuPanel.add(tf_filterPriceTo);
        leftMenuPanel.add(Box.createVerticalStrut(20));
        leftMenuPanel.add(filterButtonPanel);

        // ---------------------- RIGHT MENU (BẢNG DỮ LIỆU) ----------------------
        rightMenuPanel = new JPanel(new BorderLayout());
        rightMenuPanel.setBackground(Color.WHITE);
        BorderTool.setPadding(rightMenuPanel, 10, 10, 10, 10);

        String[] columnNames = {"Mã hóa đơn", "Tên khách hàng", "Tên nhân viên", "Thời gian", "Tổng số tiền"};
        Object[][] data = {};

        table = new JTable(data, columnNames) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        table.setFillsViewportHeight(true);
        table.setAutoCreateRowSorter(true);
        table.setRowHeight(28);
        table.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        table.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 14));
        table.getTableHeader().setBackground(new Color(41, 128, 185));
        table.getTableHeader().setForeground(Color.WHITE);
        table.setGridColor(new Color(200, 200, 200));
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        scpTable = new JScrollPane(table);
        scpTable.setBackground(Color.WHITE);
        scpTable.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1)); // viền rõ hơn

        rightMenuPanel.add(scpTable, BorderLayout.CENTER);
    }

    // ======================= CẤU HÌNH GIAO DIỆN =======================
    private void setupLayout() {
        add(topMenuPanel, BorderLayout.NORTH);
        add(leftMenuPanel, BorderLayout.WEST);
        add(rightMenuPanel, BorderLayout.CENTER);
    }

    // ======================= CẤU HÌNH JDateChooser =======================
    private void setupDateChooser(JDateChooser dateChooser, Dimension size) {
        dateChooser.setDateFormatString("dd/MM/yyyy");
        dateChooser.setMaximumSize(size);
        dateChooser.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));

        JTextField editor = (JTextField) dateChooser.getDateEditor().getUiComponent();
        editor.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.LIGHT_GRAY));
        editor.setBackground(Color.WHITE);
    }

    //=======================================XỮ LÝ SỰ KIỆN==========================================================================

    //lấy tất cả dữ liệu của hoá đơn
    private void loadHoaDon(){
        String[] columnNames = {"Mã hóa đơn", "Tên khách hàng", "Tên nhân viên", "Thời gian", "Tổng số tiền"};
        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);
        table.setModel(tableModel);
        HoaDon_BUS newHdBus = new HoaDon_BUS();
        NhanVien_BUS newNvBus = new NhanVien_BUS();
        KhachHang_BUS newKhBUS = new KhachHang_BUS();
        ArrayList<HoaDon_DTO> list =newHdBus.getAllHoaDon();
        for(HoaDon_DTO hd : list){
             Object[] row = {
                String.valueOf(hd.getMaHD()),
                newKhBUS.getTenKHbyId(hd.getmaKH()),
                newNvBus.getTenNVById(hd.getmaNV()),
                String.valueOf(hd.getNgayLap()),
                String.format("%,.0f VNĐ", hd.getTongTien()),
             };
            tableModel.addRow(row);
        }
    }

    //====================Các Event====================//
    private void addEvents() {
        btSearch.addActionListener(e -> timKiemHoaDon());
        btnFilter.addActionListener(e -> locHoaDon());
        btnReset.addActionListener(e -> resetForm());
    }

        //Xữ lý sự kiện Tìm tiếm
    private void timKiemHoaDon() {
        String loaiTimKiem = (String) cbSearch.getSelectedItem();
        String keyword = tfSearch.getText().trim();
        HoaDon_BUS hdBus = new HoaDon_BUS();

        ArrayList<HoaDon_DTO> list = new ArrayList<>();

        switch (loaiTimKiem) {
            case "Mã hoá đơn":
                try {
                    int maHD = Integer.parseInt(keyword);
                    list = hdBus.searchByMaHD(maHD);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(this, "Mã hoá đơn phải là số!");
                    return;
                }
                break;
            case "Tên nhân viên":
                list = hdBus.searchByTenNhanVien(keyword);
                break;
            case "Tên khách hàng":
                list = hdBus.searchByTenKhachHang(keyword);
                break;
        }

        if (list.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Không tìm thấy kết quả!");
        } else {
            DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
            tableModel.setRowCount(0);

            NhanVien_BUS nvBus = new NhanVien_BUS();
            KhachHang_BUS khBus = new KhachHang_BUS();

            for (HoaDon_DTO hd : list) {
                Object[] row = {
                    hd.getMaHD(),
                    khBus.getTenKHbyId(hd.getmaKH()),
                    nvBus.getTenNVById(hd.getmaNV()),
                    hd.getNgayLap(),
                    String.format("%,.0f VNĐ", hd.getTongTien())
                };
                tableModel.addRow(row);
            }
        }
    }

    //==========================LỌC HOÁ ĐƠN================================
    private void locHoaDon() {
    HoaDon_BUS bus = new HoaDon_BUS();
    ArrayList<HoaDon_DTO> list = new ArrayList<>();

    try {
        // Lấy giá trị ngày
        LocalDateTime from = null, to = null;
        if (dc_filterTimeFrom.getDate() != null)
            from = dc_filterTimeFrom.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
        if (dc_filterTimeTo.getDate() != null)
            to = dc_filterTimeTo.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();

        // Lấy giá trị tiền
        double min = tf_filterPriceFrom.getText().isEmpty() ? 0 : Double.parseDouble(tf_filterPriceFrom.getText());
        double max = tf_filterPriceTo.getText().isEmpty() ? Double.MAX_VALUE : Double.parseDouble(tf_filterPriceTo.getText());

        // Xác định trường hợp lọc
        if (from != null && to != null && (min > 0 || max < Double.MAX_VALUE)) {
            list = bus.filterByDateAndTotal(from, to, min, max);
        } else if (from != null && to != null) {
            list = bus.filterByDate(from, to);
        } else if (min > 0 || max < Double.MAX_VALUE) {
            list = bus.filterByTotal(min, max);
        } else {
            list = bus.getAllHoaDon();
        }

        hienThiBang(list);

    } catch (NumberFormatException ex) {
        JOptionPane.showMessageDialog(this, "Vui lòng nhập số hợp lệ cho khoảng tiền!");
    }
}

private void hienThiBang(ArrayList<HoaDon_DTO> list) {
    DefaultTableModel model = (DefaultTableModel) table.getModel();
    model.setRowCount(0);

    NhanVien_BUS nvBus = new NhanVien_BUS();
    KhachHang_BUS khBus = new KhachHang_BUS();

    for (HoaDon_DTO hd : list) {
        Object[] row = {
            hd.getMaHD(),
            khBus.getTenKHbyId(hd.getmaKH()),
            nvBus.getTenNVById(hd.getmaNV()),
            hd.getNgayLap(),
            String.format("%,.0f VNĐ", hd.getTongTien())
        };
        model.addRow(row);
    }
}

private void resetForm() {
    // Reset thanh tìm kiếm trên cùng
    cbSearch.setSelectedIndex(0);
    tfSearch.setText("");

    // Reset bộ lọc bên trái
    dc_filterTimeFrom.setDate(null);
    dc_filterTimeTo.setDate(null);
    tf_filterPriceFrom.setText("");
    tf_filterPriceTo.setText("");

    // Load lại toàn bộ dữ liệu hóa đơn
    loadHoaDon();
}

private void showChiTietHoaDon(int maHD){


    //Giao diện
    JPanel pnChiTietHoaDon = new JPanel();

    
}




    // ======================= MAIN TEST =======================
    public static void main(String[] args) {
        JPanel pnChiTietHoaDon = new JPanel();
        




        JFrame frame = new JFrame("Test HoaDon - 3 Layers Ready");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000, 700);
        frame.add(pnChiTietHoaDon);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

}

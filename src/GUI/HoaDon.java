package GUI;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import com.toedter.calendar.JDateChooser;
import GUI.GUITool.BorderTool;
import GUI.GUITool.PlaceholderTextField;
import java.awt.*;
import java.util.ArrayList;

import BUS.*;
import DTO.*;
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




    // ======================= MAIN TEST =======================
    public static void main(String[] args) {
        JFrame frame = new JFrame("Test HoaDon - 3 Layers Ready");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000, 700);
        frame.add(new HoaDon());
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

}

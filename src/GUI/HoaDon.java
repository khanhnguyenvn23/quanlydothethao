package GUI;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import GUI.GUITool.BorderTool;
import GUI.GUITool.PlaceholderTextField;

import java.awt.*;

//----------------------------------------------Giao Diện--------------------------------------------------
public class HoaDon extends JPanel{
    JPanel topMenuPanel, leftMenuPanel, rightMenuPanel;
    public HoaDon(){
        setLayout(new BorderLayout());

        //Phan top Menu 
        topMenuPanel = new JPanel();
        topMenuPanel.setLayout(new BorderLayout());
        topMenuPanel.setBackground(Color.WHITE);
        BorderTool.setPadding(topMenuPanel,20,10,20,20);
            //Phần tìm kiếm
            JPanel SearchField = new JPanel();
            SearchField.setLayout(new FlowLayout(FlowLayout.LEFT, 15, 0));
            SearchField.setBackground(Color.WHITE);
                JComboBox<String>  cbSearch = new JComboBox<>(new String[]{
                    "Mã hoá đơn", "Tên khách hàng"
                });
                cbSearch.setPreferredSize(new Dimension(140, 30));
                
                JTextField tfSearch = new PlaceholderTextField("Nhập nội dung tìm kiếm...");
                tfSearch.setPreferredSize(new Dimension(400,30));

                JButton btSearch = new JButton("Tìm kiếm");
                btSearch.setFocusPainted(false);
        
            SearchField.add(cbSearch);
            SearchField.add(tfSearch);
            SearchField.add(btSearch);

            JPanel ButtonField = new JPanel();
            ButtonField.setBackground(Color.WHITE);
            ButtonField.setLayout(new FlowLayout(FlowLayout.RIGHT));
                JButton btChitiet = new JButton("Xem chi tiết");
                btChitiet.setBackground(new Color(52, 152, 219));
                btChitiet.setForeground(Color.WHITE);
                btChitiet.setFocusPainted(false);
            ButtonField.add(btChitiet);
        
        topMenuPanel.add(SearchField,BorderLayout.CENTER);
        topMenuPanel.add(ButtonField,BorderLayout.EAST);


        // ==== Panel chính ====
        leftMenuPanel = new JPanel();
        leftMenuPanel.setLayout(new BoxLayout(leftMenuPanel, BoxLayout.Y_AXIS));
        leftMenuPanel.setBackground(Color.WHITE);
        leftMenuPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createTitledBorder("Bộ lọc tìm kiếm"),
                new EmptyBorder(10, 10, 10, 10)
        ));
        leftMenuPanel.setPreferredSize(new Dimension(220, leftMenuPanel.getPreferredSize().height));

        // ==== Kích thước textfield giãn hết chiều ngang ====
            Dimension fieldSize = new Dimension(Integer.MAX_VALUE, 25);

            // ==== Lọc theo thời gian ====
            JLabel lb_filterTimeFrom = new JLabel("Từ ngày:");
            lb_filterTimeFrom.setAlignmentX(Component.LEFT_ALIGNMENT);
            JTextField tf_filterTimeFrom = new JTextField();
            tf_filterTimeFrom.setMaximumSize(fieldSize);
            tf_filterTimeFrom.setAlignmentX(Component.LEFT_ALIGNMENT);

            JLabel lb_filterTimeTo = new JLabel("Đến ngày:");
            lb_filterTimeTo.setAlignmentX(Component.LEFT_ALIGNMENT);
            JTextField tf_filterTimeTo = new JTextField();
            tf_filterTimeTo.setMaximumSize(fieldSize);
            tf_filterTimeTo.setAlignmentX(Component.LEFT_ALIGNMENT);

            // ==== Lọc theo giá ====
            JLabel lb_filterPriceFrom = new JLabel("Từ số tiền (VNĐ):");
            lb_filterPriceFrom.setAlignmentX(Component.LEFT_ALIGNMENT);
            JTextField tf_filterPriceFrom = new JTextField();
            tf_filterPriceFrom.setMaximumSize(fieldSize);
            tf_filterPriceFrom.setAlignmentX(Component.LEFT_ALIGNMENT);

            JLabel lb_filterPriceTo = new JLabel("Đến số tiền (VNĐ):");
            lb_filterPriceTo.setAlignmentX(Component.LEFT_ALIGNMENT);
            JTextField tf_filterPriceTo = new JTextField();
            tf_filterPriceTo.setMaximumSize(fieldSize);
            tf_filterPriceTo.setAlignmentX(Component.LEFT_ALIGNMENT);

        // ==== Thêm các thành phần vào panel với khoảng cách ====
        leftMenuPanel.add(lb_filterTimeFrom);
        leftMenuPanel.add(Box.createVerticalStrut(5));
        leftMenuPanel.add(tf_filterTimeFrom);
        leftMenuPanel.add(Box.createVerticalStrut(10));
        leftMenuPanel.add(lb_filterTimeTo);
        leftMenuPanel.add(Box.createVerticalStrut(5));
        leftMenuPanel.add(tf_filterTimeTo);
        leftMenuPanel.add(Box.createVerticalStrut(15));

        leftMenuPanel.add(lb_filterPriceFrom);
        leftMenuPanel.add(Box.createVerticalStrut(5));
        leftMenuPanel.add(tf_filterPriceFrom);
        leftMenuPanel.add(Box.createVerticalStrut(10));
        leftMenuPanel.add(lb_filterPriceTo);
        leftMenuPanel.add(Box.createVerticalStrut(5));
        leftMenuPanel.add(tf_filterPriceTo);
        leftMenuPanel.add(Box.createVerticalStrut(20)); // khoảng cách trước nút

        // ==== Panel nút Lọc và Làm mới ====
            JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 0)); // căn trái
            buttonPanel.setBackground(Color.WHITE);
            buttonPanel.setAlignmentX(Component.LEFT_ALIGNMENT); // căn trái trong BoxLayout

            JButton btnFilter = new JButton("Lọc");
            btnFilter.setFocusPainted(false);
            JButton btnReset = new JButton("Làm mới");
            btnReset.setFocusPainted(false);

            buttonPanel.add(btnFilter);
            buttonPanel.add(btnReset);

        // Thêm panel nút vào cuối
        leftMenuPanel.add(buttonPanel);

        // ==== Panel phải ====
        rightMenuPanel = new JPanel();
        rightMenuPanel.setLayout(new BorderLayout()); // dùng BorderLayout để JScrollPane giãn hết panel
        rightMenuPanel.setBackground(Color.WHITE);
        BorderTool.setPadding(rightMenuPanel, 10, 10, 10, 10);

        // ==== Tạo dữ liệu mẫu cho JTable ====
        String[] columnNames = {"Mã hóa đơn", "Tên khách hàng", "Thời gian", "Tổng số tiền"};
        Object[][] data = {
            {"HD001", "Nguyễn Văn A", "30/10/2025", "1.000.000"},
            {"HD002", "Trần Thị B", "30/10/2025", "2.500.000"},
            {"HD003", "Lê Văn C", "29/10/2025", "3.200.000"},
            // thêm dữ liệu khác nếu muốn
        };

        // ==== Tạo JTable ====
        JTable table = new JTable(data, columnNames){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // không cho sửa bất kỳ ô nào
            }
        };;
        table.setFillsViewportHeight(true); // bảng giãn theo chiều cao
        table.setAutoCreateRowSorter(true);  // cho phép sort các cột
        table.setRowHeight(28);
        table.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        table.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 14));
        table.getTableHeader().setBackground(new Color(41, 128, 185));
        table.getTableHeader().setForeground(Color.WHITE);
        table.setGridColor(new Color(230, 230, 230));

        // ==== Thêm JTable vào JScrollPane ====
        JScrollPane scpTable = new JScrollPane(table);

        // ==== Thêm JScrollPane vào panel phải ====
        rightMenuPanel.add(scpTable, BorderLayout.CENTER);



        add(topMenuPanel,BorderLayout.NORTH);
        add(leftMenuPanel,BorderLayout.WEST);
        add(rightMenuPanel,BorderLayout.CENTER);

        setVisible(true);
    }
    
    public static void main(String args[]){
        JFrame frame = new JFrame("Test Hoa Don!");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000, 700);
        frame.add(new HoaDon());
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

}


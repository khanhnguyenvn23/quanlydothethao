/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI;

/**
 *
 * @author khanhnguyen
 */


import javax.swing.*;  // Nhập tất cả các lớp trong gói javax.swing
import java.awt.*;      // Nhập tất cả các lớp trong gói java.awt

public class home extends JFrame {
    private CardLayout cardlayout ;
    private JPanel contentPanel;
    public home(){
        setTitle("Hệ thống quản lý cửa hàng");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1200, 700);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
            // Panel menu bên trái
        JPanel menuPanel = createMenuPanel();

        // Panel hiển thị nội dung
        contentPanel = new JPanel();
        cardlayout = new CardLayout();
        contentPanel.setLayout(cardlayout);
        
        contentPanel.add(new PhieuNhap(), "phieunhap");
        contentPanel.add(new DanhMuc(),"danhmuc");
        contentPanel.add(new HoaDon(),"hoadon");

         add(menuPanel, BorderLayout.WEST);
        add(contentPanel, BorderLayout.CENTER);

        
//         JPanel menuPanel = createMenupanel();
//         add(menuPanel, BorderLayout.WEST);

        setVisible(true);
    }
    
    
    public JPanel createMenuPanel(){
        //tạo panel chứa menu và sắp dọc nó border layout;
        JPanel panel= new JPanel();
        panel.setPreferredSize(new Dimension(200, getHeight()));
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(new Color(240, 248, 255));
        // tạo label ghi tên ;
         JLabel userLabel = new JLabel("👤 Nguyễn Hùng Khánh");
        userLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        userLabel.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));
        panel.add(userLabel);
        
        // demo trước với chức năng phiếu nhập
        //ai muốn thêm chức năng thì viết class GUI trước rồi add vào trên constructor và hàm này
         panel.add(createMenuButton("Phiếu nhập", "phieunhap", "/icon/phieunhap.png"));
        panel.add(createMenuButton("Loại sản phẩm", "danhmuc", "/icon/phieunhap.png"));
         panel.add(createMenuButton("Hoá đơn", "hoadon", "/icon/phieuxuat.png"));

        return panel ;
        
    }
    
    
    public JButton createMenuButton(String title , String cardName, String iconPath){
         Icon icon;
        java.net.URL imgURL = getClass().getResource(iconPath);
        if (imgURL != null) {
            Image img = new ImageIcon(imgURL).getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
            icon = new ImageIcon(img);
        } else {
            System.err.println("Không tìm thấy icon: " + iconPath);
            icon = UIManager.getIcon("OptionPane.informationIcon");
        }

        JButton btn = new JButton(title, icon);
        btn.setAlignmentX(Component.CENTER_ALIGNMENT);
        btn.setHorizontalAlignment(SwingConstants.LEFT);
        btn.setBorderPainted(false);
        btn.setIconTextGap(10);
        btn.setMaximumSize(new Dimension(180, 60));
        btn.setFocusPainted(false);
        btn.setBackground(new Color(240, 248, 255));
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btn.addActionListener(e -> cardlayout.show(contentPanel, cardName));
        btn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent e) {
                btn.setBackground(new Color(182, 232, 243));
            }
            @Override
            public void mouseExited(java.awt.event.MouseEvent e) {
                btn.setBackground(new Color(240, 248, 255));
            }
        });
        return btn;
    }
    
    
    public static void main(String args[]){
        home h1= new home();
    }
}

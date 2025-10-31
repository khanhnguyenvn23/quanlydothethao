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
import DTO.NCC_DTO;
import DTO.SanPham_DTO;
public class ThemPhieuNhap extends JFrame {
    int idnv ;
   
    JButton themsp,xuatphieu,xoasp;
    JTextField soluong,gianhaphang,idphieu,tennhanvien,masp;
    JComboBox<NCC_DTO>nhacungcap; 
    JTable sp, listsp;
    DefaultTableModel model1, model2;
    NhaCungCap_BUS nccbus= new NhaCungCap_BUS();
    SanPham_BUS spbus=new SanPham_BUS();
    PhieuNhap_BUS pnbus=new PhieuNhap_BUS();
    NhanVien_BUS nvbus= new NhanVien_BUS();

   public ThemPhieuNhap(int idnv){
       this.setSize(1000,800);
       this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setLocationRelativeTo(null);
        //dùng grrid layout cho frame cha;
        this.setLayout(new GridLayout(2,1));

        //tạo layout cho phần trên gồm một bảng sản phẩm và một form thông tin bằng grid layout

         JPanel toppanel= new JPanel();
         toppanel.setLayout(new GridLayout(1,2,10,0));
         toppanel.setPreferredSize(new Dimension(1000, 400));

         JPanel lefttop= new JPanel();
         
         lefttop.setBackground(new Color(200, 230, 255));
         


          JPanel righttop= new JPanel();
         
        righttop.setBackground(new Color(255, 255, 255));
         toppanel.add(lefttop);
           toppanel.add(righttop);


        // PHẦN DƯỚI: chỉ 1 panel
        // để làm chi tiết list sp
        JPanel bottomPanel = new JPanel();
        bottomPanel.setBackground(Color.LIGHT_GRAY);
        bottomPanel.add(new JLabel("Phần dưới"));

       
        this.add(toppanel);
        this.add(bottomPanel);
        
// add bảng sản phẩm vào panel
   lefttop.setLayout(new BorderLayout());
   lefttop.setBorder(BorderFactory.createTitledBorder("Danh sách sản phẩm"));
    model1= new DefaultTableModel();

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



   //add form thông tin vào bên phải 
   righttop.setLayout(new GridBagLayout());
righttop.setBorder(new EmptyBorder(0, 40, 20, 40)); // lề ngoài
GridBagConstraints gbc = new GridBagConstraints();
gbc.insets = new Insets(30, 10, 10, 10); // khoảng cách giữa các hàng
gbc.fill = GridBagConstraints.HORIZONTAL;

// hàng 1 - Mã phiếu
gbc.gridx = 0; gbc.gridy = 0;
righttop.add(new JLabel("Mã Phiếu"), gbc);

gbc.gridx = 1; gbc.gridy = 0;
idphieu = new JTextField(15);
idphieu.setEditable(false);
righttop.add(idphieu, gbc);

// hàng 2 - Nhà cung cấp
gbc.gridx = 0; gbc.gridy = 1;
righttop.add(new JLabel("Nhà cung cấp"), gbc);

gbc.gridx = 1; gbc.gridy = 1;
nhacungcap = new JComboBox<>();
righttop.add(nhacungcap, gbc);

// hàng 3 - Nhân viên nhập
gbc.gridx = 0; gbc.gridy = 2;
righttop.add(new JLabel("Nhân viên nhập"), gbc);

gbc.gridx = 1; gbc.gridy = 2;
tennhanvien = new JTextField(15);
tennhanvien.setEditable(false);
righttop.add(tennhanvien, gbc);

// hàng 4 - Số lượng thêm
gbc.gridx = 0; gbc.gridy = 3;
righttop.add(new JLabel("Số lượng thêm"), gbc);

gbc.gridx = 1; gbc.gridy = 3;
soluong = new JTextField(15);
righttop.add(soluong, gbc);


// hàng 5 - giá nhập 
gbc.gridx = 0; gbc.gridy = 4;
righttop.add(new JLabel("Giá nhập"), gbc);

gbc.gridx = 1; gbc.gridy = 4;
gianhaphang = new JTextField(15);
righttop.add(gianhaphang, gbc);


loaddatatoform();






this.setVisible(true);

}

public void loaddatatoform(){
    int idpnnext=pnbus.selectNextid();
    idphieu.setText(String.valueOf(idpnnext));
 for(NCC_DTO i: nccbus.getListNCC()){
    nhacungcap.addItem(i);
 }
  tennhanvien.setText(nvbus.getTenNVById(this.idnv));


}
   public void loadsanphamtotable(){
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
package DAO;
import java.sql.*;

public class ConnectDatabase {
    public static Connection getconection(){
        Connection con = null;
        try {
            String dbUrl = "jdbc:sqlserver://localhost:1433;DatabaseName=quanlythethao;encrypt=false";
            String username = "sa"; 
            String password = "123";
            con = DriverManager.getConnection(dbUrl, username, password);
            System.out.println("Kết nối thành công!");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Kết nối thất bại!");
        }
        return con;
    }

    public static void closeconection(Connection con){
        try {
            if(con != null){
                con.close();
                System.out.println("Đóng kết nối thành công!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Phương thức main để test kết nối
    public static void main(String[] args) {
        Connection con = ConnectDatabase.getconection();
        ConnectDatabase.closeconection(con);
    }
}

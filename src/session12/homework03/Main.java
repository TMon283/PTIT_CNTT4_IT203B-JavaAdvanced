package session12.homework03;

import java.sql.*;
import java.util.Scanner;

// Phần 1: Phân tích
// - Khi làm việc với CallableStatement, ta cần khai báo rõ ràng kiểu dữ liệu của tham số đầu ra
// để driver biết cách cấp phát bộ nhớ và ánh xạ dữ liệu từ SQL sang Java. Nếu không đăng ký trước,
// khi thực thi thủ tục, JDBC không biết phải lấy dữ liệu ở vị trí nào và kiểu gì,
// dẫn đến lỗi "The column index is out of range" hoặc không thể đọc được giá trị
// - Ta phải đăng ký bằng hằng số Types.DECIMAL trong lớp java.sql.Types

// Phần 2: Thực thi
public class Main {
    private static final String URL = "jdbc:mysql://localhost:3306/hospital_db";
    private static final String USER = "root";
    private static final String PASSWORD = "mona32006";

    public static void main(String[] args) {
        Connection conn = null;
        CallableStatement cstmt = null;
        Scanner scanner = new Scanner(System.in);
        try {
            conn = DriverManager.getConnection(URL, USER, PASSWORD);
            cstmt = conn.prepareCall("{call get_surgery_cost(?, ?)}");

            System.out.print("Nhập mã phẫu thuật: ");
            int surgeryId = scanner.nextInt();
            cstmt.setInt(1, surgeryId);
            cstmt.registerOutParameter(2, Types.DECIMAL);
            cstmt.execute();

            java.math.BigDecimal totalCost = cstmt.getBigDecimal(2);

            System.out.println("Tổng chi phí phẫu thuật ID " + surgeryId + " là: " + totalCost);

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (cstmt != null) cstmt.close();
                if (conn != null) conn.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }
}


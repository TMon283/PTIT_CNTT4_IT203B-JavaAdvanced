package session12.homework02;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Scanner;

// Phần 1: Phân tích
// - Các phương thức này không chuyển số thành chuỗi để nối vào SQL. Thay vào đó,
// chúng truyền giá trị số thô trực tiếp cho DBMS.
// - DBMS sẽ tự hiểu và lưu đúng kiểu dữ liệu (DOUBLE, INT) mà không phụ thuộc vào định dạng
// dấu chấm/dấu phẩy của hệ điều hành.

// Phần 2: Thực thi
public class Main {
    private static final String URL = "jdbc:mysql://localhost:3306/hospital_db";
    private static final String USER = "root";
    private static final String PASSWORD = "mona32006";

    public static void main(String[] args) {
        Connection conn = null;
        PreparedStatement stmt = null;
        Scanner scanner = new Scanner(System.in);
        try {
            conn = DriverManager.getConnection(URL, USER, PASSWORD);
            String sql = "UPDATE patients SET temperature = ?, heart_rate = ? WHERE patient_id = ?";
            stmt = conn.prepareStatement(sql);

            System.out.print("Nhập mã bệnh nhân: ");
            int patient_id = scanner.nextInt();
            System.out.print("Nhập nhiệt độ thân thể bệnh nhân: ");
            double temperature = scanner.nextDouble();
            System.out.print("Nhập nhịp tim bệnh nhân: ");
            int heart_rate = scanner.nextInt();

            stmt.setDouble(1, temperature);
            stmt.setInt(2, heart_rate);
            stmt.setInt(3, patient_id);

            int rowsUpdated = stmt.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Cập nhật chỉ số sinh tồn thành công!");
            } else {
                System.out.println("Không tìm thấy bệnh nhân để cập nhật.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (stmt != null) stmt.close();
            } catch (Exception ignored) {}
            try {
                if (conn != null) conn.close();
            } catch (Exception ignored) {}
        }
    }
}


package session12.homework01;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

// Phần 1: Phân tích
// - PreparedStatement lại được coi là "tấm khiên" chống lại SQL Injection vì giúp tách biệt câu lệnh SQL và
// dữ liệu đầu vào. Câu lệnh SQL sẽ được định nghĩa trước, còn dữ liệu sẽ được truyền vào sau.
// - Cơ chế Pre-compiled: biên dịch + tối ưu câu lệnh SQL một lần, sau đó các tham số được gán vào
// dưới dạng giá trị chứ không phải mã lệnh -> tránh được SQL Injection

// Phần 2: Thực thi
public class Main {
    private static final String URL = "jdbc:mysql://localhost:3306/hospital_db";
    private static final String USER = "root";
    private static final String PASSWORD = "mona32006";

    public boolean login(String doctorCode, String password) {
        String sql = "select * from doctors where doctor_code = ? and password = ?";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setString(1, doctorCode);
                pstmt.setString(2, password);
             ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                System.out.println("Đăng nhập thành công");
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("Đăng nhập thất bại");
        return false;
    }
    public static void main(String[] args) {
        Main loginService = new Main();
        loginService.login("D001", "123456");
    }
}

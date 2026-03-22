package session11.src.homework01;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

// Phần 1: Phân tích
// Ảnh hưởng trong hệ thống y tế và hậu quả:
//- Gây rò rỉ tài nguyên
//- Gián đoạn công việc khi hệ thống bị treo (không thể truy xuất dữ liệu bệnh nhân, không thể nhập dữ liệu cấp cứu)
//- Gây mất an toàn dữ liệu
//- Giảm hiệu năng khi server phải duy trì nhiều kết nối không cần thiết

// Phần 2: Thực thi
public class DBContext {
    // cấu hình
    private static final String URL = "jdbc:mysql://localhost:3306/session11";
    private static final String USER = "root";
    private static final String PASSWORD = "mona32006";

    // connection
    private Connection getConnection() throws Exception {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    public void getPatientById(int patientId) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = getConnection();
            String sql = "SELECT * FROM patients WHERE id = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, patientId);
            rs = stmt.executeQuery();

            while (rs.next()) {
                System.out.println("Patient Name: " + rs.getString("name"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
            } catch (Exception ignored) {}
            try {
                if (stmt != null) stmt.close();
            } catch (Exception ignored) {}
            try {
                if (conn != null) conn.close();
            } catch (Exception ignored) {}
        }
    }
}

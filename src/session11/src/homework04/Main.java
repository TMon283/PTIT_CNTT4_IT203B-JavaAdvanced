package session11.src.homework04;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

// Phần 1: Phân tích
//Khi người dùng nhập thêm đoạn OR '1'='1', mệnh đề WHERE trở thành luôn đúng vì '1'='1' là một biểu thức logic đúng (true).
//Kết quả: truy vấn không chỉ trả về bệnh nhân có tên cụ thể, mà trả về toàn bộ bảng Patients.

// Phần 2: Thực thi
public class Main {
    private static final String URL = "jdbc:mysql://localhost:3306/session11";
    private static final String USER = "root";
    private static final String PASSWORD = "mona32006";

    public static void main(String[] args) {
        String userInput = "Nguyen Van A OR '1'='1'";

        userInput = userInput.replaceAll("--", "")
                .replaceAll(";", "")
                .replaceAll("'", "");

        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            conn = DriverManager.getConnection(URL, USER, PASSWORD);
            stmt = conn.createStatement();

            String sql = "SELECT * FROM Patients WHERE full_name = '" + userInput + "'";
            rs = stmt.executeQuery(sql);

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


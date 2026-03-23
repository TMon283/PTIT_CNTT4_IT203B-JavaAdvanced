package session12.homework04;

import java.math.BigDecimal;
import java.sql.*;
import java.time.LocalDate;

// Phần 1: Phân tích
// - Vì cấu trúc câu lệnh giống nhau, việc lặp lại 1.000 lần gây lãng phí CPU,
// bộ nhớ và làm chậm tốc độ nạp dữ liệu.
// -> Khởi tạo PreparedStatement một lần duy nhất bên ngoài vòng lặp.
//   + Trong vòng lặp, chỉ thay đổi tham số và gọi executeUpdate().
//   + JDBC driver và Database sẽ tái sử dụng Execution Plan, giảm tải đáng kể.

// Phần 2: Thực thi
public class Main {
    private static final String URL = "jdbc:mysql://localhost:3306/hospital_db";
    private static final String USER = "root";
    private static final String PASSWORD = "mona32006";

    public static void main(String[] args) {
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = DriverManager.getConnection(URL, USER, PASSWORD);
            String sql = "insert into blood_test_results (patient_id, test_date, hemoglobin, cholesterol) "
                    + "values (?, ?, ?, ?)";
            pstmt = conn.prepareStatement(sql);

            for (int i = 1; i <= 1000; i++) {
                pstmt.setInt(1, i);
                pstmt.setDate(2, Date.valueOf(LocalDate.now()));
                pstmt.setBigDecimal(3, BigDecimal.valueOf(13.5));
                pstmt.setBigDecimal(4, BigDecimal.valueOf(3.6));

                pstmt.executeUpdate();
            }

            System.out.println("Đã nạp xong 1000 kết quả xét nghiệm.");

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }
}


package session11.src.homework02;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

// Phần 1: Phân tích
//- Khi dùng if (rs.next()), con trỏ của ResultSet chỉ di chuyển một lần từ vị trí ban đầu
// (trước dòng đầu tiên) sang dòng đầu tiên. Sau đó, khối lệnh trong if chỉ thực thi đúng một lần,
// nên chỉ in ra được bản ghi đầu tiên. Nếu bảng trống, rs.next() trả về false, khối if không chạy,
// dẫn đến không có dữ liệu in ra (thậm chí có thể gây lỗi nếu vẫn cố truy xuất cột).
//- Để in toàn bộ danh sách, ta cần một vòng lặp (while) để duyệt qua tất cả các dòng.
// Mỗi lần gọi rs.next(), con trỏ sẽ di chuyển sang bản ghi kế tiếp cho đến khi hết dữ liệu (trả về false).

// Phần 2: Thực thi
public class Main {
    private static final String URL = "jdbc:mysql://localhost:3306/hospital_db";
    private static final String USER = "root";
    private static final String PASSWORD = "mona32006";

    public static void main(String[] args) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Scanner scanner = new Scanner(System.in);

        try {
            conn = DriverManager.getConnection(URL, USER, PASSWORD);
            String sql = "SELECT * FROM doctors WHERE doctor_code = ? AND password = ?";
            stmt = conn.prepareStatement(sql);

            System.out.print("Nhập mã bác sĩ: ");
            String doctor_code = scanner.nextLine();
            System.out.print("Nhập mật khẩu: ");
            String password = scanner.nextLine();
            stmt.setString(1, doctor_code);
            stmt.setString(2, password);

            rs = stmt.executeQuery();

            if (rs.next()) {
                System.out.println("Đăng nhập thành công");
            } else {
                System.out.println("Đăng nhập thất bại");
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


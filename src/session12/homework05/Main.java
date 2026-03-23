package session12.homework05;

import java.sql.*;
import java.util.Scanner;

public class Main {
    private static final String URL = "jdbc:mysql://localhost:3306/hospital_menu_db";
    private static final String USER = "root";
    private static final String PASSWORD = "mona32006";

    private static Connection conn;

    public static void main(String[] args) {
        try {
            conn = DriverManager.getConnection(URL, USER, PASSWORD);
            Scanner sc = new Scanner(System.in);
            int choice;

            do {
                System.out.println("===== MENU =====");
                System.out.println("1. Danh sách bệnh nhân");
                System.out.println("2. Tiếp nhận bệnh nhân mới");
                System.out.println("3. Cập nhật bệnh án");
                System.out.println("4. Xuất viện & Tính phí");
                System.out.println("5. Thoát");
                System.out.print("Chọn: ");
                choice = sc.nextInt();
                sc.nextLine();

                switch (choice) {
                    case 1: listPatients();
                        break;
                    case 2: addPatient(sc);
                        break;
                    case 3: updateRecord(sc);
                        break;
                    case 4: dischargeAndFee(sc);
                        break;
                    case 5: System.out.println("Thoát chương trình.");
                        break;
                    default: System.out.println("Lựa chọn không hợp lệ.");
                }
            } while (choice != 5);

            sc.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // 1. Danh sách bệnh nhân
    private static void listPatients() throws SQLException {
        String sql = "select patient_id, name, age, department from patients";
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(sql);
        while (rs.next()) {
            System.out.printf("%d - %s - %d tuổi - %s%n",
                    rs.getInt("patient_id"),
                    rs.getString("name"),
                    rs.getInt("age"),
                    rs.getString("department"));
        }
        rs.close();
        stmt.close();
    }

    // 2. Tiếp nhận bệnh nhân mới
    private static void addPatient(Scanner sc) throws SQLException {
        System.out.print("Tên: ");
        String name = sc.nextLine();
        System.out.print("Tuổi: ");
        int age = sc.nextInt(); sc.nextLine();
        System.out.print("Khoa: ");
        String dept = sc.nextLine();

        String sql = "insert into patients(name, age, department) values (?, ?, ?)";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, name);
        pstmt.setInt(2, age);
        pstmt.setString(3, dept);

        pstmt.executeUpdate();
        pstmt.close();
        System.out.println("Đã thêm bệnh nhân mới vào CSDL.");
    }

    // 3. Cập nhật bệnh án
    private static void updateRecord(Scanner sc) throws SQLException {
        System.out.print("Mã BN: ");
        int id = sc.nextInt(); sc.nextLine();
        System.out.print("Thông tin bệnh lý mới: ");
        String record = sc.nextLine();

        String sql = "update patients set medical_record=? where patient_id=?";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, record);
        pstmt.setInt(2, id);
        int rows = pstmt.executeUpdate();
        pstmt.close();
        System.out.println(rows > 0 ? "Đã cập nhật bệnh án." : "Không tìm thấy bệnh nhân.");
    }

    // 4. Xuất viện & Tính phí
    private static void dischargeAndFee(Scanner sc) throws SQLException {
        System.out.print("Mã BN: ");
        int id = sc.nextInt();

        CallableStatement cstmt = conn.prepareCall("{call CALCULATE_DISCHARGE_FEE(?, ?)}");
        cstmt.setInt(1, id);
        cstmt.registerOutParameter(2, Types.DECIMAL);
        cstmt.execute();

        java.math.BigDecimal fee = cstmt.getBigDecimal(2);
        System.out.println("Tổng viện phí BN " + id + " là: " + fee);

        cstmt.close();
    }
}

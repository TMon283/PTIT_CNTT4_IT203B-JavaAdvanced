package session14.btth;

import java.sql.*;
import java.util.Scanner;

public class Main {
    private static final String URL = "jdbc:mysql://localhost:3306/btth_ss14";
    private static final String USER = "root";
    private static final String PASSWORD = "mona32006";

    public static void main(String[] args) {
        Connection con = null;
        Scanner scanner = new Scanner(System.in);

        try {
            con = DriverManager.getConnection(URL, USER, PASSWORD);
            con.setAutoCommit(false);

            System.out.print("Nhập AccountId người gửi: ");
            String senderId = scanner.nextLine();
            System.out.print("Nhập AccountId người nhận: ");
            String receiverId = scanner.nextLine();
            System.out.print("Nhập số tiền muốn chuyển: ");
            double amount = scanner.nextDouble();

            String checkSql = "SELECT Balance FROM Accounts WHERE AccountId = ?";
            try (PreparedStatement ps = con.prepareStatement(checkSql)) {
                ps.setString(1, senderId);
                ResultSet rs = ps.executeQuery();
                if (!rs.next()) {
                    throw new SQLException("Tài khoản người gửi không tồn tại!");
                }
                double balance = rs.getDouble("Balance");
                if (balance < amount) {
                    throw new SQLException("Số dư không đủ để thực hiện giao dịch!");
                }
            }

            try (CallableStatement cstmt1 = con.prepareCall("{call sp_UpdateBalance(?, -?)}")) {
                cstmt1.setString(1, senderId);
                cstmt1.setBigDecimal(2, new java.math.BigDecimal(amount));
                cstmt1.execute();
            }

            try (CallableStatement cstmt2 = con.prepareCall("{call sp_UpdateBalance(?, ?)}")) {
                cstmt2.setString(1, receiverId);
                cstmt2.setBigDecimal(2, new java.math.BigDecimal(amount));
                cstmt2.execute();
            }

            con.commit();
            System.out.println("Chuyển khoản thành công!");

            String resultSql = "SELECT AccountId, FullName, Balance FROM Accounts WHERE AccountId IN (?, ?)";
            try (PreparedStatement ps = con.prepareStatement(resultSql)) {
                ps.setString(1, senderId);
                ps.setString(2, receiverId);
                ResultSet rs = ps.executeQuery();
                System.out.println("Kết quả sau giao dịch:");
                System.out.printf("%-10s %-20s %-10s%n", "AccountId", "FullName", "Balance");
                while (rs.next()) {
                    System.out.printf("%-10s %-20s %-10.2f%n",
                            rs.getString("AccountId"),
                            rs.getString("FullName"),
                            rs.getDouble("Balance"));
                }
            }

        } catch (Exception e) {
            try {
                if (con != null) con.rollback();
                System.out.println("Giao dịch thất bại, rollback toàn bộ!");
                System.out.println("Lỗi: " + e.getMessage());
            } catch (SQLException ex) {
                System.out.println("Rollback lỗi: " + ex.getMessage());
            }
        } finally {
            try {
                if (con != null) con.close();
            } catch (SQLException e) {
                System.out.println("Đóng kết nối lỗi: " + e.getMessage());
            }
        }
    }
}

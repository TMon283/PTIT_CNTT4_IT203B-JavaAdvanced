package session14.miniProject.DAO;

import session14.miniProject.config.DatabaseConnectionManager;
import session14.miniProject.exception.DataAccessException;
import session14.miniProject.model.Order;
import session14.miniProject.model.OrderItem;

import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class ReportingDAO {
    private final DatabaseConnectionManager connectionManager;

    public ReportingDAO(DatabaseConnectionManager connectionManager) {
        this.connectionManager = connectionManager;
    }

    public List<String> getTop5Buyers() {
        String call = "{call SP_GetTopBuyers()}";
        List<String> lines = new ArrayList<>();
        try (Connection con = connectionManager.getConnection();
             CallableStatement callableStatement = con.prepareCall(call);
             ResultSet rs = callableStatement.executeQuery()) {
            while (rs.next()) {
                lines.add(
                    rs.getString("username") + " - tong so luong da mua: " + rs.getInt("total_quantity")
                );
            }
            return lines;
        } catch (SQLException e) {
            throw new DataAccessException("Loi khi goi SP_GetTopBuyers.", e);
        }
    }

    public List<String> getRevenueByCategory() {
        String call = "{call SP_GetCategoryRevenue()}";
        List<String> lines = new ArrayList<>();
        try (Connection con = connectionManager.getConnection();
             CallableStatement callableStatement = con.prepareCall(call);
             ResultSet rs = callableStatement.executeQuery()) {
            while (rs.next()) {
                lines.add(
                    rs.getString("category") + " - doanh thu: " + rs.getBigDecimal("revenue")
                );
            }
            return lines;
        } catch (SQLException e) {
            throw new DataAccessException("Loi khi goi SP_GetCategoryRevenue.", e);
        }
    }

    public BigDecimal calculateCategoryRevenue(String category) {
        String call = "{? = call FUNC_CalculateCategoryRevenue(?)}";
        try (Connection con = connectionManager.getConnection();
             CallableStatement callableStatement = con.prepareCall(call)) {
            callableStatement.registerOutParameter(1, Types.DECIMAL);
            callableStatement.setString(2, category);
            callableStatement.execute();
            return callableStatement.getBigDecimal(1);
        } catch (SQLException e) {
            throw new DataAccessException("Loi khi goi function tinh doanh thu theo category.", e);
        }
    }

    public List<Order> getOrdersWithItems() {
        String sql = "SELECT o.order_id, o.user_id, u.username, o.created_at, " +
            "od.order_detail_id, od.product_id, p.product_name, od.quantity, od.unit_price " +
            "FROM Orders o " +
            "JOIN Users u ON u.user_id = o.user_id " +
            "LEFT JOIN Order_Details od ON od.order_id = o.order_id " +
            "LEFT JOIN Products p ON p.product_id = od.product_id " +
            "ORDER BY o.order_id DESC, od.order_detail_id ASC";

        Map<Integer, Order> orderMap = new LinkedHashMap<>();
        try (Connection con = connectionManager.getConnection();
             PreparedStatement preparedStatement = con.prepareStatement(sql);
             ResultSet rs = preparedStatement.executeQuery()) {
            while (rs.next()) {
                int orderId = rs.getInt("order_id");
                Order order = orderMap.get(orderId);
                if (order == null) {
                    order = new Order(
                        orderId,
                        rs.getInt("user_id"),
                        rs.getString("username"),
                        rs.getTimestamp("created_at")
                    );
                    orderMap.put(orderId, order);
                }

                int orderDetailId = rs.getInt("order_detail_id");
                if (!rs.wasNull()) {
                    order.addItem(
                        new OrderItem(
                            orderDetailId,
                            rs.getInt("product_id"),
                            rs.getString("product_name"),
                            rs.getInt("quantity"),
                            rs.getBigDecimal("unit_price")
                        )
                    );
                }
            }
            return new ArrayList<>(orderMap.values());
        } catch (SQLException e) {
            throw new DataAccessException("Loi khi lay danh sach orders va order items.", e);
        }
    }
}

package session14.miniProject.DAO;

import session14.miniProject.exception.DataAccessException;
import session14.miniProject.model.OrderItemRequest;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class OrderDAO {
    public void placeOrder(Connection connection, int userId, List<OrderItemRequest> items) {
        String lockStockSql = "SELECT stock FROM Products WHERE product_id = ? FOR UPDATE";
        String updateStockSql = "UPDATE Products SET stock = stock - ? WHERE product_id = ?";
        String createOrderSql = "INSERT INTO Orders(user_id) VALUES(?)";
        String insertOrderDetailSql = "INSERT INTO Order_Details(order_id, product_id, quantity, unit_price) " +
            "VALUES(?, ?, ?, (SELECT price FROM Products WHERE product_id = ?))";

        try (PreparedStatement lockStockStatement = connection.prepareStatement(lockStockSql);
             PreparedStatement updateStockStatement = connection.prepareStatement(updateStockSql);
             PreparedStatement createOrderStatement = connection.prepareStatement(createOrderSql, Statement.RETURN_GENERATED_KEYS);
             PreparedStatement insertDetailStatement = connection.prepareStatement(insertOrderDetailSql)) {

            for (OrderItemRequest item : items) {
                lockStockStatement.setInt(1, item.getProductId());
                try (ResultSet stockRs = lockStockStatement.executeQuery()) {
                    if (!stockRs.next() || stockRs.getInt("stock") < item.getQuantity()) {
                        throw new IllegalStateException("Het hang cho product_id = " + item.getProductId());
                    }
                }
            }

            for (OrderItemRequest item : items) {
                updateStockStatement.setInt(1, item.getQuantity());
                updateStockStatement.setInt(2, item.getProductId());
                updateStockStatement.executeUpdate();
            }

            createOrderStatement.setInt(1, userId);
            createOrderStatement.executeUpdate();

            int orderId;
            try (ResultSet keyRs = createOrderStatement.getGeneratedKeys()) {
                if (!keyRs.next()) {
                    throw new SQLException("Khong tao duoc order_id.");
                }
                orderId = keyRs.getInt(1);
            }

            for (OrderItemRequest item : items) {
                insertDetailStatement.setInt(1, orderId);
                insertDetailStatement.setInt(2, item.getProductId());
                insertDetailStatement.setInt(3, item.getQuantity());
                insertDetailStatement.setInt(4, item.getProductId());
                insertDetailStatement.addBatch();
            }
            insertDetailStatement.executeBatch();
        } catch (SQLException e) {
            throw new DataAccessException("Loi SQL trong qua trinh placeOrder.", e);
        }
    }
}

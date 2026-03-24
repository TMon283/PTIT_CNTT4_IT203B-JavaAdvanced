package session14.miniProject.service;

import session14.miniProject.config.DatabaseConnectionManager;
import session14.miniProject.DAO.OrderDAO;
import session14.miniProject.exception.DataAccessException;
import session14.miniProject.model.OrderItemRequest;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class FlashSaleService {
    private final DatabaseConnectionManager connectionManager;
    private final OrderDAO orderDAO;

    public FlashSaleService(DatabaseConnectionManager connectionManager, OrderDAO orderDAO) {
        this.connectionManager = connectionManager;
        this.orderDAO = orderDAO;
    }

    public void placeOrder(int userId, List<OrderItemRequest> items) {
        placeOrderAndReturnStatus(userId, items, true);
    }

    public boolean placeOrderAndReturnStatus(int userId, List<OrderItemRequest> items) {
        return placeOrderAndReturnStatus(userId, items, false);
    }

    private boolean placeOrderAndReturnStatus(int userId, List<OrderItemRequest> items, boolean printLog) {
        Connection con = null;
        try {
            con = connectionManager.getConnection();
            con.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
            con.setAutoCommit(false);

            orderDAO.placeOrder(con, userId, items);
            con.commit();
            if (printLog) {
                System.out.println("Dat hang thanh cong.");
            }
            return true;
        } catch (Exception e) {
            if (con != null) {
                try {
                    con.rollback();
                } catch (SQLException rollbackError) {
                    throw new DataAccessException("Rollback that bai.", rollbackError);
                }
            }
            if (printLog) {
                System.out.println("Dat hang that bai: " + e.getMessage());
            }
            return false;
        } finally {
            if (con != null) {
                try {
                    con.setAutoCommit(true);
                    con.close();
                } catch (SQLException closeError) {
                    throw new DataAccessException("Loi khi dong ket noi.", closeError);
                }
            }
        }
    }
}

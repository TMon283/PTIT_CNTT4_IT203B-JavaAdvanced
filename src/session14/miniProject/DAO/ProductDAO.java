package session14.miniProject.DAO;

import session14.miniProject.config.DatabaseConnectionManager;
import session14.miniProject.exception.DataAccessException;
import session14.miniProject.model.Product;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductDAO {
    private final DatabaseConnectionManager connectionManager;

    public ProductDAO(DatabaseConnectionManager connectionManager) {
        this.connectionManager = connectionManager;
    }

    public void createProduct(String name, String category, BigDecimal price, int stock) {
        String sql = "INSERT INTO Products(product_name, category, price, stock) VALUES(?, ?, ?, ?)";
        try (Connection con = connectionManager.getConnection();
             PreparedStatement preparedStatement = con.prepareStatement(sql)) {
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, category);
            preparedStatement.setBigDecimal(3, price);
            preparedStatement.setInt(4, stock);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DataAccessException("Loi khi tao san pham.", e);
        }
    }

    public List<Product> getAllProducts() {
        String sql = "SELECT product_id, product_name, category, price, stock FROM Products ORDER BY product_id";
        List<Product> products = new ArrayList<>();
        try (Connection con = connectionManager.getConnection();
             PreparedStatement preparedStatement = con.prepareStatement(sql);
             ResultSet rs = preparedStatement.executeQuery()) {
            while (rs.next()) {
                products.add(
                    new Product(
                        rs.getInt("product_id"),
                        rs.getString("product_name"),
                        rs.getString("category"),
                        rs.getBigDecimal("price"),
                        rs.getInt("stock")
                    )
                );
            }
            return products;
        } catch (SQLException e) {
            throw new DataAccessException("Loi khi lay danh sach san pham.", e);
        }
    }

    public void updateProduct(int productId, String name, String category, BigDecimal price, int stock) {
        String sql = "UPDATE Products SET product_name = ?, category = ?, price = ?, stock = ? WHERE product_id = ?";
        try (Connection con = connectionManager.getConnection();
             PreparedStatement preparedStatement = con.prepareStatement(sql)) {
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, category);
            preparedStatement.setBigDecimal(3, price);
            preparedStatement.setInt(4, stock);
            preparedStatement.setInt(5, productId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DataAccessException("Loi khi cap nhat san pham.", e);
        }
    }

    public void deleteProduct(int productId) {
        String sql = "DELETE FROM Products WHERE product_id = ?";
        try (Connection con = connectionManager.getConnection();
             PreparedStatement preparedStatement = con.prepareStatement(sql)) {
            preparedStatement.setInt(1, productId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DataAccessException("Loi khi xoa san pham.", e);
        }
    }
}

package session14.miniProject.DAO;

import session14.miniProject.config.DatabaseConnectionManager;
import session14.miniProject.exception.DataAccessException;
import session14.miniProject.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {
    private final DatabaseConnectionManager connectionManager;

    public UserDAO(DatabaseConnectionManager connectionManager) {
        this.connectionManager = connectionManager;
    }

    public void insertUser(String username) {
        String sql = "INSERT INTO Users(username) VALUES(?)";
        try (Connection con = connectionManager.getConnection();
             PreparedStatement preparedStatement = con.prepareStatement(sql)) {
            preparedStatement.setString(1, username);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DataAccessException("Loi khi them user.", e);
        }
    }

    public List<User> findAllUsers() {
        String sql = "SELECT user_id, username FROM Users ORDER BY user_id";
        List<User> users = new ArrayList<>();
        try (Connection con = connectionManager.getConnection();
             PreparedStatement preparedStatement = con.prepareStatement(sql);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                users.add(new User(resultSet.getInt("user_id"), resultSet.getString("username")));
            }
            return users;
        } catch (SQLException e) {
            throw new DataAccessException("Loi khi lay danh sach user.", e);
        }
    }
}

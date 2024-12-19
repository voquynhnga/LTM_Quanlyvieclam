package Model.DAO;

import Model.BEAN.User;
import Util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {
    private Connection connection;

    public UserDAO() {
        try {
            connection = DBConnection.getConnection();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public List<User> getAllUsers() {
        List<User> userList = new ArrayList<>();
        String query = "SELECT u.userId, u.username, u.password, u.email, u.phoneNumber, u.firstname, u.lastname, u.createdAt " +
                       "FROM user u " +
                       "JOIN user_role ur ON u.userId = ur.userId " +
                       "WHERE ur.roleId <> 1"; 

        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                int userId = rs.getInt("userId");
                String username = rs.getString("username");
                String password = rs.getString("password");
                String email = rs.getString("email");
                String phoneNumber = rs.getString("phoneNumber");
                String firstname = rs.getString("firstname");
                String lastname = rs.getString("lastname");
                Timestamp createdAt = rs.getTimestamp("createdAt"); 
                
                userList.add(new User(userId, username, password, email, phoneNumber, firstname, lastname, createdAt));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return userList;
    }


    public void deleteUser(int userId) {
        String deleteJobsQuery = "DELETE FROM job WHERE clientId = ?"; 
        
        String deleteUserQuery = "DELETE FROM user WHERE userId = ?"; 

        try (
            PreparedStatement deleteJobsStmt = connection.prepareStatement(deleteJobsQuery);
            PreparedStatement deleteUserStmt = connection.prepareStatement(deleteUserQuery)
        ) {
            deleteJobsStmt.setInt(1, userId);
            deleteJobsStmt.executeUpdate();

            deleteUserStmt.setInt(1, userId);
            deleteUserStmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public User getUserById(int userId) {
        User user = null;
        String query = "SELECT * FROM user WHERE userId = ?"; // Chỉnh lại tên bảng nếu cần

        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, userId);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                user = new User(
                    rs.getInt("userId"),
                    rs.getString("username"),
                    rs.getString("password"),
                    rs.getString("email"),
                    rs.getString("phoneNumber"),
                    rs.getString("firstname"),
                    rs.getString("lastname"),
                    rs.getTimestamp("createdAt")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user; 
    }




    public boolean updateUser(User user) throws SQLException {
        String sql = "UPDATE users SET firstname = ?, lastname = ?, email = ?, phoneNumber = ?, password = ? WHERE userId = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, user.getFirstname());
            stmt.setString(2, user.getLastname());
            stmt.setString(3, user.getEmail());
            stmt.setString(4, user.getPhoneNumber());
            stmt.setString(5, user.getPassword());
            stmt.setInt(6, user.getUserId());

            int rowsUpdated = stmt.executeUpdate();
            return rowsUpdated > 0; 
        }
    }
}

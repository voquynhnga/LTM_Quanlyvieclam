package model.dao;
import java.sql.*;
import java.util.ArrayList;

import model.bean.User;
import utils.DBConnect;
import utils.RoleEnum;

public class UserDAO {
    public User getUserById(int userId) {
        try {
            String url = "jdbc:mySQL://localhost:3306/ltm_worksearch";
            Connection conn = DBConnect.connectDB(url);

            String query = "SELECT * FROM user WHERE userId = ?";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, userId);
            ResultSet rs = pstmt.executeQuery();

            while(rs.next()){
                User user = new User(
                        rs.getInt("userId"),
                        rs.getString("email"),
                        rs.getString("username"),
                        rs.getString("password"),
                        rs.getString("phoneNumber")
                );
                return user;
            }
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public User getUserByEmail(String email) {
        try {
            String url = "jdbc:mySQL://localhost:3306/ltm_worksearch";
            Connection conn = DBConnect.connectDB(url);

            String query = "SELECT * FROM user WHERE email = ?";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, email);
            ResultSet rs = pstmt.executeQuery();

            while(rs.next()){
                User user = new User(
                        rs.getInt("userId"),
                        rs.getString("email"),
                        rs.getString("username"),
                        rs.getString("password"),
                        rs.getString("phoneNumber")
                );
                return user;
            }
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public ArrayList<User> getAllUsers() {
        try {
            String url = "jdbc:mySQL://localhost:3306/ltm_worksearch";
            Connection conn = DBConnect.connectDB(url);

            String query = "SELECT * FROM user";
            PreparedStatement pstmt = conn.prepareStatement(query);
            ResultSet rs = pstmt.executeQuery();

            ArrayList<User> listUser = new ArrayList<>();
            while(rs.next()){
                User user = new User(
                        rs.getInt("userId"),
                        rs.getString("email"),
                        rs.getString("username"),
                        rs.getString("password"),
                        rs.getString("phoneNumber")
                );
                listUser.add(user);
            }
            return listUser;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public ArrayList<User> getAllUsersWithRoleClient() {
        try {
            String url = "jdbc:mySQL://localhost:3306/ltm_worksearch";
            Connection conn = DBConnect.connectDB(url);

            String query = """
                            SELECT u.* 
                            FROM user u
                            JOIN user_role ur ON u.userId = ur.userId
                            JOIN role r ON ur.roleId = r.roleId
                            WHERE r.roleId = ?""";
            RbacDAO rbacDAO = new RbacDAO();
            int roleId = rbacDAO.getRoleIdByRoleName(RoleEnum.CLIENT.getRoleName());
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, roleId);
            ResultSet rs = pstmt.executeQuery();

            ArrayList<User> listUser = new ArrayList<>();
            while(rs.next()){
                User user = new User(
                        rs.getInt("userId"),
                        rs.getString("email"),
                        rs.getString("username"),
                        rs.getString("password"),
                        rs.getString("phoneNumber")
                );
                listUser.add(user);
            }
            return listUser;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean createUser(User newUser) {
        try {
            String url = "jdbc:mySQL://localhost:3306/ltm_worksearch";
            Connection conn = DBConnect.connectDB(url);

            //add user
            String queryUser = "INSERT INTO user (email, password, username, phoneNumber) VALUES (?, ?, ?, ?)";
            PreparedStatement pstmtUser = conn.prepareStatement(queryUser, Statement.RETURN_GENERATED_KEYS);
            pstmtUser.setString(1, newUser.getEmail());
            pstmtUser.setString(2, newUser.getPassword());
            pstmtUser.setString(3, newUser.getUsername());
            pstmtUser.setString(4, newUser.getPhoneNumber());
            int userAffectedRows = pstmtUser.executeUpdate();
            if (userAffectedRows == 0) {
                throw new SQLException("Creating user failed, no rows affected");
            }

            //get userId
            try (ResultSet rs = pstmtUser.getGeneratedKeys()) {
                if (rs.next()) {
                    int userId = rs.getInt(1);
                    newUser.setUserId(userId);
                } else {
                    throw new SQLException("Creating user failed, no userId created");
                }
            }

            //get roleId
            RbacDAO rbacDAO = new RbacDAO();
            int roleId = rbacDAO.getRoleIdByRoleName(RoleEnum.CLIENT.getRoleName());

            //add role
            String queryRole = "INSERT INTO user_role (userId, roleId) VALUES (?, ?)";
            PreparedStatement pstmtRole = conn.prepareStatement(queryRole);
            pstmtRole.setInt(1, newUser.getUserId());
            pstmtRole.setInt(2, roleId);
            int roleAffectedRows = pstmtRole.executeUpdate();
            if (roleAffectedRows == 0) {
                throw new SQLException("Assign role to user failed, no rows affected");
            }

            return true;
        }catch(Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean updateUser(User user) {
        try {
            String url = "jdbc:mySQL://localhost:3306/ltm_worksearch";
            Connection conn = DBConnect.connectDB(url);

            String query = "UPDATE user SET username = ?, phoneNumber = ? WHERE userId = ?";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, user.getUsername());
            pstmt.setString(2, user.getPhoneNumber());
            pstmt.setInt(3, user.getUserId());
            int affectedRows = pstmt.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Updating user failed, no rows affected");
            }

            return true;
        }catch(Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteUser(int userId) {
        try {
            String url = "jdbc:mySQL://localhost:3306/ltm_worksearch";
            Connection conn = DBConnect.connectDB(url);

            String query = "DELETE FROM user WHERE userId = ?";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, userId);
            int affectedRows = pstmt.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Deleting user failed, no rows affected");
            }
            return true;
        }catch(Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}

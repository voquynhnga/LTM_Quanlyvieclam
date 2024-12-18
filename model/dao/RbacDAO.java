package model.dao;

import model.bean.User;
import utils.DBConnect;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class RbacDAO {
    public int getRoleIdByRoleName(String roleName) {
        try {
            String url = "jdbc:mySQL://localhost:3306/ltm_worksearch";
            Connection conn = DBConnect.connectDB(url);

            String query = "SELECT * FROM role WHERE RoleName = ?";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, roleName);
            ResultSet rs = pstmt.executeQuery();

            while(rs.next()){
                return rs.getInt("roleId");
            }
            return -1;
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    public ArrayList<String> getPermissionsByUserId(int userId) {
        try {
            String url = "jdbc:mySQL://localhost:3306/ltm_worksearch";
            Connection conn = DBConnect.connectDB(url);

            String query = """
                            SELECT * 
                            FROM user u
                            JOIN user_role ur ON u.userId = ur.userId
                            JOIN role r ON ur.roleId = r.roleId
                            JOIN role_permission rp ON r.roleId = rp.roleId
                            JOIN permission p ON rp.permissionId = p.permissionId
                            WHERE u.userId = ?
                            """;
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, userId);
            ResultSet rs = pstmt.executeQuery();

            ArrayList<String> listUserPermissions = new ArrayList<>();
            while(rs.next()){
                listUserPermissions.add(rs.getString("permissionName"));
            }
            return listUserPermissions;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}

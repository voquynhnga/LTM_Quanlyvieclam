package model.bo.user;

import java.util.List;
import model.bean.User;
import model.dao.UserDAO;

public class UserBO {
    private UserDAO userDAO = new UserDAO();

    // Lấy danh sách toàn bộ người dùng (chỉ admin cần dùng).
    public List<User> getAllUsers() {
        return userDAO.getAllUsers();
    }

    // Lấy thông tin người dùng qua ID (Client cần để xem thông tin cá nhân).
    public User getUserById(int userId) {
        return userDAO.getUserById(userId);
    }

    // Admin xóa người dùng.
    public boolean deleteUser(int userId) {
        return userDAO.deleteUser(userId);
    }
}

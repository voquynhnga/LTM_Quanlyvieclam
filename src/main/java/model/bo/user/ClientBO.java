package model.bo.user;

import model.bean.User;
import model.dao.UserDAO;

public class ClientBO extends UserBO {
    private UserDAO userDAO = new UserDAO();

    public boolean updateUserProfile(int userId, User updatedUser) {
        return userDAO.updateUser(userId, updatedUser);
    }
}

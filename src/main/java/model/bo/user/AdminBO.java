package model.bo.user;

import model.dao.UserDAO;

public class AdminBO extends UserBO {
    private UserDAO userDAO = new UserDAO();

    @Override
    public boolean deleteUser(int userId) {
        return userDAO.deleteUser(userId);
    }
}

package model.bo.role;

import model.dao.RoleDAO;

public class PermissionBO {
    private RoleDAO roleDAO = new RoleDAO();

    public boolean hasPermission(int userId, String permission) {
        return roleDAO.checkPermission(userId, permission);
    }
}

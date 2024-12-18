package model.bo.role;

import java.util.List;
import model.bean.Role;
import model.dao.RoleDAO;

public class RoleBO {
    private RoleDAO roleDAO = new RoleDAO();

    public List<String> getPermissionsByRole(int roleId) {
        return roleDAO.getPermissionsByRole(roleId);
    }

    public boolean addRole(Role role) {
        return roleDAO.insertRole(role);
    }

    public boolean deleteRole(int roleId) {
        return roleDAO.deleteRole(roleId);
    }
}

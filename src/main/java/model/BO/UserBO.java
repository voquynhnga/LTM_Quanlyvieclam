package Model.BO;
import Model.BEAN.User;
import Model.DAO.UserDAO;

import java.sql.SQLException;
import java.util.List;

public class UserBO {

    private UserDAO userDAO;

    public UserBO() {
        userDAO = new UserDAO();  
    }

    public List<User> getAllUsers() {
        return userDAO.getAllUsers(); 
    }
    
    public void deleteUser(int userId) {
        userDAO.deleteUser(userId); 
    }
    public User getUserById(int userId) {
        return userDAO.getUserById(userId); 
    }

	public boolean updateUser(User user) throws SQLException {
		return userDAO.updateUser(user);
	}
}

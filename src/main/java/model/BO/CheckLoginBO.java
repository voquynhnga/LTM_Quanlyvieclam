package Model.BO;

import java.sql.SQLException;
import java.util.ArrayList;

import Model.BEAN.Job;
import Model.DAO.CheckLoginDAO;

public class CheckLoginBO {
	
	CheckLoginDAO dao = new CheckLoginDAO();
	

	public boolean isValidUser(String username, String password) throws ClassNotFoundException, SQLException {
		return dao.isExitUser(username, password);
	}


	public ArrayList<Job> getAllJobList() {
		return dao.getAllJobList();
	}
	
	public int getRole(String username) throws ClassNotFoundException, SQLException {
		return dao.getRoleIdByUserId(username);
	}


	public int getUserId(String username) throws ClassNotFoundException, SQLException {
		return dao.getUserIdByUsername(username);
	}




}

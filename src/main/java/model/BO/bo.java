package Model.BO;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Model.BEAN.Job;
import Model.DAO.dao;

public class bo {
	dao dao = new dao();
	public boolean addNew(Job Job) throws ClassNotFoundException, SQLException {
		return dao.addNew(Job);
		
	}
	 public boolean isExist(String title, String description, String company) throws SQLException, ClassNotFoundException {
		 return dao.isExist(title, description, company);
	 }
	 public ArrayList<Job> getAll(String field, String value) throws SQLException, ClassNotFoundException {
		 return dao.getAll(field,value);
	 }
	 public Job getByID(int id) throws SQLException, ClassNotFoundException {
		 return dao.getByID(id);
	 }
	 public boolean update(int jobId, String title, String company, String salary, String description, String location, Date deadline) throws SQLException, ClassNotFoundException {
		 return dao.update(jobId, title,company,  salary, description,  location,deadline);
	 }
	 

	public boolean delete(int JobID) throws ClassNotFoundException, SQLException {
		return dao.delete(JobID);
	}
	public int getMaxId() throws ClassNotFoundException, SQLException {
		
		return dao.getMaxId();
	}
}

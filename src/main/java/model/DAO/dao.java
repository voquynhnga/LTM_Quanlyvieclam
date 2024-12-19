package Model.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.Date;

import Util.DBConnection;
import Model.BEAN.Job;

public class dao {

	public boolean addNew(Job job) throws ClassNotFoundException, SQLException {
	    String insertSql = "INSERT INTO job (jobId, title, description, company, location, salary, deadline, postedAt, clientId) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

	    try (Connection conn = DBConnection.getConnection();
	         PreparedStatement insertStmt = conn.prepareStatement(insertSql)) {

	        int jobId = getMaxId();
	        
	        System.out.println("Job ID: " + jobId);
	        System.out.println("Title: " + job.getTitle());
	        System.out.println("Description: " + job.getDescription());
	        System.out.println("Company Name: " + job.getCompanyName());
	        System.out.println("Location: " + job.getLocation());
	        System.out.println("Salary: " + job.getSalary());
	        System.out.println("Deadline: " + job.getDeadline());
	        System.out.println("Posted At: " + job.getPostedAt());

	        insertStmt.setInt(1, jobId);
	        insertStmt.setString(2, job.getTitle());
	        insertStmt.setString(3, job.getDescription());
	        insertStmt.setString(4, job.getCompanyName());
	        insertStmt.setString(5, job.getLocation());
	        insertStmt.setString(6, job.getSalary());
	        insertStmt.setDate(7, new java.sql.Date(job.getDeadline().getTime()));
	        insertStmt.setDate(8, new java.sql.Date(job.getPostedAt().getTime()));
	        insertStmt.setInt(9, job.getClientId()); 

	        int rowsAffected = insertStmt.executeUpdate();
	        return rowsAffected > 0;
	    }
	}

	
	public int getMaxId() throws SQLException, ClassNotFoundException {
	    String getMaxIdSql = "SELECT COALESCE(MAX(jobId), 0) + 1 AS nextId FROM job";
	    try (Connection conn = DBConnection.getConnection();
	         PreparedStatement getMaxIdStmt = conn.prepareStatement(getMaxIdSql);
	         ResultSet rs = getMaxIdStmt.executeQuery()) {
	        
	        if (rs.next()) {
	            return rs.getInt("nextId");
	        }
	    }
	    return 1; 
	}



    public boolean isExist(String jobTitle, String description, String company) throws SQLException, ClassNotFoundException {
        String sql = "SELECT COUNT(*) FROM job WHERE title = ? and description = ? and company = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, jobTitle);
            stmt.setString(2,  description);
            stmt.setString(3, company);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
            return false;
        }
    }

    public ArrayList<Job> getAll(String field, String value) throws SQLException, ClassNotFoundException {
        ArrayList<Job> resultList = new ArrayList<>();
        String sql;

        if (field == null || field.isEmpty() || value == null || value.isEmpty()) {
            sql = "SELECT * FROM job";
        } else {
            sql = "SELECT * FROM job WHERE " + field + " LIKE ?";
        }

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            if (field != null && !field.isEmpty() && value != null && !value.isEmpty()) {
                stmt.setString(1, "%" + value.trim() + "%");
            }

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Job job = new Job(
                	rs.getInt("jobId"),
                    rs.getString("title"),
                    rs.getString("description"),
                    rs.getString("company"),
                    rs.getString("location"),
                    rs.getString("salary"),
                    rs.getDate("deadline"),
                    rs.getDate("postedAt"),
                    rs.getInt("clientId")
                );

                resultList.add(job);
            }
            System.out.println(resultList);
        }

        return resultList;
    }


    public Job getByID(int id) throws SQLException, ClassNotFoundException {
        String sql = "SELECT * FROM job WHERE jobId = ?";
        Job job = null;

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
            	  job = new Job(
                      	rs.getInt("jobId"),
                          rs.getString("title"),
                          rs.getString("description"),
                          rs.getString("company"),
                          rs.getString("location"),
                          rs.getString("salary"),
                          rs.getDate("deadline"),
                          rs.getDate("postedAt"),
                          rs.getInt("clientId")
                      );
                
            }
        }

        return job;
    }

    public boolean update(int jobId, String title, String company, String salary, String description, String location, Date deadline) throws SQLException, ClassNotFoundException {
        String updateSql = "UPDATE job SET title = ?, company = ?, salary = ?, description = ?, location = ?, deadline = ? WHERE jobId = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(updateSql)) {

            stmt.setString(1, title);
            stmt.setString(2, company);
            stmt.setString(3, salary);
            stmt.setString(4, description);
            stmt.setString(5, location);
            stmt.setDate(6, deadline);
            stmt.setInt(7, jobId);

            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        }
    }

    public boolean delete(int id) throws SQLException, ClassNotFoundException {
        String sql = "DELETE FROM job WHERE jobId = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        }
    }
}

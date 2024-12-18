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
	    String getMaxIdSql = "SELECT COALESCE(MAX(jobId), 0) + 1 AS nextId FROM job";
	    String insertSql = "INSERT INTO job (jobId, title, description, company, location, salary, deadline, postedAt, clientId) VALUES (?, ?, ?, ?, ?, ?, ?, ?,?)";

	    try (Connection conn = DBConnection.getConnection();
	         PreparedStatement getMaxIdStmt = conn.prepareStatement(getMaxIdSql);
	         PreparedStatement insertStmt = conn.prepareStatement(insertSql)) {

	        ResultSet rs = getMaxIdStmt.executeQuery();
	        int jobId = 1; 
	        if (rs.next()) {
	            jobId = rs.getInt("nextId");
	        }

	        insertStmt.setInt(1, jobId);
	        insertStmt.setString(2, job.getTitle());
	        insertStmt.setString(3, job.getDescription());
	        insertStmt.setString(4, job.getCompanyName());
	        insertStmt.setString(5, job.getLocation());
	        insertStmt.setString(6, job.getSalary());
	        insertStmt.setDate(7, job.getDeadline()); 
	        insertStmt.setDate(8, job.getPostedAt()); 
	        insertStmt.setString(9, getMaxIdSql);

	        int rowsAffected = insertStmt.executeUpdate();
	        return rowsAffected > 0;
	    }
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
                Job job = new Job();
                job.setTitle(rs.getString("title"));
                job.setCompanyName(rs.getString("company"));
                job.setSalary(rs.getString("salary"));
                job.setDescription(rs.getString("description"));
                job.setLocation(rs.getString("location"));
                job.setDeadline(rs.getDate("deadline"));
                job.setPostedAt(rs.getDate("postedAt"));
                job.setClientId(1);
                

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
                job = new Job();
                job.setJobId(rs.getInt("jobId"));
                 job.setTitle(rs.getString("title"));
                 job.setDescription(rs.getString("description"));
                 job.setCompanyName(rs.getString("company"));
                 job.setLocation(rs.getString("location"));
                 job.setSalary(rs.getString("salary"));
                 job.setDeadline(rs.getDate("deadline"));
                 job.setPostedAt(rs.getDate("postedAt"));
                    
                
            }
        }

        return job;
    }

    public boolean update(Job job) throws SQLException, ClassNotFoundException {
        String sql = "UPDATE job SET title = ?, description = ?, company = ?, location = ?, salary = ?, deadline = ?, postedAt = ? WHERE jobId = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, job.getTitle());
            stmt.setString(4, job.getDescription());

            stmt.setString(2, job.getCompanyName());
            stmt.setString(5, job.getLocation());

            stmt.setString(3, job.getSalary());
            stmt.setDate(6, (job.getDeadline()));
            stmt.setDate(7, job.getPostedAt());
            stmt.setInt(8, job.getJobId());

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

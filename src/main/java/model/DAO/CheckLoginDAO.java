package Model.DAO;

import java.sql.*;
import java.util.ArrayList;

import Model.BEAN.Job;
import Util.DBConnection;


public class CheckLoginDAO {
	public boolean isExitUser(String username, String password) throws ClassNotFoundException, SQLException {
	    String query = null;
	    try (Connection conn = DBConnection.getConnection()) {
	        PreparedStatement pstmt = null;

	        if (username != null && username.contains("@gmail.com")) {
	            query = "SELECT * FROM user WHERE email = ? AND password = ?";
	        } else {
	            query = "SELECT * FROM user WHERE username = ? AND password = ?";
	        }

	        pstmt = conn.prepareStatement(query);
	        pstmt.setString(1, username);
	        pstmt.setString(2, password);

	        ResultSet rs = pstmt.executeQuery();
	        return rs.next(); 
	    } catch (Exception e) {
	        e.printStackTrace();
	        return false; 
	    }
	}
	
	public int getUserIdByUsername(String username) throws ClassNotFoundException, SQLException {
	    String query;
	    
	    if (username.contains("@gmail.com")) {
	        query = "SELECT userId FROM user WHERE email = ?";
	    } else {
	        query = "SELECT userId FROM user WHERE username = ?";
	    }
	    
	    try (Connection conn = DBConnection.getConnection();
	         PreparedStatement pstmt = conn.prepareStatement(query)) {
	        
	        pstmt.setString(1, username);
	        ResultSet rs = pstmt.executeQuery();
	        
	        if (rs.next()) {
	            return rs.getInt("userId");
	        } else {
	            return -1; 
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	        return -1; 
	    }
	}

	
	public int getRoleIdByUserId(String username) throws ClassNotFoundException, SQLException {
	    String query1;  
	    if(username.contains("@gmail.com")) {
	        query1 = "SELECT userId FROM user WHERE email = ?";
	    } else {
	        query1 = "SELECT userId FROM user WHERE username = ?";
	    }

	    String query2 = "SELECT roleId FROM user_role WHERE userId = ?";
	    int userId = -1;

	    try (Connection conn = DBConnection.getConnection();
	         PreparedStatement pstmt1 = conn.prepareStatement(query1)) {

	        pstmt1.setString(1, username);
	        ResultSet rs1 = pstmt1.executeQuery();
	        
	        if (rs1.next()) {
	            userId = rs1.getInt("userId");
	            System.out.println("userId " + userId);
	        } else {
	            return -1;
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	        return -1; 
	    }

	    if (userId == -1) {
	        return -1;
	    }

	    try (Connection conn = DBConnection.getConnection();
	         PreparedStatement pstmt2 = conn.prepareStatement(query2)) {

	        pstmt2.setInt(1, userId);
	        ResultSet rs2 = pstmt2.executeQuery();
	        
	        if (rs2.next()) {
	            return rs2.getInt("roleId");
	        } else {
	            return -1; 
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	        return -1; 
	    }
	}




    public ArrayList<Job> getAllJobList() {
        ArrayList<Job> result = new ArrayList<Job>();
        
    	try (Connection conn = DBConnection.getConnection()) {
            String query = "SELECT * FROM job";
            PreparedStatement pstmt = conn.prepareStatement(query);
            ResultSet rs = pstmt.executeQuery();
            while(rs.next()) {
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
            	result.add(job);
            }
            System.out.println("DAO" + result);
            
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }


}
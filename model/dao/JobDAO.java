package model.dao;

import model.bean.Job;
import model.bean.User;
import utils.DBConnect;
import utils.RoleEnum;

import java.sql.*;
import java.util.ArrayList;

public class JobDAO {
    public Job getJobById(int jobId) {
        try {
            String url = "jdbc:mySQL://localhost:3306/ltm_worksearch";
            Connection conn = DBConnect.connectDB(url);

            String query = "SELECT * FROM job WHERE jobId = ?";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, jobId);
            ResultSet rs = pstmt.executeQuery();

            while(rs.next()){
                Job job = new Job(
                        rs.getInt("jobId"),
                        rs.getString("title"),
                        rs.getString("description"),
                        rs.getString("company"),
                        rs.getString("location"),
                        rs.getString("salary"),
                        rs.getDate("deadline"),
                        rs.getInt("clientId")
                );
                return job;
            }
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public ArrayList<Job> getAllJobs() {
        try {
            String url = "jdbc:mySQL://localhost:3306/ltm_worksearch";
            Connection conn = DBConnect.connectDB(url);

            String query = "SELECT * FROM job";
            PreparedStatement pstmt = conn.prepareStatement(query);
            ResultSet rs = pstmt.executeQuery();

            ArrayList<Job> listJob = new ArrayList<>();
            while(rs.next()){
                Job job = new Job(
                        rs.getInt("jobId"),
                        rs.getString("title"),
                        rs.getString("description"),
                        rs.getString("company"),
                        rs.getString("location"),
                        rs.getString("salary"),
                        rs.getDate("deadline"),
                        rs.getInt("clientId")
                );
                listJob.add(job);
            }
            return listJob;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean createJob(Job newJob) {
        try {
            String url = "jdbc:mySQL://localhost:3306/ltm_worksearch";
            Connection conn = DBConnect.connectDB(url);

            String queryUser = "INSERT INTO job (title, description, company, location, salary, deadline, clientId) VALUES (?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement pstmtUser = conn.prepareStatement(queryUser);
            pstmtUser.setString(1, newJob.getTitle());
            pstmtUser.setString(2, newJob.getDescription());
            pstmtUser.setString(3, newJob.getCompany());
            pstmtUser.setString(4, newJob.getLocation());
            pstmtUser.setString(5, newJob.getSalary());
            pstmtUser.setDate(6, newJob.getDeadline());
            pstmtUser.setInt(7, newJob.getClientId());
            int userAffectedRows = pstmtUser.executeUpdate();
            if (userAffectedRows == 0) {
                throw new SQLException("Creating job failed, no rows affected");
            }

            return true;
        }catch(Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean updateJob(Job job) {
        try {
            String url = "jdbc:mySQL://localhost:3306/ltm_worksearch";
            Connection conn = DBConnect.connectDB(url);

            String query = "UPDATE job SET title = ?, description = ?, company = ?, location = ?, salary = ?, deadline = ? WHERE jobId = ?";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, job.getTitle());
            pstmt.setString(2, job.getDescription());
            pstmt.setString(3, job.getCompany());
            pstmt.setString(4, job.getLocation());
            pstmt.setString(5, job.getSalary());
            pstmt.setDate(6, job.getDeadline());
            pstmt.setInt(7, job.getJobId());
            int affectedRows = pstmt.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Updating job failed, no rows affected");
            }

            return true;
        }catch(Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteJob(int jobId) {
        try {
            String url = "jdbc:mySQL://localhost:3306/ltm_worksearch";
            Connection conn = DBConnect.connectDB(url);

            String query = "DELETE FROM job WHERE jobId = ?";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, jobId);
            int affectedRows = pstmt.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Deleting job failed, no rows affected");
            }
            return true;
        }catch(Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static void main(String[] args){
        JobDAO jobDAO = new JobDAO();
        Job job = new Job();
        job.setTitle("Software Engineer");
        job.setDescription("Develop and maintain software applications.");
        job.setCompany("Tech Corp");
        job.setLocation("Lien Chieu, Da Nang");
        job.setSalary("Thoa thuan");
        job.setDeadline(Date.valueOf("2024-12-18"));
        job.setClientId(5);
        job.setJobId(6);
        boolean check = jobDAO.deleteJob(6);
        System.out.println(check);
    }
}

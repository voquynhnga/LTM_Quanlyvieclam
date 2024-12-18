package Model.BEAN;

import java.sql.Date;
import java.sql.PreparedStatement;

public class Job {
    private int jobId;
    private String title;
    private String description;
    private String companyName;
    private String location;
    private String salary;
    private Date deadline;
    private Date postedAt;
    private int clientId;

 

    public Job(int jobId, String title, String description, String companyName, String location, String salary, Date deadline, Date postedAt, int clientId) {
        this.jobId = jobId;
        this.title = title;
        this.description = description;
        this.companyName = companyName;
        this.location = location;
        this.salary = salary;
        this.deadline = deadline;
        this.postedAt = postedAt;
        this.clientId = clientId;
    }

    // Getters and setters
    public int getJobId() {
        return jobId;
    }

    public void setJobId(int jobId) {
        this.jobId = jobId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getSalary() {
        return salary;
    }

    public void setSalary(String salary) {
        this.salary = salary;
    }

    public Date getDeadline() {
        return deadline;
    }

    public void setDeadline(Date date) {
        this.deadline = date;
    }
    
    public Date getPostedAt() {
        return postedAt;
    }

    public void setPostedAt(Date date) {
        this.postedAt = date;
    }

    public int getClientId() {
        return clientId;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }
}
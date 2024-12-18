package model.bo.job;

import java.util.List;
import model.bean.Job;
import model.dao.JobDAO;

public class JobBO {
    private JobDAO jobDAO = new JobDAO();

    public boolean addJob(Job job) {
        return jobDAO.insertJob(job);
    }

    public boolean updateJob(int jobId, Job updatedJob) {
        return jobDAO.updateJob(jobId, updatedJob);
    }

    public boolean deleteJob(int jobId) {
        return jobDAO.deleteJob(jobId);
    }

    public List<Job> searchJobs(String keyword) {
        return jobDAO.searchJobs(keyword);
    }
}

package model.bo.job;

import model.bean.Job;

public class JobClientBO extends JobBO {
    public boolean uploadJob(Job job) {
        return addJob(job);
    }
}

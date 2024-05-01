package org.revhire.service;

import org.revhire.dao.JobDAO;
import org.revhire.model.Job;

import java.sql.SQLException;
import java.util.List;

public class JobService {
    private JobDAO jobDAO = new JobDAO();


    public void postJob(Job job) throws SQLException {
        jobDAO.postJob(job);
    }


    public List<Job> searchJobs(String title, String location, int experience) throws SQLException {
        return jobDAO.searchJobs(title, location, experience);
    }
    public List<Job> viewAllJobsByEmployerById(int empId){
        return jobDAO.viewAllJobsByEmployerById(empId);

    }
}


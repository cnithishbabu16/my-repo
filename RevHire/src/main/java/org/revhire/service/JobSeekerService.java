package org.revhire.service;

import org.revhire.dao.JobSeekerDAO;
import org.revhire.model.Experience;
import org.revhire.model.Job;
import org.revhire.model.JobSeeker;

import java.sql.SQLException;
import java.util.List;

public class JobSeekerService {
    private JobSeekerDAO jobSeekerDAO = new JobSeekerDAO();

    public void registerJobSeeker(JobSeeker jobSeeker) throws SQLException {
        jobSeekerDAO.registerJobSeeker(jobSeeker);
    }

    public void addResume(int jobSeekerId, String resume) throws SQLException {
        jobSeekerDAO.addResume(jobSeekerId, resume);
    }
    public List<Job> applyJobs(int jobId) throws  SQLException{
        return jobSeekerDAO.applyJobs(jobId);

    }

    public JobSeeker getJobSeekerByUsername(String username) throws SQLException {
        return jobSeekerDAO.getJobSeekerByUsername(username);
    }

    public void addExperience(Experience experience){
        try {
            jobSeekerDAO.addExperience(experience);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public List<Job>  allJobs(){
        return null;

    }
}


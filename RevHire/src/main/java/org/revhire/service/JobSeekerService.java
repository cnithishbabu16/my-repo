package org.revhire.service;

import org.revhire.dao.JobSeekerDAO;
import org.revhire.model.*;

import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

public class JobSeekerService {
    private JobSeekerDAO jobSeekerDAO = new JobSeekerDAO();

    public void registerJobSeeker(JobSeeker jobSeeker) throws SQLException {
        jobSeekerDAO.registerJobSeeker(jobSeeker);
    }

    public void addResume(int jobSeekerId, String resume) throws SQLException {
        jobSeekerDAO.addResume(jobSeekerId, resume);
    }
    public void applyJob(JobAppication jobAppication){
        jobSeekerDAO.jobApply(jobAppication);
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
        return  jobSeekerDAO.allJobs();
    }
    public List<Job>  searchByExperience(int exp){
        List<Job> jobs=jobSeekerDAO.allJobs();
        List<Job> collect = jobs.stream().filter((n) -> n.getExperience() == exp).collect(Collectors.toList());
        return collect;
    }
    public List<JobSeekerViewApplication>   applicationsByJobSeekerId(int jobSeekerId){
        return jobSeekerDAO.applicationsByJobSeekerId(jobSeekerId);
    }
    public void withdrawApplication(JobAppication jobAppication){

        jobSeekerDAO.withdrawApllication(jobAppication);
    }
    public void forgotPassword(String username,String email,String password){
        jobSeekerDAO.forgotPassword(username,email,password);
    }
}


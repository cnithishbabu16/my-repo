package org.revhire.service;

import org.revhire.dao.EmployerDAO;
import org.revhire.dao.JobDAO;
import org.revhire.model.Employer;
import org.revhire.model.Job;

import java.sql.SQLException;
import java.util.List;

public class EmployerService {
    private EmployerDAO employerDAO = new EmployerDAO();
    private JobDAO jobDao=new JobDAO();


    public void registerEmployer(Employer employer) throws SQLException {
        employerDAO.registerEmployer(employer);
    }


    public Employer getEmployerByUsername(String username) throws SQLException {
        return employerDAO.getEmployerByUsername(username);
    }
   public void postJob(Job job){
        employerDAO.postJob(job);
   }
   public void deletePost(int employerId,int jobId){
        employerDAO.deletePost(employerId,jobId);

   }
    public void updateStatus(int employerId,int applicationId,String applicationStatus){
        employerDAO.updateStatus(employerId,applicationId,applicationStatus);
    }

}


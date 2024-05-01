package org.revhire.service;

import org.revhire.dao.EmployerDAO;
import org.revhire.model.Employer;
import org.revhire.model.Job;

import java.sql.SQLException;

public class EmployerService {
    private EmployerDAO employerDAO = new EmployerDAO();


    public void registerEmployer(Employer employer) throws SQLException {
        employerDAO.registerEmployer(employer);
    }


    public Employer getEmployerByUsername(String username) throws SQLException {
        return employerDAO.getEmployerByUsername(username);
    }
   public void postJob(Job job){
        employerDAO.postJob(job);
   }
   public void deletePost(int jobId){
        employerDAO.deletePost(jobId);

   }

}


package org.revhire.controller;

import org.revhire.dao.JobSeekerDAO;
import org.revhire.model.Experience;
import org.revhire.model.*;
import org.revhire.model.JobSeeker;
import org.revhire.service.JobSeekerService;
import org.revhire.service.JobService;
import org.revhire.util.CredentialsValidator;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class JobSeekerController {

    private JobSeekerService jobSeekerService = new JobSeekerService();
    private JobService jobService = new JobService();
    private Scanner scanner = new Scanner(System.in);




    public void showMenu(int loggedInJobSeekerId) throws SQLException {
        System.out.println("1.create profile");
        System.out.println("2.Create and manage standard textual resume.");
        System.out.println("3.Search for jobs using filters like job role, location, experience in years, and  company name.");
        System.out.println("4.Apply for interesting jobs (No Limit).");
        System.out.println("5.View Applications and their status.");
        System.out.println("6.Withdraw the application (from the interested jobs) in case of a change of interest");
        System.out.println("7.forget password and change password");
        System.out.println("8.exit");
        Scanner sc = new Scanner(System.in);
        int option = sc.nextInt();
        switch (option) {
            case 1:
                addExperience(loggedInJobSeekerId);
                break;
            case 2:
                addResume(loggedInJobSeekerId);
                break;
            case 3:
                searchJob();
                break;
            case 4:
                applyJobs(loggedInJobSeekerId);
                break;

            case 5:
                viewApplicationStatus(loggedInJobSeekerId);
                break;
            case 6:
                withdrawApplication(loggedInJobSeekerId);
                break;
            case 7:
                forgotPassword();
                break;
            case 8:
                System.out.println("Exit");
                scanner.close();
                System.exit(0);
                break;

        }
    }



    private void withdrawApplication(int jobSeekerId) {
        System.out.println("Enter the job id : ");
        int id=scanner.nextInt();
        JobAppication jobAppication=new JobAppication();
        jobAppication.setJobSeekerId(jobSeekerId);
        jobAppication.setJobId(id);
        jobSeekerService.withdrawApplication(jobAppication);
    }




    public void registerJobSeeker() {
        System.out.println("Job Seeker Registration:");
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        while(!CredentialsValidator.isValidUsername(username)){
            System.out.println("eneter username again");
            username=scanner.nextLine();
        }

        System.out.print("Enter password: ");
        String password = scanner.nextLine();
        while (!CredentialsValidator.isValidPassword(password)){
            System.out.println("invalid password enter again");
            password= scanner.nextLine();
        }

        System.out.print("Enter email: ");
        String email = scanner.nextLine();
       // String email = scanner.nextLine();
        while (!CredentialsValidator.isValidEmail(email)){
            System.out.println("enter valid email : ");
            email=scanner.nextLine();
        }


        System.out.print("Enter first name: ");
        String firstName = scanner.nextLine();

        System.out.print("Enter last name: ");
        String lastName = scanner.nextLine();

        System.out.print("Enter contact number: ");
        String contactNumber = scanner.nextLine();

        System.out.print("Enter address: ");
        String address = scanner.nextLine();

        JobSeeker jobSeeker = new JobSeeker(0, username, password, email, firstName, lastName, contactNumber, address, null);

        try {
            jobSeekerService.registerJobSeeker(jobSeeker);
            System.out.println("Job seeker registered successfully.");

            //addResume(jobSeeker.getId());
        } catch (SQLException e) {
            System.out.println("Error registering job seeker: " + e.getMessage());
        }
    }


    public void addResume(int jobSeekerId) {
        System.out.println("Please add your resume:");
        System.out.print("Enter resume (as plain text): ");
        String resume = scanner.nextLine();

        try {
            jobSeekerService.addResume(jobSeekerId, resume);
            System.out.println("Resume added successfully.");
        } catch (SQLException e) {
            System.out.println("Error adding resume: " + e.getMessage());
        }
    }

//    public void applyJobs(int jobId) {
//        Job jobs =  new Job();
//
//
//        try {
//
//            System.out.println("view all the jobs");
//             System.out.println(jobSeekerService.applyJobs(jobId));
//        }
//        catch (SQLException e){
//            System.out.println("no jobs available"+e.getMessage());
//        }
//    }
public void applyJobs(int jobSeekerId){
    System.out.println("Enter the job id : ");
    int jobId=scanner.nextInt();

    JobAppication jobAppication=new JobAppication();
    jobAppication.setJobId(jobId);
    jobAppication.setJobSeekerId(jobSeekerId);
    jobSeekerService.applyJob(jobAppication);
}
    public void   viewApplicationStatus(int  jobSeekerId){
        List<JobSeekerViewApplication> list = jobSeekerService.applicationsByJobSeekerId(jobSeekerId);
        printJobApplications(list);
    }





    public void addProfile(int userId) {
        while (true) {
            Scanner sc = new Scanner(System.in);
            System.out.println("1.education");
            System.out.println("2.experience");
            System.out.println("3.skills");
            System.out.println("4.exit");
            int option = sc.nextInt();
            switch (option) {
                case 1:
                    addEducation(userId);
                    break;
                case 2:
                    addEducation(userId);
                case 3:
                    addSkills(userId);
                    break;
                case 4:
                    return;
            }
        }
    }

    public void apply(int userId) {

    }

    public void viewStatus() {

    }

    public void searchJob() {
        Scanner sc=new Scanner(System.in);
        System.out.println("1.find the all jobs ");
        System.out.println("2.search job using filter");
         int option=sc.nextInt();
        switch(option){
            case 1:
                  List<Job> job=jobSeekerService.allJobs();

//                id, employer_id, title, description, location, salary, experience
                System.out.printf("%-10s %-20s %-20s %-30s %-20s %-10s %-10s%n", "job_id", "employer_id", "title", "description","location","salary","experience");
                System.out.printf(""+job.size());
                for(Job j:job){

                    System.out.printf("%-10s %-20s %-20s %-30s %-20s %-10s %-10s%n",j.getId(),j.getEmployerId(),j.getTitle(),j.getDescription(),j.getLocation(),j.getSalary(),j.getExperience());
                }

            case 2:
                System.out.println("1.role and experience");
                System.out.println("2.company");
                System.out.println("3. search by role experience location company : ");
                int jobOption=scanner.nextInt();
                switch (jobOption) {
                    case 1:
                        List<JobPost> jobs1 = jobService.searchByRoleAndExperience();
                        printJoPost(jobs1);
                        break;
                    case 2:
                        List<JobPost> jobs2 = jobService.searchByCompanyOrLocation();
                        printJoPost(jobs2);
                        break;
                    case 3:
                        List<JobPost> jobs3 = jobService.getAllJobPost();
                        printJoPost(jobs3);
                        break;
                }
        }
    }

    public void addEducation(int userId) {

    }

    public void addExperience(int userId) {
        Scanner sc = new Scanner(System.in);


        System.out.print("Job Title: ");
        String jobTitle = scanner.nextLine();

        System.out.print("Company Name: ");
        String companyName = scanner.nextLine();

        System.out.print("Location: ");
        String location = scanner.nextLine();

        System.out.print("Start Date (YYYY-MM-DD): ");
        String startDate = scanner.nextLine();

        System.out.print("End Date (YYYY-MM-DD, or leave blank for NULL): ");
        String endDate = scanner.nextLine();
        if (endDate.isEmpty()) {
            endDate = null;
        }

        System.out.print("Responsibilities: ");
        String responsibilities = scanner.nextLine();
        Experience e = new Experience(0, userId, jobTitle, companyName, location, Date.valueOf(startDate), Date.valueOf(endDate), responsibilities);
        jobSeekerService.addExperience(e);
    }

    public void addSkills(int userId) {

    }
    public  void jobPosts(){

    }
    public void printJobApplications(List<JobSeekerViewApplication> lba){
        JobSeekerDAO jsd = new JobSeekerDAO();
        int jobIdWidth = 8;
        int applicationIdWidth = 15;
        int companyNameWidth = 20;
        int roleWidth = 20;
        int locationWidth = 15;
        int dateWidth = 12;
        int experienceWidth = 10;
        int statusWidth = 12;

        System.out.printf(String.valueOf(jobIdWidth),applicationIdWidth , companyNameWidth , roleWidth , locationWidth ,dateWidth, experienceWidth , statusWidth + "s%n",
                "Job ID", "Application ID", "Company Name", "Role", "Location", "Register Date", "Experience", "Status");

        System.out.println(jobIdWidth + applicationIdWidth + companyNameWidth + roleWidth + locationWidth + dateWidth + experienceWidth + statusWidth + 7);

        for (JobSeekerViewApplication app : lba) {
            System.out.printf("%-" + jobIdWidth + "d | %-" + applicationIdWidth + "d | %-" + companyNameWidth + "s | %-" + roleWidth + "s | %-" + locationWidth + "s | %-" + dateWidth + "s | %-" + experienceWidth + "s | %-" + statusWidth + "s%n",
                    app.getJobId(), app.getApplicationId(), app.getCompanyName(), app.getRole(), app.getLocation(), app.getRegisterDate().toString(), app.getExperience(), app.getApplicationStatus());
        }
    }
    private void printJoPost(List<JobPost> jobPosts){
        System.out.printf("%-5s | %-10s | %-20s | %-25s | %-25s | %-20s | %-40s | %-20s | %-10s | %-10s%n",
                "ID", "Employer ID", "Company Name", "Email", "Address", "Title", "Job Details", "Location", "Salary", "Experience");
        System.out.println("---------------------------------------------------------------------------------"
                + "-----------------------------------------------------------------------------------------");
        for (JobPost j : jobPosts) {
            System.out.printf("%-5d | %-10d | %-20s | %-25s | %-25s | %-20s | %-40s | %-20s | $%-10f | %-10d%n",
                    j.getId(),
                    j.getEmployerId(),
                    j.getCompany(),
                    j.getEmail(),
                    j.getAddress(),
                    j.getTitle(),
                    j.getDescription(),
                    j.getLocation(),
                    j.getSalary(),
                    j.getExperience());
        }
    }
    public void forgotPassword(){
        System.out.println("Enter username : ");
        String username=scanner.next();
        System.out.println("Enter the email : ");
        String email=scanner.next();
        System.out.println("Enter the password");
        String password=scanner.next();
        jobSeekerService.forgotPassword(username,email,password);
    }


}


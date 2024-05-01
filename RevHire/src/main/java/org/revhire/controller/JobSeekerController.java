package org.revhire.controller;

import org.revhire.model.Experience;
import org.revhire.model.*;
import org.revhire.model.JobSeeker;
import org.revhire.service.JobSeekerService;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class JobSeekerController {
    //
//    1.	Register myself and create an account.
//            2.	Login to my account.
//            3.	Create and manage standard textual resume.
//            4.	Search for jobs using filters like job role, location, experience in years, and company name.
//            5.	Apply for interesting jobs (No Limit).
//            6.	View Applications and their status.
//   7.	Withdraw the application (from the interested jobs) in case of a change of interest.
    private JobSeekerService jobSeekerService = new JobSeekerService();
    private Scanner scanner = new Scanner(System.in);

    // Register a new job seeker and prompt them to add a resume


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
            case 6:
            case 7:
            case 8:
                return;
        }
    }



    public void registerJobSeeker() {
        System.out.println("Job Seeker Registration:");
        System.out.print("Enter username: ");
        String username = scanner.nextLine();

        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        System.out.print("Enter email: ");
        String email = scanner.nextLine();

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
            // Prompt the job seeker to add a resume
            addResume(jobSeeker.getId());
        } catch (SQLException e) {
            System.out.println("Error registering job seeker: " + e.getMessage());
        }
    }

    // Prompt the job seeker to add a resume
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

    public void applyJobs(int jobId) {
        Job jobs =  new Job();


        try {

            System.out.println("view all the jobs");
             System.out.println(jobSeekerService.applyJobs(jobId));
        }
        catch (SQLException e){
            System.out.println("no jobs available"+e.getMessage());
        }
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

        }
    }

    public void addEducation(int userId) {

    }

    public void addExperience(int userId) {
        Scanner sc = new Scanner(System.in);
//        CREATE TABLE experience (
//                id INT PRIMARY KEY AUTO_INCREMENT,
//                job_seeker_id INT NOT NULL,
//                job_title VARCHAR(255) NOT NULL,
//                company_name VARCHAR(255) NOT NULL,
//                location VARCHAR(255) NOT NULL,
//                start_date DATE NOT NULL,
//                end_date DATE DEFAULT NULL,
//                responsibilities TEXT,
//                FOREIGN KEY (job_seeker_id) REFERENCES job_seeker(id)
//        );

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
            endDate = null; // Insert null for end_date if the user leaves it blank
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


}


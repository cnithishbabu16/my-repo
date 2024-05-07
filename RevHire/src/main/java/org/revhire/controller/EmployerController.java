package org.revhire.controller;

import org.revhire.model.Employer;
import org.revhire.model.Job;
import org.revhire.model.JobAppication;
import org.revhire.service.EmployerService;
import org.revhire.service.JobSeekerService;
import org.revhire.service.JobService;
import org.revhire.util.CredentialsValidator;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class EmployerController {
    private EmployerService employerService = new EmployerService();
    private JobSeekerService jobSeekerService = new JobSeekerService();
    private JobService jobService = new JobService();
    private Scanner scanner = new Scanner(System.in);


    public void registerEmployer() {

        System.out.println("Employer Registration:");

        System.out.print("Name of the Employer:");
        String name= scanner.next();
        System.out.print("Enter username: ");
        String username = scanner.next();
        while(!CredentialsValidator.isValidUsername(username)){
            System.out.println("enter username again");
            username=scanner.nextLine();
        }


        System.out.print("Enter password: ");
        String password = scanner.next();
        while (!CredentialsValidator.isValidPassword(password)){
            System.out.println("invalid password enter again");
            password= scanner.nextLine();
        }

        System.out.print("Enter email: ");
        String email = scanner.next();
       // String email = scanner.nextLine();
        while (!CredentialsValidator.isValidEmail(email)){
            System.out.println("enter valid email : ");
            email=scanner.nextLine();
        }

        System.out.print("Enter company name: ");
        String companyName = scanner.next();

        System.out.print("Enter contact number: ");
        String contactNumber = scanner.next();

        System.out.print("Enter address: ");
        String address = scanner.next();

        System.out.print("Enter company description: ");
        String companyDescription = scanner.next();

        System.out.print("Enter industry: ");
        String industry = scanner.next();

        Employer employer = new Employer(0,name, username, password, email, companyName, contactNumber, address, companyDescription, industry);

        try {
            employerService.registerEmployer(employer);
            System.out.println("Employer registered successfully.");
        } catch (SQLException e) {
            System.out.println("Error registering employer: " + e.getMessage());
        }
    }

    public void showMenu(int loggedInEmployerId) {
        while(true){
            System.out.println("1.View ");
            System.out.println("2.Add new job post");

            //System.out.println(".Update new job post");

            System.out.println("3.Delete new job post");

            System.out.println("4.view applications");
            System.out.println("5.review application and provide status");
            System.out.println("6.Exit");
            System.out.print("Choose an option:");
            int choice =scanner.nextInt();

            switch (choice){
                case 1:
                    List<Job> jobs = jobService.viewAllJobsByEmployerById(loggedInEmployerId);
                    for(Job j: jobs) System.out.println(j);
                    break;
                case 2:
                    postJob(loggedInEmployerId);
                    break;
                case 3:
                    deletePost(loggedInEmployerId);

                    break;

                case 4:
                    viewApplications(loggedInEmployerId);
                    break;
                case 5:
                    updateStatus(loggedInEmployerId);
                    break;
                case 6:
                    System.out.println("Exiting employer");
                    scanner.close();
                    System.exit(0);


                    break;


            }


        }
    }



    public void postJob(int employerId){
        scanner.nextLine();
        System.out.print("Enter job title:");
        String title = scanner.nextLine();

        System.out.print("Enter job description:");
        String description = scanner.nextLine();

        System.out.print("Enter job location:");
        String location = scanner.nextLine();
        System.out.print("Enter job salary:");
        double salary = scanner.nextDouble();
        System.out.print("Enter job experience:");
        int experience = scanner.nextInt();

        Job job=new Job(0,employerId,title,description,location,salary,experience);
        employerService.postJob(job);


    }
    public  void deletePost(int employerId){
        System.out.println("enter job id : ");
        int  jobId=scanner.nextInt();
        employerService.deletePost(employerId,jobId);
    }
    public void viewApplications(int employerId){
        List<JobAppication> applications = jobService.getApplications(employerId);

        for(JobAppication j:applications){
            System.out.println(j);
        }
    }
    public void updateStatus(int employerId){
        System.out.println("Enter application id : ");
        int applicationId=scanner.nextInt();
//        System.out.println("enter job id : ");
        String status="";
        System.out.println("select  application status : ");
        System.out.println("1.approve");
        System.out.println("2.reject");
        System.out.println("3.pending");
        boolean b=true;
        while(b) {
            int choice=scanner.nextInt();
            switch (choice) {
                case 1:
                    status = "accepted";
                    b=false;
                    break;
                case 2:
                    status = "reject";
                    b=false;
                    break;
                case 3:
                    status = "pending";
                    b=false;
                    break;
                default:
                    System.out.println("enter valid input");
            }
        }
        employerService.updateStatus(employerId,applicationId,status);


    }




}


package org.revhire.controller;

import org.revhire.model.Employer;
import org.revhire.model.Job;
import org.revhire.service.EmployerService;
import org.revhire.service.JobService;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class EmployerController {
    private EmployerService employerService = new EmployerService();
    JobService jobService = new JobService();
    private Scanner scanner = new Scanner(System.in);


    public void registerEmployer() {

        System.out.println("Employer Registration:");

        System.out.print("Name of the Employer:");
        String name= scanner.next();
        System.out.print("Enter username: ");
        String username = scanner.next();

        System.out.print("Enter password: ");
        String password = scanner.next();

        System.out.print("Enter email: ");
        String email = scanner.next();

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

            System.out.println("3.Update new job post");

            System.out.println("4.Delete new job post");

            System.out.println("5.view applications");
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
                case 4:
                    deletePost(1206);

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
    public void deletePost(int jobId){
        System.out.print("Enter job Id:");
        scanner.nextInt();
        Job job = new Job();
        job.setId(jobId);

        employerService.deletePost(jobId);


    }

    public void updateJobPost(){

    }
}


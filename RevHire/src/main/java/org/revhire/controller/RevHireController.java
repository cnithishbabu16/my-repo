package org.revhire.controller;

import org.example.Main;
import org.revhire.model.Employer;
import org.revhire.model.JobSeeker;
import org.revhire.service.EmployerService;
import org.revhire.service.JobSeekerService;

import java.sql.SQLException;
import java.util.Scanner;


import org.revhire.util.CredentialsValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class RevHireController {
    private static final Logger logger = LoggerFactory.getLogger(RevHireController.class);

        private static int loggedInJobSeekerId = -1;
        private static int loggedInEmployerId = -1;

        public static void main(String[] args) {
            logger.info("This is an informational message as application started");
             Scanner scanner=new Scanner(System.in);
            JobSeekerController jobSeekerController = new JobSeekerController();
            EmployerController employerController = new EmployerController();

            while (true) {
                System.out.println("\nRevHire Job Portal:");
                System.out.println("1. Job Seeker Registration");
                System.out.println("2. Employer Registration");
                System.out.println("3. Job Seeker Login");
                System.out.println("4. Employer Login");
                System.out.println("5. Exit");
                System.out.print("Choose an option: ");
                int choice = Integer.parseInt(scanner.nextLine());

                switch (choice) {
                    case 1:
                        jobSeekerController.registerJobSeeker();
                        break;

                    case 2:
                        employerController.registerEmployer();
                        break;

                    case 3:
                        jobSeekerLogin(jobSeekerController);
                        break;

                    case 4:
                        employerLogin(employerController);
                        break;

                    case 5:
                        System.out.println("Exiting RevHire.");
                        scanner.close();
                        System.exit(0);
                        break;

                    default:
                        System.out.println("Invalid option. Please try again.");
                        break;
                }
            }
        }


        private static void jobSeekerLogin(JobSeekerController jobSeekerController) {
            logger.info("jobseeker logging in");
            Scanner scanner = new Scanner(System.in);
            System.out.print("Enter job seeker username: ");
            String username = scanner.nextLine();


            System.out.print("Enter password: ");
            String password = scanner.nextLine();



            JobSeekerService jobSeekerService = new JobSeekerService();
            try {
                JobSeeker jobSeeker = jobSeekerService.getJobSeekerByUsername(username);
                System.out.println("password : "+jobSeeker.getPassword());
                if (jobSeeker != null && jobSeeker.getPassword().equals(password)) {
                    System.out.println("Job Seeker Login Successful.");

                    loggedInJobSeekerId = jobSeeker.getId();
                    jobSeekerController.showMenu(loggedInJobSeekerId);
                } else {
                    System.out.println("Invalid job seeker credentials.");
                }
            } catch (SQLException e) {
                System.out.println("Error during job seeker login: " + e.getMessage());
            }
        }


        private static void employerLogin(EmployerController employerController) {
            Scanner scanner = new Scanner(System.in);
            System.out.print("Enter employer username: ");
            String username = scanner.nextLine();


            System.out.print("Enter password: ");
            String password = scanner.nextLine();



            EmployerService employerService = new EmployerService();
            try {
                Employer employer = employerService.getEmployerByUsername(username);

                if (employer != null && employer.getPassword().equals(password)) {
                    logger.info("Employer logged in");
                    System.out.println("Employer Login Successful.");
                    loggedInEmployerId = employer.getId();
                    employerController.showMenu(loggedInEmployerId);
                } else {
                    System.out.println("Invalid employer credentials.");
                }
            } catch (SQLException e) {
                System.out.println("Error during employer login: " + e.getMessage());
            }

        }
    }



package org.revhire.dao;

import org.revhire.model.Experience;
import org.revhire.model.Job;
import org.revhire.model.JobSeeker;
import org.revhire.util.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;



public class JobSeekerDAO {
    // Register a new job seeker
    public void registerJobSeeker(JobSeeker jobSeeker) throws SQLException {
        String query = "INSERT INTO job_seekers (username, password, email, first_name, last_name, contact_number, address) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, jobSeeker.getUsername());
            statement.setString(2, jobSeeker.getPassword());
            statement.setString(3, jobSeeker.getEmail());
            statement.setString(4, jobSeeker.getFirstName());
            statement.setString(5, jobSeeker.getLastName());
            statement.setString(6, jobSeeker.getContactNumber());
            statement.setString(7, jobSeeker.getAddress());
            statement.executeUpdate();
        }
    }


    // Add resume to a job seeker's profile
    public void addResume(int jobSeekerId, String resume) throws SQLException {
        String query = "UPDATE job_seekers SET resume = ? WHERE id = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, resume);
            statement.setInt(2, jobSeekerId);
            statement.executeUpdate();
        }
    }
    public List<Job> applyJobs(int jobId){
        String SELECT_ALL_JOBS_SQL = "SELECT * FROM jobs";
        List<Job> jobs = new ArrayList<>();

        try (PreparedStatement statement = DatabaseConnection.getConnection().prepareStatement(SELECT_ALL_JOBS_SQL)) {
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                int employerId = resultSet.getInt("employer_id");
                String title = resultSet.getString("title");
                String description = resultSet.getString("description");
                String location = resultSet.getString("location");
                double salary = resultSet.getDouble("salary");
                int experience = resultSet.getInt("experience");

                Job job = new Job(id, employerId, title, description, location, salary, experience);
                jobs.add(job);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return jobs;
    }

    // Retrieve a job seeker by username
    public JobSeeker getJobSeekerByUsername(String username) throws SQLException {
        String query = "SELECT * FROM job_seekers WHERE username = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, username);

            try (ResultSet resultSet = statement.executeQuery()) {
                System.out.println("executed  login "+resultSet);
                if (resultSet.next()) {
                    int id = resultSet.getInt("id");
                    String password = resultSet.getString("password");
                    String email = resultSet.getString("email");
                    String firstName = resultSet.getString("first_name");
                    String lastName = resultSet.getString("last_name");
                    String contactNumber = resultSet.getString("contact_number");
                    String address = resultSet.getString("address");
                    String resume = resultSet.getString("resume");
                    return new JobSeeker(id, username, password, email, firstName, lastName, contactNumber, address, resume);
                }
            }
        }
        return null; // Job seeker not found
    }

    public void addExperience(Experience experience) throws SQLException {
        String query = "INSERT INTO experience (job_seeker_id, job_title, company_name, location, start_date, end_date, responsibilities) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, experience.getJobSeekerId());
            statement.setString(2, experience.getJobTitle());
            statement.setString(3, experience.getCompanyName());
            statement.setString(4, experience.getLocation());
            statement.setDate(5, experience.getStartDate());
            statement.setDate(6, experience.getEndDate());
            statement.setString(7, experience.getResponsibilities());
            statement.executeUpdate();
        }
    }

    public List<Experience> getExperiencesByJobSeekerId(int jobSeekerId) throws SQLException {
        List<Experience> experiences = new ArrayList<>();
        String query = "SELECT * FROM experience WHERE job_seeker_id = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, jobSeekerId);

            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    int id = resultSet.getInt("id");
                    String jobTitle = resultSet.getString("job_title");
                    String companyName = resultSet.getString("company_name");
                    String location = resultSet.getString("location");
                    Date startDate = resultSet.getDate("start_date");
                    Date endDate = resultSet.getDate("end_date");
                    String responsibilities = resultSet.getString("responsibilities");
                    experiences.add(new Experience(id, jobSeekerId, jobTitle, companyName, location, startDate, endDate, responsibilities));
                }
            }
        }
        return experiences;
    }



}

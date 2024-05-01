package org.revhire.dao;

import org.revhire.model.Job;
import org.revhire.util.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JobDAO {

    public void postJob(Job job) throws SQLException {
        String query = "INSERT INTO jobs (employer_id, title, description, location, salary,experience) VALUES (?,?, ?, ?, ?, ?)";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, job.getEmployerId());
            statement.setString(2, job.getTitle());
            statement.setString(3, job.getDescription());
            statement.setString(4, job.getLocation());
            statement.setDouble(5, job.getSalary());
            statement.executeUpdate();
        }
    }


    public List<Job> searchJobs(String title, String location, int experience) throws SQLException {
        List<Job> jobs = new ArrayList<>();
        String query = "SELECT * FROM jobs WHERE title LIKE ? AND location LIKE ? AND experience_required <= ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, "%" + title + "%");
            statement.setString(2, "%" + location + "%");
            statement.setInt(3, experience);

            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    int id = resultSet.getInt("id");
                    int employerId = resultSet.getInt("employer_id");
                    String jobTitle = resultSet.getString("title");
                    String jobDescription = resultSet.getString("description");
                    String jobLocation = resultSet.getString("location");
                    double salary = resultSet.getDouble("salary");
                    int exp=resultSet.getInt("experience");
                    jobs.add(null
                    );
                }
            }
        }
        return jobs;
    }

    public List<Job> viewAllJobsByEmployerById(int empId) {
            List<Job> jobs = new ArrayList<>();
            try{

                Connection c = DatabaseConnection.getConnection();
                Statement statement = c.createStatement();


                String sql = "select * from jobs where employer_id = "+empId;
                ResultSet resultSet = statement.executeQuery(sql);


                while (resultSet.next()) {

                    int id = resultSet.getInt("id");
                    int employerId = resultSet.getInt("employer_id");
                    String title = resultSet.getString("title");
                    String description = resultSet.getString("description");
                    String location = resultSet.getString("location");
                    double salary = resultSet.getDouble("salary");
                    int experience = resultSet.getInt("experience");


                    Job job = new Job(0,employerId,title,description,location,salary,experience);
                    jobs.add(job);

                }

            }
            catch (Exception e){
                System.out.println("sql exception");
                System.out.println(e.getMessage());
            }
            return jobs;

    }


}


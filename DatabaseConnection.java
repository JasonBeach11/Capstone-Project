package com.example.memorization_mastery;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;

public class DatabaseConnection {
    // Database connection parameters
    private static String jdbcUrl = "jdbc:postgresql://localhost:5432/memorization_mastery";
    private static String dbUsername = "mastery";
    private static String dbPassword = "password";
    
    // Establish a database connection
    private static Connection connection;
    
    static {
    	try {
            Class.forName("org.postgresql.Driver");
    	connection = DriverManager.getConnection(jdbcUrl, dbUsername, dbPassword);
    	}
    	catch(Exception e){
    		throw new RuntimeException(e);
    	}
    };

    public static Connection getDatabaseConnection() {
    	return connection;
    }
    
    // Method to perform a database query
    public static void getUsernamesFromDataBase() {
        try {
            // Create an SQL query to retrieve the poem
            String sql = "SELECT * FROM User_Profiles";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            // preparedStatement.setString(1, poemTitle);

            // Execute the query
            ResultSet resultSet = preparedStatement.executeQuery();

            // Check if a result was found
            if (resultSet.next()) {
                
                String userName = resultSet.getString(2);

                System.out.println(userName);
            } else {
                System.out.println("Username not found");
            }
            
            // Close resources
            resultSet.close();
            preparedStatement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

 // Method to check if a username exists in the database
    public static boolean checkUsernameExists(String username) {
        boolean usernameExists = false;

        try  {
            String sql = "SELECT * FROM User_Profiles WHERE Username = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setString(1, username);

                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        // Username exists in the database
                        usernameExists = true;
                    }
                }
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return usernameExists;
    }

    public static int getNumberOfBadgesForUser(String username) {
        int numberOfBadges = -1; // Default value in case of an error or if the user is not found

        try  {
            String sql = "SELECT Badges FROM User_Profiles WHERE Username = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setString(1, username);

                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        // Retrieve the number of badges from the result set
                        numberOfBadges = resultSet.getInt("Badges");
                    } else {
                        System.out.println("User not found");
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return numberOfBadges;
    }

    // Method to retrieve poem titles from the database
    public static ObservableList<Writing> getPoemTitles() {
        return getWritings("Poems");
    }

    // Method to retrieve Scripture References from the database
    public static ObservableList<Writing> getScriptureReferences() {
        return getWritings("Scriptures");
    }

    // Method to retrieve Scripture References from the database
    public static ObservableList<Writing> getHistoricalWritingsTitles() {
        return getWritings("Historical Writings");
    }

    // Method to retrieve Scripture References from the database
    public static ObservableList<Writing> getFamousQuoteAuthors() {
        return getWritings("Famous Quotes");
    }

    // Method to retrieve Scripture References from the database
    public static ObservableList<Writing> getWritings(String type) {
        ObservableList<Writing> writings = FXCollections.observableArrayList();

        try {
            String sql = "SELECT * FROM Writings WHERE Type = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setString(1, type);
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    while (resultSet.next()) {
                        writings.add(mapWriting(resultSet));
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return writings;
    }
    
    public static String getTextForTitle(String title) {
    	try (PreparedStatement statement= connection.prepareStatement("select * from Writings where title = ?")){
    		statement.setString(1, title);
    		try(ResultSet rs = statement.executeQuery()){
    			return rs.getString("text");
    		}
    	}
    	catch(Exception e) {
    		e.printStackTrace();
    	}
    	return null;
    }

    private static Writing mapWriting(ResultSet rs) throws Exception{
        Writing w = new Writing();
        w.setId(rs.getInt("id"));
        w.setText(rs.getString("text"));
        w.setTitle(rs.getString("title"));
        w.setAuthor(rs.getString("Author"));
        w.setYear(rs.getInt("year"));

        return w;
    }
}
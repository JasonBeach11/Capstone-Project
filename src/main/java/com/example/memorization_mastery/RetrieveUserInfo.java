package com.example.memorization_mastery;

public class RetrieveUserInfo {

	private static String currentUser;
	private static int currentUserBadges;
	
	
	// Database connection parameters
    private String jdbcUrl = "jdbc:mysql://localhost:3306/Memorization_Mastery";
    private String dbUsername = "username";
    private String dbPassword = "password";
	
	public int getNumberOfBadgesForUser(String username) {
        int numberOfBadges = -1;
        currentUser = Controller.currentUser;
        System.out.println("You are in Retrieve user data. User is: " + currentUser);
        
        currentUserBadges = DatabaseConnection.getNumberOfBadgesForUser(currentUser);
        
        return currentUserBadges;
    }
}
